package by.mishastoma.controller.impl.it;

import by.mishastoma.config.AppConfig;
import by.mishastoma.util.TestUtils;
import by.mishastoma.web.dto.GenreDto;
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
public class GenreIT {

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
        Serializable id = TestUtils.buildDefaultGenre().getId();
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/genres/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //then
    }

    @Test
    @WithMockUser(username = TestUtils.ADMIN_USERNAME, password = TestUtils.ADMIN_PASSWORD, roles = TestUtils.ADMIN_ROLE)
    public void addTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        GenreDto genreDto = TestUtils.buildSaveGenreDto();
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/genres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildDefaultGenreJson()))
                .andExpect(status().isCreated());
        //then
    }

    @Test
    @WithMockUser(username = TestUtils.ADMIN_USERNAME, password = TestUtils.ADMIN_PASSWORD, roles = TestUtils.ADMIN_ROLE)
    public void updateTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        GenreDto genreDto = TestUtils.buildUpdateGenreIT();
        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/genres/{id}", genreDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildUpdateGenreJsonIT()))
                //then
                .andExpect(status().isOk());
    }

    @Test
    public void findByIdTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        GenreDto genreDto = TestUtils.buildDefaultToFindGenre();
        Serializable id = genreDto.getId();
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/genres/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(genreDto.getId()))
                .andExpect(jsonPath("name").value(genreDto.getName()));

    }

    @Test
    public void findByIdTest_NotFound() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        Serializable id = TestUtils.NOT_FOUND_ID;
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/genres/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isNotFound());

    }

    @Test
    public void findByName() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        GenreDto genreDto = TestUtils.buildDefaultToFindGenre();
        String name = genreDto.getName();
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/genres/?name=" + name)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(genreDto.getId()))
                .andExpect(jsonPath("name").value(genreDto.getName()));

    }

    @Test
    public void findByName_NotFound() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        String name = TestUtils.GENRE_NOT_FOUND;
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/genres/?name=" + name)
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isNotFound());
    }
}
