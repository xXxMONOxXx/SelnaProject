package by.mishastoma.service.impl;

import by.mishastoma.config.ServiceTestConfig;
import by.mishastoma.config.mapper.MapperConfig;
import by.mishastoma.exception.ItemNotFoundException;
import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.entity.Item;
import by.mishastoma.service.ItemService;
import by.mishastoma.util.TestUtils;
import by.mishastoma.web.dto.ItemDto;
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
public class ItemServiceImplTest {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ItemService itemService;

    @Test
    public void saveTest() {
        //preparation
        ItemDto itemDto = TestUtils.buildSaveItemDto();
        Item item = TestUtils.buildSaveItem();
        Mockito.when(itemDao.save(item)).thenReturn(item);
        //when
        ItemDto returnedItem = itemService.save(itemDto);
        //then
        Mockito.verify(itemDao, Mockito.times(1)).save(item);
        Assert.assertEquals(itemDto, returnedItem);
    }

    @Test
    public void deleteTest() {
        //preparation
        Serializable id = TestUtils.buildDefaultItemDto().getId();
        Item item = TestUtils.buildDefaultItem();
        Mockito.doNothing().when(itemDao).delete(item);
        Mockito.when(itemDao.findById(id)).thenReturn(Optional.of(item));
        //when
        itemService.delete(id);
        //then
        Mockito.verify(itemDao, Mockito.times(1)).delete(item);
    }

    @Test
    public void updateTest() {
        //preparation
        ItemDto itemDto = TestUtils.buildUpdateItemDto();
        Item item = TestUtils.buildUpdateItem();
        Mockito.doNothing().when(itemDao).delete(item);
        //when
        itemService.update(itemDto);
        //then
        Mockito.verify(itemDao, Mockito.times(1)).update(item);
    }

    @Test
    public void getTest() {
        //preparation
        ItemDto expectedItem = TestUtils.buildGetItemDto();
        Serializable id = expectedItem.getId();
        Item item = TestUtils.buildGetItem();
        Mockito.when(itemDao.findById(id)).thenReturn(Optional.of(item));
        //when
        ItemDto actualItem = itemService.findById(id);
        //then
        Mockito.verify(itemDao, Mockito.times(1)).findById(id);
        Assert.assertEquals(expectedItem, actualItem);
    }

    @Test(expected = ItemNotFoundException.class)
    public void getTest_NotFound() {
        //preparation
        Serializable id = TestUtils.NOT_FOUND_ID;
        Mockito.when(itemDao.findById(id)).thenReturn(Optional.ofNullable(null));
        //when
        itemService.findById(id);
        //then
        Mockito.verify(itemDao, Mockito.times(1)).findById(id);
    }
}
