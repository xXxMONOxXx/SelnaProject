package by.mishastoma.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;
    @Size(max = 256, message = "Title maximum size is 32")
    private String title;
    @Size(min = 13, max = 13, message = "Isbn must be 13 numbers long")
    private String isbn;
    private Date releaseDate;
    private Set<AuthorDto> authors;
    private Set<GenreDto> genres;
}
