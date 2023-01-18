package by.mishastoma.dao;

import by.mishastoma.di.annotations.Autowire;
import by.mishastoma.di.annotations.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DaoImpl implements Dao{
    @Autowire
    private ParametersHolder parametersHolder;
    @Override
    public String execute() {
        return parametersHolder.getSomeText();
    }
}
