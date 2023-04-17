package by.mishastoma.model.dao.impl;

import by.mishastoma.model.dao.AbstractDao;
import by.mishastoma.model.entity.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Component
@Repository
public class ProfileDao extends AbstractDao<Profile> {
    protected ProfileDao(EntityManager entityManager) {
        super(entityManager, Profile.class);
    }
}
