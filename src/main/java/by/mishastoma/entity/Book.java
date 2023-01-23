package by.mishastoma.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Book extends AbstractEntity {
    private String title;
    private String isbn;
    private int quantity;
    private LocalDate releaseDate;
    private List<Long> genreIds;
    private List<Long> authorIds;
}
