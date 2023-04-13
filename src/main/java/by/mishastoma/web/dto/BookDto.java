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
    @Pattern(regexp = "[a-zA-Z0-9_-]", message = "Invalid title")
    private String title;
    @Size(min = 10, max = 10, message = "ISBN must be 10 or 13 characters long")
    @Size(min = 13, max = 13, message = "ISBN must be 10 or 13 characters long")
    @Pattern(regexp = "-?\\d+", message = "ISBN must contain only numbers")
    private String isbn;
    private Date releaseDate;
    private Set<AuthorDto> authors;
    private Set<GenreDto> genres;
}
