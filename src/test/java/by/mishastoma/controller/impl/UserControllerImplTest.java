package by.mishastoma.controller.impl;

import by.mishastoma.config.ControllerTestConfig;
import by.mishastoma.config.mapper.MapperConfig;
import by.mishastoma.exception.UserNotFoundException;
import by.mishastoma.service.UserService;
import by.mishastoma.util.TestUtils;
import by.mishastoma.web.controller.impl.UserControllerImpl;
import by.mishastoma.web.dto.UserDto;
import by.mishastoma.web.handler.ControllerExceptionHandler;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.Serializable;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ControllerTestConfig.class, MapperConfig.class})
public class UserControllerImplTest {

    private MockMvc mockMvc;

    @Autowired
    private UserControllerImpl userController;

    @Autowired
    private UserService userService;

    @Test
    public void deleteTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        Serializable id = TestUtils.buildDefaultUser().getId();
        Mockito.doNothing().when(userService).delete(id);
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //then
        Mockito.verify(userService, Mockito.times(1)).delete(id);
    }

    @Test
    public void addTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        UserDto userDto = TestUtils.buildSaveUserDto();
        Mockito.when(userService.save(userDto)).thenReturn(userDto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildDefaultUserJson()))
                .andExpect(status().isCreated());
        //then
        Mockito.verify(userService, Mockito.times(1)).save(userDto);
    }

    @Test
    public void updateTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        UserDto userDto = TestUtils.buildUpdateUserDto();
        Mockito.doNothing().when(userService).update(userDto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}", userDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildUpdateUserJson()))
                .andExpect(status().isOk());
        //then
        Mockito.verify(userService, Mockito.times(1)).update(userDto);
    }

    @Test
    public void findByIdTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        UserDto userDto = TestUtils.buildGetUserDto();
        Serializable id = userDto.getId();
        Mockito.when(userService.findById(id)).thenReturn(userDto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(userDto.getId()))
                .andExpect(jsonPath("username").value(userDto.getUsername()))
                .andExpect(jsonPath("password").value(userDto.getPassword()))
                .andExpect(jsonPath("isBlocked").value(userDto.getIsBlocked()));
        //then
        Mockito.verify(userService, Mockito.times(1)).findById(id);
    }

    @Test
    public void findByIdTest_NotFound() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new ControllerExceptionHandler()).build();
        Serializable id = TestUtils.NOT_FOUND_ID;
        Mockito.when(userService.findById(id)).thenThrow(UserNotFoundException.class);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        //then
        Mockito.verify(userService, Mockito.times(1)).findById(id);
    }

    @Test
    public void findByUsernameTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        UserDto userDto = TestUtils.buildGetUserDto();
        String username = userDto.getUsername();
        Mockito.when(userService.findUserByUsername(username)).thenReturn(userDto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/users/?username=" + username)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(userDto.getId()))
                .andExpect(jsonPath("username").value(userDto.getUsername()))
                .andExpect(jsonPath("password").value(userDto.getPassword()))
                .andExpect(jsonPath("isBlocked").value(userDto.getIsBlocked()));
        //then
        Mockito.verify(userService, Mockito.times(1)).findUserByUsername(username);
    }

    @Test
    public void findByUsernameTest_NotFound() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new ControllerExceptionHandler()).build();
        String username = TestUtils.NOT_FOUND_USERNAME;
        Mockito.when(userService.findUserByUsername(username)).thenThrow(UserNotFoundException.class);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/users/?username=" + username)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        //then
        Mockito.verify(userService, Mockito.times(1)).findUserByUsername(username);
    }
}
