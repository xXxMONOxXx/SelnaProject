package by.mishastoma.model.service.impl;

import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.dto.DTOItem;
import by.mishastoma.model.entity.Item;
import by.mishastoma.model.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao dao;
    private final ModelMapper modelMapper;

    @Override
    public void insert(DTOItem dtoItem) {
        try {
            Item item = modelMapper.map(dtoItem, Item.class);
            dao.insert(item);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(DTOItem dtoItem) {
        try {
            Item item = modelMapper.map(dtoItem, Item.class);
            dao.delete(item);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DTOItem> findAll() {
        try {
            List<Item> items = dao.findAll();
            return items.stream().map(x -> modelMapper.map(x, DTOItem.class)).toList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(DTOItem dtoItem) {
        try {
            Item item = modelMapper.map(dtoItem, Item.class);
            dao.update(item);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
