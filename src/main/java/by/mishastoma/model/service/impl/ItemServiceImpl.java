package by.mishastoma.model.service.impl;

import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.dto.ItemDto;
import by.mishastoma.model.entity.Item;
import by.mishastoma.model.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;

@Component
public class ItemServiceImpl implements ItemService {

    private final ItemDao dao;
    private final ModelMapper modelMapper;

    private ItemServiceImpl(ItemDao dao, ModelMapper modelMapper) {
        this.dao = dao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void insert(ItemDto itemDto) {
        Item item = modelMapper.map(itemDto, Item.class);
        dao.save(item);
    }

    @Override
    public void delete(Serializable id) {
        Item item = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Can't find item with id " + id));
        dao.delete(item);
    }

    @Override
    public ItemDto findById(Serializable id) {
        Item itemEntity = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Can't find item with id " + id));
        return modelMapper.map(itemEntity, ItemDto.class);
    }

    @Override
    public void update(ItemDto itemDto) {
        Item item = modelMapper.map(itemDto, Item.class);
        dao.update(item);
    }
}
