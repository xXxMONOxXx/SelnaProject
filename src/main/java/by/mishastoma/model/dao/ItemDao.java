package by.mishastoma.model.dao;

import by.mishastoma.model.entity.Item;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ItemDao extends GenericDao<Item> {
    Optional<Item> getFreeItemForBook(Long bookId);

    Long countUsersItems(Long id);

    Page<Item> getItemsForBook(Long bookId, int pageNumber, int pageSize);

}
