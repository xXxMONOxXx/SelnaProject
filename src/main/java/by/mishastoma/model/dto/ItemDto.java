package by.mishastoma.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ItemDto {
    private Long id;
    private Long bookId;
    private Long userId;
    private Date takingDate;
    private Date expirationDate;

}
