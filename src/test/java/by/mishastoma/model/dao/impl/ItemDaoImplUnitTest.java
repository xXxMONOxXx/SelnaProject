package by.mishastoma.model.dao.impl;

import by.mishastoma.config.HibernateConfig;
import by.mishastoma.config.LiquibaseConfig;
import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.entity.Item;
import by.mishastoma.util.util.TestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {LiquibaseConfig.class, HibernateConfig.class, ItemDaoImpl.class},
        loader = AnnotationConfigContextLoader.class)
public class ItemDaoImplUnitTest {

    @Autowired
    private ItemDao itemDao;


    @Test
    public void saveTest() {
        //preparation
        Item expectedItem = TestUtils.buildSaveItem();
        //when
        itemDao.save(expectedItem);
        //then
        Item actualItem = itemDao.findById(expectedItem.getId()).get();
        Assert.assertEquals(expectedItem, actualItem);

    }

    @Test
    public void updateTest() {
        //preparation
        Item expectedItem = TestUtils.buildUpdateItem();
        //when
        itemDao.update(expectedItem);
        //then
        Item actualItem = itemDao.findById(expectedItem.getId()).get();
        Assert.assertEquals(expectedItem, actualItem);
    }

    @Test
    public void deleteTest() {
        //preparation
        Item defaultItem = TestUtils.buildDefaultItem();
        Item foundItem = itemDao.findById(defaultItem.getId()).get();
        //when
        itemDao.delete(foundItem);
        //then
        Assert.assertTrue(itemDao.findById(defaultItem.getId()).isEmpty());
    }

    @Test
    public void getTest() {
        //preparation
        Item expectedItem = TestUtils.buildDefaultItem();
        //when
        Item actualItem = itemDao.findById(expectedItem.getId()).get();
        //then
        Assert.assertEquals(expectedItem, actualItem);
    }
}
