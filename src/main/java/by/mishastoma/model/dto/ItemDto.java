package by.mishastoma.model.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class ItemDto {
    private Long id;
    private Long bookId;
    private Long userId;
    private Date takingDate;
    private Date expirationDate;

}
