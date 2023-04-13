package by.mishastoma.integration;

import by.mishastoma.config.AppConfig;
import by.mishastoma.util.TestUtils;
import by.mishastoma.web.dto.AuthorDto;
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
public class AuthorIT {

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
        Serializable id = TestUtils.buildGetAuthorDto().getId();
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/authors/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/authors/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteTest_Unauthorized() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        Serializable id = TestUtils.buildGetAuthorDto().getId();
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/authors/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isForbidden());

    }

    @Test
    public void deleteTest_AccessDenied() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        Serializable id = TestUtils.buildGetAuthorDto().getId();
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/authors/{id}", id)
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
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildDefaultAuthorJson()))
                .andExpect(status().isCreated());
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/authors/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = TestUtils.ADMIN_USERNAME, password = TestUtils.ADMIN_PASSWORD, roles = TestUtils.ADMIN_ROLE)
    public void updateTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        AuthorDto authorDto = TestUtils.buildUpdateAuthorDto();
        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/authors/{id}", authorDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildUpdateAuthorJson()))
                .andExpect(status().isOk());
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/authors/{id}", authorDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findByIdTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        AuthorDto authorDto = TestUtils.buildDefaultItForAuthor();
        Serializable id = authorDto.getId();
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/authors/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(authorDto.getId()))
                .andExpect(jsonPath("firstname").value(authorDto.getFirstname()))
                .andExpect(jsonPath("surname").value(authorDto.getSurname()));
    }

    @Test
    public void findByIdTest_NotFound() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        Serializable id = TestUtils.NOT_FOUND_ID;
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/authors/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isNotFound());
    }
}
