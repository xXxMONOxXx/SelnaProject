package by.mishastoma.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Author extends AbstractEntity {

    private String firstname;
    private String surname;
    private String patronymic;
}
