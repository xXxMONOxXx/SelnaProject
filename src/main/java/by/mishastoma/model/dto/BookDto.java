package by.mishastoma.model.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.Set;

@Data
@Builder
public class BookDto {

    private Long id;
    private String title;
    private String isbn;
    private Date releaseDate;
    private Set<AuthorDto> authors;
    private Set<GenreDto> genres;
    private Set<ItemDto> items;
}
