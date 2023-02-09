package by.mishastoma;

import by.mishastoma.controller.BookControllerImpl;
import liquibase.servicelocator.LiquibaseService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

@Slf4j
@ComponentScan
@Primary
@LiquibaseService
public class Application {

    @SneakyThrows
    public static void main(String... args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        //AuthorControllerImpl controller = context.getBean(AuthorControllerImpl.class);
        BookControllerImpl controller = context.getBean(BookControllerImpl.class);
        System.out.println(controller.findById(1));
    }
}
