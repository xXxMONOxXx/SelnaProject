package by.mishastoma.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DTOBook {

    private Long id;
    private String title;
    private String isbn;
    private int quantity;
    private LocalDate releaseDate;
    private long[] genreIds;
    private long[] authorIds;
}
