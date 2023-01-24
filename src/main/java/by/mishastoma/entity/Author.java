package by.mishastoma.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Objects;


@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Author extends AbstractEntity {
    private String firstname;
    private String surname;
    private String patronymic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Author author = (Author) o;
        return Objects.equals(firstname, author.firstname) && Objects.equals(surname, author.surname) && Objects.equals(patronymic, author.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstname, surname, patronymic);
    }
}
