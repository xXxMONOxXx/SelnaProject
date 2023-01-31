package by.mishastoma.model.service.impl;

import by.mishastoma.connection.ConnectionHolder;
import by.mishastoma.model.dao.UserDao;
import by.mishastoma.model.dto.DTOUser;
import by.mishastoma.model.entity.User;
import by.mishastoma.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao dao;
    private final ModelMapper modelMapper;
    private final ConnectionHolder connectionHolder;

    @Override
    public void insert(DTOUser dtoUser) {
        Connection connection = null;
        try {
            connection = connectionHolder.getConnection();
            User user = modelMapper.map(dtoUser, User.class);
            dao.insert(user, connection);
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
    public void delete(DTOUser dtoUser) {
        Connection connection = null;
        try {
            connection = connectionHolder.getConnection();
            User user = modelMapper.map(dtoUser, User.class);
            dao.delete(user, connection);
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
    public List<DTOUser> findAll() {
        Connection connection = null;
        try {
            connection = connectionHolder.getConnection();
            List<User> users = dao.findAll(connection);
            return users.stream().map(x -> modelMapper.map(x, DTOUser.class)).toList();
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
    public void update(DTOUser dtoUser) {
        Connection connection = null;
        try {
            connection = connectionHolder.getConnection();
            User user = modelMapper.map(dtoUser, User.class);
            dao.update(user, connection);
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
