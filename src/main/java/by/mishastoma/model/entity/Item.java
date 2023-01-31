package by.mishastoma.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Item extends AbstractEntity {
    private Long bookId;
    private Long userId;
    private LocalDate takingDate;
    private LocalDate expirationDate;

}
