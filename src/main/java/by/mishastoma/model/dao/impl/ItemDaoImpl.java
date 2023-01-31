package by.mishastoma.model.dao.impl;

import by.mishastoma.connection.ConnectionHolder;
import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemDaoImpl implements ItemDao {

    private final ConnectionHolder connectionHolder;
    private static final String INSERT_QUERY = "insert into items " +
            "(fk_book_id) values " +
            "(?)";
    private static final String UPDATE_QUERY = "update items " +
            "set fk_users_id = ?, " +
            "taking_date = ?, " +
            "expiration_date = ? " +
            "where id = ?";
    private static final String DELETE_QUERY = "delete " +
            "from items " +
            "where id = ? ";
    private static final String SELECT_ALL_QUERY = "select * " +
            "from items";
    private static final String COUNT_QUERY = "select count(*) as total " +
            "from items " +
            "where fk_book_id = ?";

    private static final String SELECT_BOOKS_ITEMS_QUERY = "select id " +
            "from items " +
            "where fk_book_id = ? and fk_users_id is null";

    private static final String DELETE_UNSIGNED_ITEM_QUERY = "delete from items " +
            "where fk_book_id = ? and fk_users_id is null";
    private static final String ID = "id";
    private static final String BOOK_ID = "fk_book_id";
    private static final String USER_ID = "fk_user_id";
    private static final String TAKING_DATE = "taking_date";
    private static final String EXPIRATION_DATE = "expiration_date";
    private static final String TOTAL = "total";

    @Override
    public void insert(Item item, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setLong(1, item.getBookId());
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void delete(Item item, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setLong(1, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<Item> findAll(Connection connection) throws SQLException {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item item = Item.builder().
                        id(resultSet.getLong(ID)).
                        bookId(resultSet.getLong(BOOK_ID)).
                        userId(resultSet.getLong(USER_ID)).
                        takingDate(resultSet.getDate(TAKING_DATE).toLocalDate()).
                        expirationDate(resultSet.getDate(EXPIRATION_DATE).toLocalDate()).
                        build();
                items.add(item);
            }

        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return items;
    }

    @Override
    public void update(Item item, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setLong(1, item.getUserId());
            statement.setDate(2, Date.valueOf(item.getTakingDate()));
            statement.setDate(3, Date.valueOf(item.getExpirationDate()));
            statement.setLong(4, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public int count(long bookId, Connection connection) throws SQLException {
        int quantity = 0;
        try (PreparedStatement statement = connection.prepareStatement(COUNT_QUERY)) {
            statement.setLong(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            quantity = resultSet.getInt(TOTAL);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return quantity;
    }

    @Override
    public List<Long> getItemsIds(long bookId, Connection connection) {
        List<Long> ids = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BOOKS_ITEMS_QUERY)) {
            statement.setLong(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getLong(ID));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ids;
    }

    @Override
    public void deleteUnsignedItem(long bookId, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_UNSIGNED_ITEM_QUERY)) {
            statement.setLong(1, bookId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
