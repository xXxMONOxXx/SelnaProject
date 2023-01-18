package by.mishastoma.services;

import by.mishastoma.dao.Dao;
import by.mishastoma.di.annotations.Autowire;
import by.mishastoma.di.annotations.Component;

@Component
public class ServiceImpl implements Service{

    private Dao dao;
    @Autowire
    public ServiceImpl(Dao dao){
        this.dao = dao;
    }
    @Override
    public String execute() {
        return dao.execute();
    }
}
