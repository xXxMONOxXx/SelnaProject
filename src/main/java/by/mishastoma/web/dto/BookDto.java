package by.mishastoma.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
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
    @Pattern(regexp="^[a-zA-Z0-9_-]{3,16}$", message = "Invalid title")
    private String title;
    @Size(min = 10, max = 13, message = "ISBN must be 13 or 10 numbers long")
    @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$\n", message = "ISBN must only numbers")
    private String isbn;
    private Date releaseDate;
    private Set<AuthorDto> authors;
    private Set<GenreDto> genres;
}
