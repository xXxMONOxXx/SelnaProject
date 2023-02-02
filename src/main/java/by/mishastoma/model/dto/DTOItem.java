package by.mishastoma.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DTOItem {
    private Long id;
    private Long bookId;
    private Long userId;
    private LocalDate takingDate;
    private LocalDate expirationDate;

}
