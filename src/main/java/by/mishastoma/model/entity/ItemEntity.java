package by.mishastoma.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "items", schema = "public", catalog = "postgres")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ItemEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "fk_book_id")
    private int fkBookId;
    @Basic
    @Column(name = "fk_users_id")
    private Integer fkUserId;
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
        if (id != that.id) return false;
        if (fkBookId != that.fkBookId) return false;
        if (fkUserId != null ? !fkUserId.equals(that.fkUserId) : that.fkUserId != null) return false;
        if (takingDate != null ? !takingDate.equals(that.takingDate) : that.takingDate != null) return false;
        if (expirationDate != null ? !expirationDate.equals(that.expirationDate) : that.expirationDate != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + fkBookId;
        result = 31 * result + (fkUserId != null ? fkUserId.hashCode() : 0);
        result = 31 * result + (takingDate != null ? takingDate.hashCode() : 0);
        result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
        return result;
    }
}
