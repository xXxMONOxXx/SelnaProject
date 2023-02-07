package by.mishastoma.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class DTOBook {

    private Integer id;
    private String title;
    private String isbn;
    private LocalDate releaseDate;
    private Set<DTOAuthor> authors;
    private Set<DTOGenre> genres;
    private Set<DTOItem> items;
}
