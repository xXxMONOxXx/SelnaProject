package by.mishastoma.model.service.impl;

import by.mishastoma.connection.ConnectionHolder;
import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.dto.DTOAuthor;
import by.mishastoma.model.entity.Author;
import by.mishastoma.model.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao dao;
    private final ModelMapper modelMapper;
    private final ConnectionHolder connectionHolder;

    @Override
    public void insert(DTOAuthor dtoAuthor) {
        Author author = modelMapper.map(dtoAuthor, Author.class);
        Connection connection = null;
        try {
            connection = connectionHolder.getConnection();
            dao.insert(author, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                if (!connectionHolder.releaseConnection(connection)) {
                    throw new RuntimeException("Couldn't release connection, connection closed");
                }
            }
        }
    }

    @Override
    public void delete(DTOAuthor dtoAuthor) {
        Connection connection = null;
        try {
            Author author = modelMapper.map(dtoAuthor, Author.class);
            connection = connectionHolder.getConnection();
            dao.delete(author, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                if (!connectionHolder.releaseConnection(connection)) {
                    throw new RuntimeException("Couldn't release connection, connection closed");
                }
            }
        }
    }

    @Override
    public List<DTOAuthor> findAll() {
        Connection connection = null;
        try {
            connection = connectionHolder.getConnection();
            List<Author> authors = dao.findAll(connection);
            return authors.stream().map(x -> modelMapper.map(x, DTOAuthor.class)).toList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                if (!connectionHolder.releaseConnection(connection)) {
                    throw new RuntimeException("Couldn't release connection, connection closed");
                }
            }
        }
    }

    @Override
    public void update(DTOAuthor dtoAuthor) {
        Author author = modelMapper.map(dtoAuthor, Author.class);
        Connection connection = null;
        try {
            connection = connectionHolder.getConnection();
            dao.update(author, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                if (!connectionHolder.releaseConnection(connection)) {
                    throw new RuntimeException("Couldn't release connection, connection closed");
                }
            }
        }
    }
}
