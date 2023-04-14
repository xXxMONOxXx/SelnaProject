package by.mishastoma.service;

import by.mishastoma.web.dto.BookDto;

import java.io.Serializable;

public interface BookService extends CrudService<BookDto> {
    BookDto findBookByIdJpql(Serializable id);

    BookDto findBookByIdEntityGraph(Serializable id);

    BookDto findBookByIdCriteria(Serializable id);

    BookDto findBookByIsbn(String isbn);
}
