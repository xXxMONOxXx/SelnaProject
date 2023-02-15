package by.mishastoma.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDto {
    private Long id;
    private String firstname;
    private String surname;
    private String patronymic;
}
