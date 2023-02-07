package by.mishastoma.model.dao.impl;

import by.mishastoma.model.dao.AbstractDao;
import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.entity.AuthorEntity;
import by.mishastoma.model.entity.ItemEntity;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Date;

@Component
public class ItemDaoImpl extends AbstractDao<ItemEntity> implements ItemDao {

    private ItemDaoImpl(LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        super(localContainerEntityManagerFactoryBean, ItemEntity.class);
    }
}
