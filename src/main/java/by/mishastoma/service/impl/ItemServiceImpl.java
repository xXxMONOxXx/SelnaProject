package by.mishastoma.service.impl;

import by.mishastoma.exception.ItemNotFoundException;
import by.mishastoma.exception.NoFreeBooksFoundException;
import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.entity.Item;
import by.mishastoma.service.ItemService;
import by.mishastoma.web.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemDao itemDao;
    private final ModelMapper modelMapper;
    @Value("${items.max.number}")
    private Long maxItems;

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

    @Override
    @Transactional
    public Page<ItemDto> getAll(int pageNumber, int pageSize) {
        Page<Item> items = itemDao.getAll(pageNumber, pageSize);
        List<ItemDto> itemDtos = new ArrayList<>();
        for (Item item : items.getContent()) {
            itemDtos.add(modelMapper.map(item, ItemDto.class));
        }
        return new PageImpl<>(itemDtos, items.getPageable(), items.getTotalElements());
    }

    @Override
    @Transactional
    public boolean assignBook(Long bookId, Long userId, Date expirationDate) {
        Long numberOfAssignedBooks = itemDao.countUsersItems(userId);
        if (maxItems.equals(numberOfAssignedBooks)) {
            return false;
        }
        Item item = itemDao.getFreeItemForBook(bookId).orElseThrow(() -> new NoFreeBooksFoundException(bookId));
        item.setUserId(userId);
        item.setTakingDate(new Date(System.currentTimeMillis()));
        item.setExpirationDate(expirationDate);
        itemDao.update(item);
        return true;
    }

    @Override
    @Transactional
    public void unassignItem(Long itemId) {
        Item item = itemDao.findById(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));
        item.setUserId(null);
        item.setExpirationDate(null);
        item.setTakingDate(null);
        itemDao.update(item);
    }
}
