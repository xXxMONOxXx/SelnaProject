package by.mishastoma.model.dao.impl;

import by.mishastoma.config.HibernateConfig;
import by.mishastoma.config.LiquibaseConfig;
import by.mishastoma.model.dao.UserDao;
import by.mishastoma.model.entity.RoleEntity;
import by.mishastoma.model.entity.UserEntity;
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

    private static final Integer EXPECTED_ID = 1;
    private static final String EXPECTED_USERNAME = "addy";
    private static final boolean EXPECTED_IS_BLOCKED = false;
    private static final String EXPECTED_PASSWORD = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
    private static final Integer EXPECTED_ROLE_ID = 3;
    private static final String EXPECTED_ROLE_NAME = "admin";
    private static final String SAVE_USERNAME = "test";
    private static final String SAVE_PASSWORD = "f2ca1bb6c7e907d06dafe4687e579fce76b37e4e93b7605022da52e6ccc26fd2";
    private static final String SAVE_ROLE_NAME = "librarian";
    private static final Integer SAVE_ROLE_ID = 2;
    private static UserEntity expectedEntity;
    private static UserEntity saveEntity;
    private static RoleEntity expectedRole;
    private static RoleEntity saveRole;
    private static List<UserEntity> expectedUsersWithAdminRole;

    @Autowired
    private UserDao dao;

    @Before
    public void setUp() {
        expectedRole = RoleEntity.builder()
                .id(EXPECTED_ROLE_ID)
                .role(EXPECTED_ROLE_NAME)
                .build();

        expectedEntity = UserEntity.builder()
                .id(EXPECTED_ID)
                .username(EXPECTED_USERNAME)
                .password(EXPECTED_PASSWORD)
                .isBlocked(EXPECTED_IS_BLOCKED)
                .role(expectedRole)
                .build();

        saveRole = RoleEntity.builder()
                .id(SAVE_ROLE_ID)
                .role(SAVE_ROLE_NAME)
                .build();

        saveEntity = UserEntity.builder()
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
    }

    @Test
    public void updateTest() {
        dao.update(expectedEntity);
    }

    @Test
    public void deleteTest() {
        dao.delete(expectedEntity);
    }

    @Test
    public void getTest() {
        UserEntity actualEntity = dao.findById(EXPECTED_ID);
        Assert.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    public void findUsersByRole() {
        List<UserEntity> actualUsers = dao.findUsersByRole(expectedRole);
        Assert.assertEquals(expectedUsersWithAdminRole, actualUsers);
    }

    @Test
    public void findByIdCriteria() {
        UserEntity actualEntity = dao.findByIdCriteria(EXPECTED_ID);
        Assert.assertEquals(expectedEntity, actualEntity);
    }

    @Test
    public void findByUsername() {
        UserEntity actualEntity = dao.findUserByUsername(EXPECTED_USERNAME);
        Assert.assertEquals(expectedEntity, actualEntity);
    }
}
