package by.mishastoma.model.dao.impl;

import by.mishastoma.model.dao.AbstractDao;
import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.entity.Item;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class ItemDaoImpl extends AbstractDao<Item> implements ItemDao {

    private ItemDaoImpl(EntityManager entityManager) {
        super(entityManager, Item.class);
    }
}
