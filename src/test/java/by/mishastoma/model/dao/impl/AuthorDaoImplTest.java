package by.mishastoma.model.dao.impl;

import by.mishastoma.config.HibernateConfig;
import by.mishastoma.config.LiquibaseConfig;
import by.mishastoma.model.dao.AuthorDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { LiquibaseConfig.class, HibernateConfig.class, AuthorDaoImpl.class },
        loader = AnnotationConfigContextLoader.class)
public class AuthorDaoImplTest {

    @Autowired
    private AuthorDao dao;

    @Test
    public void saveTest(){

    }

    @Test
    public void updateTest(){

    }

    @Test
    public void deleteTest(){

    }

    @Test
    public void getTest(){
        System.out.println(dao.findById(1));
    }
}
