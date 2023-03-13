package by.mishastoma.service.impl;

import by.mishastoma.exception.ItemNotFoundException;
import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.entity.Item;
import by.mishastoma.service.ItemService;
import by.mishastoma.web.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public ItemDto save(ItemDto itemDto) {
        Item item = modelMapper.map(itemDto, Item.class);
        return modelMapper.map(itemDao.save(item), ItemDto.class);
    }

    @Override
    @Transactional
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
    @Transactional
    public void update(ItemDto itemDto) {
        Item item = modelMapper.map(itemDto, Item.class);
        itemDao.update(item);
    }
}
