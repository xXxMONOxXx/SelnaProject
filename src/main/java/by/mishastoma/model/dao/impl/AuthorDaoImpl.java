package by.mishastoma.model.dao.impl;

import by.mishastoma.model.dao.AbstractDao;
import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.entity.Author;
import by.mishastoma.model.entity.Author_;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorDaoImpl extends AbstractDao<Author> implements AuthorDao {
    private AuthorDaoImpl(EntityManager entityManager) {
        super(entityManager, Author.class);
    }

    @Override
    public Page<Author> search(Author author, int pageNumber, int pageSize) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> query = builder.createQuery(Author.class);
        Root<Author> root = query.from(Author.class);

        List<Predicate> predicates = new ArrayList<>();
        if (author.getFirstname() != null) {
            predicates.add(builder.equal(root.get(Author_.FIRSTNAME), author.getFirstname()));
        }
        if (author.getSurname() != null) {
            predicates.add(builder.equal(root.get(Author_.SURNAME), author.getSurname()));
        }
        if (author.getPatronymic() != null) {
            predicates.add(builder.equal(root.get(Author_.PATRONYMIC), author.getPatronymic()));
        }

        query.where(predicates.toArray(new Predicate[predicates.size()]));

        TypedQuery<Author> typedQuery = entityManager.createQuery(query);

        return PageableExecutionUtils.getPage(typedQuery.getResultList(), PageRequest.of(pageNumber - 1, pageSize),
                this::countEntities);
    }
}
