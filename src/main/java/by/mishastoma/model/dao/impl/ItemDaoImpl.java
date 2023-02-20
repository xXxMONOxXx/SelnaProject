package by.mishastoma.model.dao.impl;

import by.mishastoma.model.dao.AbstractDao;
import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.entity.Item;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Component
@Repository
public class ItemDaoImpl extends AbstractDao<Item> implements ItemDao {

    private ItemDaoImpl(EntityManager entityManager) {
        super(entityManager, Item.class);
    }
}
