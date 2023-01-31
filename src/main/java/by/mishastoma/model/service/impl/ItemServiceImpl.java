package by.mishastoma.model.service.impl;

import by.mishastoma.connection.ConnectionHolder;
import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.dto.DTOItem;
import by.mishastoma.model.entity.Item;
import by.mishastoma.model.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao dao;
    private final ModelMapper modelMapper;
    private final ConnectionHolder connectionHolder;

    @Override
    public void insert(DTOItem dtoItem) {
        Connection connection = null;
        try {
            connection = connectionHolder.getConnection();
            Item item = modelMapper.map(dtoItem, Item.class);
            dao.insert(item, connection);
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
    public void delete(DTOItem dtoItem) {
        Connection connection = null;
        try {
            connection = connectionHolder.getConnection();
            Item item = modelMapper.map(dtoItem, Item.class);
            dao.delete(item, connection);
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
    public List<DTOItem> findAll() {
        Connection connection = null;
        try {
            connection = connectionHolder.getConnection();
            List<Item> items = dao.findAll(connection);
            return items.stream().map(x -> modelMapper.map(x, DTOItem.class)).toList();
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
    public void update(DTOItem dtoItem) {
        Connection connection = null;
        try {
            connection = connectionHolder.getConnection();
            Item item = modelMapper.map(dtoItem, Item.class);
            dao.update(item, connection);
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
