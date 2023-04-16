package by.mishastoma.service;

import by.mishastoma.util.BookSearchRequest;
import by.mishastoma.web.dto.AuthorDto;
import by.mishastoma.web.dto.BookDto;
import by.mishastoma.web.dto.GenreDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService extends CrudService<BookDto> {

    BookDto findBookByIsbn(String isbn);

    Page<BookDto> findBookByGenres(List<GenreDto> genres, int pageNumber, int pageSize);

    Page<BookDto> findBookByAuthors(List<AuthorDto> authors, int pageNumber, int pageSize);

    Page<BookDto> findBookByTitle(String title, int pageNumber, int pageSize);

    Page<BookDto> findBookWithParameters(BookSearchRequest bookSearchRequest, int pageNumber,
                                         int pageSize);
}
