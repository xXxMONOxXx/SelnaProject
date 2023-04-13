package by.mishastoma.unit.service.impl;

import by.mishastoma.config.ServiceTestConfig;
import by.mishastoma.config.mapper.MapperConfig;
import by.mishastoma.exception.UserNotFoundException;
import by.mishastoma.model.dao.UserDao;
import by.mishastoma.model.entity.User;
import by.mishastoma.service.UserService;
import by.mishastoma.util.TestUtils;
import by.mishastoma.web.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.Serializable;
import java.util.Optional;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceTestConfig.class, MapperConfig.class})
public class UserServiceImplTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Test
    public void saveTest() {
        //preparation
        UserDto userDto = TestUtils.buildSaveUserDto();
        User user = TestUtils.buildSaveUser();
        Mockito.when(userDao.save(user)).thenReturn(user);
        //when
        UserDto returnedUser = userService.save(userDto);
        //then
        Mockito.verify(userDao, Mockito.times(1)).save(user);
        Assert.assertEquals(userDto, returnedUser);
    }

    @Test
    public void deleteTest() {
        //preparation
        Serializable id = TestUtils.buildDefaultUserDto().getId();
        User user = TestUtils.buildDefaultUser();
        Mockito.doNothing().when(userDao).delete(user);
        Mockito.when(userDao.findById(id)).thenReturn(Optional.of(user));
        //when
        userService.delete(id);
        //then
        Mockito.verify(userDao, Mockito.times(1)).delete(user);
    }

    @Test
    public void updateTest() {
        //preparation
        UserDto userDto = TestUtils.buildUpdateUserDto();
        User user = TestUtils.buildUpdateUser();
        Mockito.doNothing().when(userDao).delete(user);
        //when
        userService.update(userDto);
        //then
        Mockito.verify(userDao, Mockito.times(1)).update(user);
    }

    @Test
    public void getTest() {
        //preparation
        UserDto expectedUser = TestUtils.buildGetUserDto();
        Serializable id = expectedUser.getId();
        User user = TestUtils.buildGetUser();
        Mockito.when(userDao.findById(id)).thenReturn(Optional.of(user));
        //when
        UserDto actualUser = userService.findById(id);
        //then
        Mockito.verify(userDao, Mockito.times(1)).findById(id);
        Assert.assertEquals(expectedUser, actualUser);
    }

    @Test(expected = UserNotFoundException.class)
    public void getTest_NotFound() {
        //preparation
        Serializable id = TestUtils.NOT_FOUND_ID;
        Mockito.when(userDao.findById(id)).thenReturn(Optional.ofNullable(null));
        //when
        userService.findById(id);
        //then
        Mockito.verify(userDao, Mockito.times(1)).findById(id);
    }

    @Test
    public void findUserByUsername() {
        //preparation
        UserDto expectedUser = TestUtils.buildGetUserDto();
        User user = TestUtils.buildGetUser();
        Mockito.when(userDao.findByUsername(expectedUser.getUsername())).thenReturn(Optional.of(user));
        //when
        UserDto actualUser = userService.findUserByUsername(expectedUser.getUsername());
        //then
        Mockito.verify(userDao, Mockito.times(1)).findByUsername(expectedUser.getUsername());
        Assert.assertEquals(expectedUser, actualUser);
    }

    @Test(expected = UserNotFoundException.class)
    public void findUserByUsername_NotFound() {
        //preparation
        Mockito.when(userDao.findByUsername(TestUtils.NOT_FOUND_USERNAME)).thenReturn(Optional.ofNullable(null));
        //when
        userService.findUserByUsername(TestUtils.NOT_FOUND_USERNAME);
        //then
        Mockito.verify(userDao, Mockito.times(1)).findByUsername(TestUtils.NOT_FOUND_USERNAME);
    }
}
