package by.mishastoma.util;

import by.mishastoma.web.dto.AuthorDto;
import by.mishastoma.web.dto.GenreDto;
import lombok.Data;

import java.util.List;

@Data
public class BookSearchRequest {
    private List<GenreDto> genres;
    private List<AuthorDto> authors;
    private String title;
}
