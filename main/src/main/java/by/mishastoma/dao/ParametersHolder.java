package by.mishastoma.dao;

import by.mishastoma.di.annotations.Value;
import lombok.Getter;

public class ParametersHolder {
    @Getter
    @Value("${my.param.db}")
    private String someText;
}
