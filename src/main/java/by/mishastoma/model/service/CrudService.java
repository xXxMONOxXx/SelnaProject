package by.mishastoma.model.service;

public interface CrudService<M> {
    void insert(M m);

    void delete(M m);

    M findById(int id);

    void update(M m);
}
