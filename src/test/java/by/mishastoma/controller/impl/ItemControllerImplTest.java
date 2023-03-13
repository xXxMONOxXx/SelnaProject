package by.mishastoma.controller.impl;

import by.mishastoma.config.ControllerTestConfig;
import by.mishastoma.config.mapper.MapperConfig;
import by.mishastoma.exception.AuthorNotFoundException;
import by.mishastoma.exception.ItemNotFoundException;
import by.mishastoma.service.ItemService;
import by.mishastoma.util.TestUtils;
import by.mishastoma.web.controller.impl.ItemControllerImpl;
import by.mishastoma.web.dto.AuthorDto;
import by.mishastoma.web.dto.ItemDto;
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
public class ItemControllerImplTest {

    private MockMvc mockMvc;

    @Autowired
    private ItemControllerImpl itemController;

    @Autowired
    private ItemService itemService;

    @Test
    public void deleteTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
        Serializable id = TestUtils.buildDefaultItem().getId();
        Mockito.doNothing().when(itemService).delete(id);
        //when
        mockMvc.perform(MockMvcRequestBuilders.delete("/items/delete/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //then
        Mockito.verify(itemService, Mockito.times(1)).delete(id);
    }

    @Test
    public void addTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
        ItemDto itemDto = TestUtils.buildSaveItemDto();
        Mockito.when(itemService.save(itemDto)).thenReturn(itemDto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/items/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildDefaultItemJson()))
                .andExpect(status().isCreated());
        //then
        Mockito.verify(itemService, Mockito.times(1)).save(itemDto);
    }

    @Test
    public void updateTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
        ItemDto authorDto = TestUtils.buildUpdateItemDto();
        Mockito.doNothing().when(itemService).update(authorDto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.put("/items/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.buildUpdateItemJson()))
                .andExpect(status().isOk());
        //then
        Mockito.verify(itemService, Mockito.times(1)).update(authorDto);
    }

    @Test
    public void findByIdTest() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
        ItemDto itemDto = TestUtils.buildGetItemDto();
        Serializable id = itemDto.getId();
        Mockito.when(itemService.findById(id)).thenReturn(itemDto);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/items/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(itemDto.getId()));
        //then
        Mockito.verify(itemService, Mockito.times(1)).findById(id);
    }

    @Test
    public void findByIdTest_NotFound() throws Exception {
        //preparation
        mockMvc = MockMvcBuilders.standaloneSetup(itemController)
                .setControllerAdvice(new ControllerExceptionHandler()).build();
        Serializable id = TestUtils.NOT_FOUND_ID;
        Mockito.when(itemService.findById(id)).thenThrow(ItemNotFoundException.class);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/items/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        //then
        Mockito.verify(itemService, Mockito.times(1)).findById(id);
    }
}
