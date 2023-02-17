package by.mishastoma;

import by.mishastoma.controller.AuthorControllerImpl;
import by.mishastoma.controller.BookControllerImpl;
import by.mishastoma.controller.UserControllerImpl;
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
        UserControllerImpl controller = context.getBean(UserControllerImpl.class);
        controller.delete(2L);
    }
}
