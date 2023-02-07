package by.mishastoma.model.service;

import by.mishastoma.model.dto.DTOAuthor;
import by.mishastoma.model.dto.DTOBook;
import by.mishastoma.model.dto.DTOGenre;

import java.util.List;

public interface BookService extends CrudService<DTOBook> {

    DTOBook findBookByIdJpql(Integer id);
    DTOBook findBookByIdEntityGraph(Integer id);

    DTOBook findBookByIdCriteria(Integer id);

    DTOBook findBookByIsbn(String isbn);
}
