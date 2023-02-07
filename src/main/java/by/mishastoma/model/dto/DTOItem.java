package by.mishastoma.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DTOItem {
    private Integer id;
    private Integer bookId;
    private Integer userId;
    private LocalDate takingDate;
    private LocalDate expirationDate;

}
