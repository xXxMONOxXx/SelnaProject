package by.mishastoma.model.dao;

import by.mishastoma.model.entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao extends CrudDao<Item> {
    int count(long bookId) throws SQLException;

    List<Long> getItemsIds(long bookId) throws SQLException;

    void deleteUnsignedItem(long bookId) throws SQLException;
}
