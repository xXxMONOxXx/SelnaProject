package by.mishastoma;

import by.mishastoma.controller.AuthorControllerImpl;
import by.mishastoma.controller.BookControllerImpl;
import by.mishastoma.model.dto.UserDto;
import by.mishastoma.model.service.UserService;
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
        //UserService bean = context.getBean(UserService.class);
        BookControllerImpl controller = context.getBean(BookControllerImpl.class);
        AuthorControllerImpl controller1 = context.getBean(AuthorControllerImpl.class);
        controller1.delete(3L);
    }
}
