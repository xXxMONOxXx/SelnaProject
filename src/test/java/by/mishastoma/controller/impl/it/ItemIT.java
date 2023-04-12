package by.mishastoma.controller.impl.it;

import by.mishastoma.config.AppConfig;
import by.mishastoma.util.TestUtils;
import by.mishastoma.web.dto.ItemDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class ItemIT {

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
        Serializable id = TestUtils.buildDefaultItem().getId();
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/items/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest_Forbidden() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        Serializable id = TestUtils.buildDefaultItem().getId();
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/items/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
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
        ItemDto itemDto = TestUtils.buildSaveItemDto();
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildDefaultItemJson()))
                //then
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = TestUtils.ADMIN_USERNAME, password = TestUtils.ADMIN_PASSWORD, roles = TestUtils.ADMIN_ROLE)
    public void updateTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        ItemDto itemDto = TestUtils.buildUpdateItemDto();
        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/items/{id}", itemDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildUpdateItemJson()))
                //then
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = TestUtils.ADMIN_USERNAME, password = TestUtils.ADMIN_PASSWORD, roles = TestUtils.ADMIN_ROLE)
    public void findByIdTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        ItemDto itemDto = TestUtils.buildGetItemDto();
        Serializable id = itemDto.getId();
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/items/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(itemDto.getId()));
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
        mockMvc.perform(MockMvcRequestBuilders.get("/items/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isNotFound());
    }
}
