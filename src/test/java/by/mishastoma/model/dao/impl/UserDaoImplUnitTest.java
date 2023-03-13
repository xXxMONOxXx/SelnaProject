package by.mishastoma.model.dao.impl;

import by.mishastoma.config.db.HibernateConfig;
import by.mishastoma.config.db.LiquibaseConfig;
import by.mishastoma.model.dao.UserDao;
import by.mishastoma.model.entity.Role;
import by.mishastoma.model.entity.User;
import by.mishastoma.util.TestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {LiquibaseConfig.class, HibernateConfig.class, UserDaoImpl.class},
        loader = AnnotationConfigContextLoader.class)
public class UserDaoImplUnitTest {

    @Autowired
    private UserDao userDao;


    @Test
    public void saveTest() {
        //preparation
        User expectedUser = TestUtils.buildSaveUser();
        //when
        User returnedUser = userDao.save(expectedUser);
        //then
        User savedUser = userDao.findById(returnedUser.getId()).get();
        Assert.assertEquals(expectedUser, returnedUser);
        Assert.assertEquals(returnedUser, savedUser);
    }

    @Test
    public void updateTest() {
        //preparation
        User expectedUser = TestUtils.buildUpdateUser();
        //when
        userDao.update(expectedUser);
        //then
        User actualUser = userDao.findById(expectedUser.getId()).get();
        Assert.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void deleteTest() {
        //preparation
        User defaultUser = TestUtils.buildDefaultUser();
        User foundUser = userDao.findById(defaultUser.getId()).get();
        //when
        userDao.delete(foundUser);
        //then
        Assert.assertTrue(userDao.findById(defaultUser.getId()).isEmpty());
    }

    @Test
    public void getTest() {
        //preparation
        User expedctedUser = TestUtils.buildDefaultUser();
        //when
        User actualUser = userDao.findById(expedctedUser.getId()).get();
        //then
        Assert.assertEquals(expedctedUser, actualUser);
    }

    @Test
    public void findUsersByRole() {
        //preparation
        Role defaultRole = TestUtils.buildDefaultRole();
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(TestUtils.buildDefaultUser());
        //when
        List<User> actualUsers = userDao.findByRole(defaultRole);
        //then
        Assert.assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void findByIdCriteria() {
        //preparation
        User expedctedUser = TestUtils.buildDefaultUser();
        //when
        User actualUser = userDao.findByIdCriteria(expedctedUser.getId()).get();
        //then
        Assert.assertEquals(expedctedUser, actualUser);
    }

    @Test
    public void findByUsername() {
        //preparation
        User expedctedUser = TestUtils.buildDefaultUser();
        //when
        User actualUser = userDao.findByUsername(expedctedUser.getUsername()).get();
        //then
        Assert.assertEquals(expedctedUser, actualUser);
    }
}
