package by.mishastoma.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "items")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ItemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "book_id")
    private int bookId;
    @Basic
    @Column(name = "users_id")
    private Integer userId;
    @Basic
    @Column(name = "taking_date")
    private LocalDate takingDate;
    @Basic
    @Column(name = "expiration_date")
    private LocalDate expirationDate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
