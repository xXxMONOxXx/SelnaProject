package by.mishastoma.model.dao;

import by.mishastoma.model.entity.ItemEntity;

import java.io.Serializable;

public interface ItemDao {
    void save(ItemEntity entity);

    ItemEntity findById(Serializable id);

    void update(ItemEntity t);

    void delete(ItemEntity t);
}
