package by.mishastoma.model.dao.impl;

import by.mishastoma.config.HibernateConfig;
import by.mishastoma.config.LiquibaseConfig;
import by.mishastoma.model.dao.UserDao;
import by.mishastoma.model.entity.Role;
import by.mishastoma.model.entity.User;
import org.junit.Assert;
import org.junit.Before;
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
public class UserDaoImplTest {

    private static final Long EXPECTED_ID = 1L;
    private static final String EXPECTED_USERNAME = "addy";
    private static final boolean EXPECTED_IS_BLOCKED = false;
    private static final String EXPECTED_PASSWORD = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
    private static final Long EXPECTED_ROLE_ID = 3L;
    private static final String EXPECTED_ROLE_NAME = "admin";
    private static final String SAVE_USERNAME = "test";
    private static final String SAVE_PASSWORD = "f2ca1bb6c7e907d06dafe4687e579fce76b37e4e93b7605022da52e6ccc26fd2";
    private static final String UPDATE_PASSWORD = "f2ca1bb6c7e907d06dafe4687e579fce76b37e4e93b7605022da52e6ccc26fd2";
    private static final String SAVE_ROLE_NAME = "librarian";
    private static final Long SAVE_ROLE_ID = 2L;
    private static final Long DELETE_ID = 3L;
    private static final Long SAVE_ID = 6L;
    private static User expectedEntity;
    private static User saveEntity;
    private static Role expectedRole;
    private static Role saveRole;
    private static List<User> expectedUsersWithAdminRole;

    @Autowired
    private UserDao dao;

    @Before
    public void setUp() {
        expectedRole = Role.builder()
                .id(EXPECTED_ROLE_ID)
                .role(EXPECTED_ROLE_NAME)
                .build();

        expectedEntity = User.builder()
                .id(EXPECTED_ID)
                .username(EXPECTED_USERNAME)
                .password(EXPECTED_PASSWORD)
                .isBlocked(EXPECTED_IS_BLOCKED)
                .role(expectedRole)
                .build();

        saveRole = Role.builder()
                .id(SAVE_ROLE_ID)
                .role(SAVE_ROLE_NAME)
                .build();

        saveEntity = User.builder()
                .username(SAVE_USERNAME)
                .password(SAVE_PASSWORD)
                .role(saveRole)
                .build();

        expectedUsersWithAdminRole = new ArrayList<>();
        expectedUsersWithAdminRole.add(expectedEntity);
    }

    @Test
    public void saveTest() {
        dao.save(saveEntity);
        Assert.assertTrue(dao.findById(SAVE_ID).isPresent());
    }

    @Test
    public void updateTest() {
        expectedEntity.setPassword(UPDATE_PASSWORD);
        dao.update(expectedEntity);
        User actualEntity = dao.findById(EXPECTED_ID).get();
        Assert.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    public void deleteTest() {
        User deleteEntity = dao.findById(DELETE_ID).get();
        dao.delete(deleteEntity);
        Assert.assertTrue(dao.findById(DELETE_ID).isEmpty());
    }

    @Test
    public void getTest() {
        User actualEntity = dao.findById(EXPECTED_ID).get();
        Assert.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    public void findUsersByRole() {
        List<User> actualUsers = dao.findByRole(expectedRole);
        Assert.assertEquals(expectedUsersWithAdminRole, actualUsers);
    }

    @Test
    public void findByIdCriteria() {
        User actualEntity = dao.findByIdCriteria(EXPECTED_ID).get();
        Assert.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    public void findByUsername() {
        User actualEntity = dao.findByUsername(EXPECTED_USERNAME).get();
        Assert.assertEquals(expectedEntity, actualEntity);
    }
}
