package by.mishastoma;

import by.mishastoma.controllers.Controller;
import by.mishastoma.di.AnnotationConfigApplicationContext;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Application {
    @SneakyThrows
    public static void main(String... args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class, "application.properties");
        Controller bean = context.getBean(Controller.class);
        String dbResult = bean.execute();
        log.info(dbResult);
    }
}
