package by.mishastoma;

import by.mishastoma.controller.UserControllerImpl;
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
        //BookControllerImpl controller = context.getBean(BookControllerImpl.class);
        UserControllerImpl controller = context.getBean(UserControllerImpl.class);
        controller.delete("{" +
                "  \"id\" : 1," +
                "  \"username\" : \"addy\"," +
                "  \"password\" : \"6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b\"," +
                "  \"role\" : {" +
                "    \"id\" : 3," +
                "    \"role\" : \"admin\"" +
                "  }," +
                "  \"profile\" : {" +
                "    \"id\" : 1," +
                "    \"firstname\" : \"Addy\"," +
                "    \"surname\" : \"Lamb\"," +
                "    \"phone\" : \"375332949604\"," +
                "    \"email\" : \"rogahn.charlie@franecki.com\"," +
                "    \"birthdate\" : [ 2000, 9, 16 ]," +
                "    \"blocked\" : false" +
                "  }," +
                "  \"items\" : null" +
                "}");
    }
}
