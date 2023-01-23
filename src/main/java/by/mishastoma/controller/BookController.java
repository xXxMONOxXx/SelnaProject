package by.mishastoma.controller;

import by.mishastoma.exception.ControllerException;


public interface BookController {
    void insert(String obj) throws ControllerException;

    void delete(String obj) throws ControllerException;

    String findAll() throws ControllerException;

    void update(String obj) throws ControllerException;
}
