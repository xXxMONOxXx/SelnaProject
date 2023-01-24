package by.mishastoma.controller;

public interface CrudController {
    void insert(String obj);

    void delete(String obj);

    String findAll();

    void update(String obj);
}
