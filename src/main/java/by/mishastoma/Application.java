package by.mishastoma;

import by.mishastoma.controller.UserControllerImpl;
import by.mishastoma.model.dao.UserDao;
import by.mishastoma.model.dao.impl.UserDaoImpl;
import by.mishastoma.model.entity.User;
import liquibase.servicelocator.LiquibaseService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

import java.util.Optional;

@Slf4j
@ComponentScan
@Primary
@LiquibaseService
public class Application {

    @SneakyThrows
    public static void main(String... args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        //AuthorControllerImpl controller = context.getBean(AuthorControllerImpl.class);
        //BookControllerImpl controller = context.getBean(BookControllerImpl.class);

        UserDao bean = context.getBean(UserDao.class);
        Optional<User> byId = bean.findByUsername("max");
        bean.delete(byId.get());
    }
}
