package by.mishastoma.model.dao.impl;

import by.mishastoma.model.dao.AbstractDao;
import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.entity.ItemEntity;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class ItemDaoImpl extends AbstractDao<ItemEntity> implements ItemDao {

    private ItemDaoImpl(LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        super(localContainerEntityManagerFactoryBean, ItemEntity.class);
    }
}
