package by.mishastoma.integration;

import by.mishastoma.config.AppConfig;
import by.mishastoma.util.TestUtils;
import by.mishastoma.web.dto.UserDto;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class UserIT {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext applicationContext;

    @Test
    @WithMockUser(username = TestUtils.ADMIN_USERNAME, password = TestUtils.ADMIN_PASSWORD, roles = TestUtils.ADMIN_ROLE)
    public void deleteTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        Serializable id = TestUtils.DELETE_ID;
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                //then
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = TestUtils.ADMIN_USERNAME, password = TestUtils.ADMIN_PASSWORD, roles = TestUtils.ADMIN_ROLE)
    public void findByIdTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        UserDto userDto = TestUtils.buildDefaultUserIT();
        Serializable id = userDto.getId();
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(userDto.getId()))
                .andExpect(jsonPath("username").value(userDto.getUsername()))
                .andExpect(jsonPath("password").value(userDto.getPassword()))
                .andExpect(jsonPath("isBlocked").value(userDto.getIsBlocked()));
    }

    @Test
    public void deleteTest_Forbidden() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        Serializable id = TestUtils.DELETE_ID;
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                //then
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = TestUtils.ADMIN_USERNAME, password = TestUtils.ADMIN_PASSWORD, roles = TestUtils.ADMIN_ROLE)
    public void addTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        UserDto userDto = TestUtils.buildSaveUserDto();
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildDefaultUserJson()))
                //then
                .andExpect(status().isLocked());
    }

    @Test
    @WithMockUser(username = TestUtils.ADMIN_USERNAME, password = TestUtils.ADMIN_PASSWORD, roles = TestUtils.ADMIN_ROLE)
    public void updateTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        UserDto userDto = TestUtils.buildUpdateUserDto();
        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/users/{id}", userDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildUpdateUserJson()))
                //then
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = TestUtils.ADMIN_USERNAME, password = TestUtils.ADMIN_PASSWORD, roles = TestUtils.ADMIN_ROLE)
    public void findByIdTest_NotFound() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        Serializable id = TestUtils.NOT_FOUND_ID;
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = TestUtils.ADMIN_USERNAME, password = TestUtils.ADMIN_PASSWORD, roles = TestUtils.ADMIN_ROLE)
    public void findByUsernameTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        UserDto userDto = TestUtils.buildDefaultUserIT();
        String username = userDto.getUsername();
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/users/?username=" + username)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(userDto.getId()))
                .andExpect(jsonPath("username").value(userDto.getUsername()))
                .andExpect(jsonPath("password").value(userDto.getPassword()))
                .andExpect(jsonPath("isBlocked").value(userDto.getIsBlocked()));
    }

    @Test
    @WithMockUser(username = TestUtils.ADMIN_USERNAME, password = TestUtils.ADMIN_PASSWORD, roles = TestUtils.ADMIN_ROLE)
    public void findByUsernameTest_NotFound() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        String username = TestUtils.NOT_FOUND_USERNAME;
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/users/?username=" + username)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isNotFound());
    }
}
