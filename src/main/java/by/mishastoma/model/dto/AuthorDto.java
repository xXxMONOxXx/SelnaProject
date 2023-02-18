package by.mishastoma.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDto {
    private Long id;
    private String firstname;
    private String surname;
    private String patronymic;
}
