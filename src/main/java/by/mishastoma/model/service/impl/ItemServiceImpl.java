package by.mishastoma.model.service.impl;

import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.dto.DTOItem;
import by.mishastoma.model.entity.ItemEntity;
import by.mishastoma.model.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ItemServiceImpl implements ItemService {

    private final ItemDao dao;
    private final ModelMapper modelMapper;

    private ItemServiceImpl(ItemDao dao, ModelMapper modelMapper){
        this.dao = dao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void insert(DTOItem dtoItem) {
        ItemEntity item = modelMapper.map(dtoItem, ItemEntity.class);
        dao.save(item);
    }

    @Override
    public void delete(DTOItem dtoItem) {
        ItemEntity item = modelMapper.map(dtoItem, ItemEntity.class);
        dao.delete(item);
    }

    @Override
    public DTOItem findById(int id) {
        return modelMapper.map(dao.findById(id), DTOItem.class);
    }

    @Override
    public void update(DTOItem dtoItem) {
        ItemEntity item = modelMapper.map(dtoItem, ItemEntity.class);
        dao.update(item);
    }
}
