package by.mishastoma.web.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@Builder
@JsonAutoDetect
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private Long id;
    @Size(max = 32, message = "Firstname maximum size is 32")
    private String firstname;
    @Size(max = 32, message = "Surname maximum size is 32")
    private String surname;
    @Size(max = 32, message = "Patronymic maximum size is 32")
    private String patronymic;
}
