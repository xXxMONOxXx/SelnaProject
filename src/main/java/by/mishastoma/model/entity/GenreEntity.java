package by.mishastoma.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "genres")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class GenreEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "genre")
    private String genre;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "book_genres",
            joinColumns = @JoinColumn(name = "genre_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<BookEntity> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenreEntity that = (GenreEntity) o;

        if (id != that.id) return false;
        if (genre != null ? !genre.equals(that.genre) : that.genre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }
}
