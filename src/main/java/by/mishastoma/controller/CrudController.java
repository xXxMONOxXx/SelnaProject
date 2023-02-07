package by.mishastoma.controller;

public interface CrudController {
    void insert(String obj);

    void delete(String obj);

    String findById(int id);

    void update(String obj);
}
