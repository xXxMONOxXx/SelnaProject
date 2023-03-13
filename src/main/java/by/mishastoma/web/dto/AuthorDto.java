package by.mishastoma.web.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonAutoDetect
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "firstname")
    private String firstname;
    @JsonProperty(value = "surname")
    private String surname;
    @JsonProperty(value = "patronymic")
    private String patronymic;
}
