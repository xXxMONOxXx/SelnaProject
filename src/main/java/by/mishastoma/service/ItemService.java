package by.mishastoma.service;

import by.mishastoma.web.dto.ItemDto;

import java.sql.Date;

public interface ItemService extends CrudService<ItemDto> {

    boolean assignBook(Long bookId, Long userId, Date expirationDate);

    void unassignItem(Long itemId);
}
