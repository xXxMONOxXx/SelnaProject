package by.mishastoma.controllers;

import by.mishastoma.di.annotations.Autowire;
import by.mishastoma.di.annotations.Component;
import by.mishastoma.services.Service;

@Component
public class ControllerImpl implements Controller{

    private Service service;
    @Autowire
    public void setService(Service service){
        this.service = service;
    }
    @Override
    public String execute() {
        return service.execute();
    }
}
