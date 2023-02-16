package by.mishastoma.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
public class BookDto {

    private Long id;
    private String title;
    private String isbn;
    private Date releaseDate;
    private Set<AuthorDto> authors;
    private Set<GenreDto> genres;
    private Set<ItemDto> items;
}
