package by.mishastoma.model.service.impl;

import by.mishastoma.exception.ItemNotFoundException;
import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.dto.ItemDto;
import by.mishastoma.model.entity.Item;
import by.mishastoma.model.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;
    private final ModelMapper modelMapper;

    @Override
    public void insert(ItemDto itemDto) {
        Item item = modelMapper.map(itemDto, Item.class);
        itemDao.save(item);
    }

    @Override
    public void delete(Serializable id) {
        Item item = itemDao.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
        itemDao.delete(item);
    }

    @Override
    public ItemDto findById(Serializable id) {
        Item itemEntity = itemDao.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
        return modelMapper.map(itemEntity, ItemDto.class);
    }

    @Override
    public void update(ItemDto itemDto) {
        Item item = modelMapper.map(itemDto, Item.class);
        itemDao.update(item);
    }
}
