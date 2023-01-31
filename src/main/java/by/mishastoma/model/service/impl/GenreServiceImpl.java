package by.mishastoma.model.service.impl;

import by.mishastoma.connection.ConnectionHolder;
import by.mishastoma.model.dao.GenreDao;
import by.mishastoma.model.dto.DTOGenre;
import by.mishastoma.model.entity.Genre;
import by.mishastoma.model.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreDao dao;
    private final ModelMapper modelMapper;
    private final ConnectionHolder connectionHolder;

    @Override
    public void insert(DTOGenre dtoGenre) {
        Connection connection = null;
        try {
            connection = connectionHolder.getConnection();
            Genre genre = modelMapper.map(dtoGenre, Genre.class);
            dao.insert(genre, connection);
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
    public void delete(DTOGenre dtoGenre) {
        Connection connection = null;
        try {
            connection = connectionHolder.getConnection();
            Genre genre = modelMapper.map(dtoGenre, Genre.class);
            dao.delete(genre, connection);
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
    public List<DTOGenre> findAll() {
        Connection connection = null;
        try {
            connection = connectionHolder.getConnection();
            List<Genre> genres = dao.findAll(connection);
            return genres.stream().map(x -> modelMapper.map(x, DTOGenre.class)).toList();
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
    public void update(DTOGenre dtoGenre) {
        Connection connection = null;
        try {
            connection = connectionHolder.getConnection();
            Genre genre = modelMapper.map(dtoGenre, Genre.class);
            dao.update(genre, connection);
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
