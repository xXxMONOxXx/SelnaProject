package by.mishastoma;

import by.mishastoma.controller.AuthorControllerImpl;
import by.mishastoma.controller.BookControllerImpl;
import by.mishastoma.controller.GenreControllerImpl;
import by.mishastoma.controller.UserControllerImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@Slf4j
@ComponentScan
public class Application {
    @SneakyThrows
    public static void main(String... args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        checkAuthor(context);
        checkBook(context);
        checkGenre(context);
        checkUser(context);

    }

    @SneakyThrows
    private static void checkAuthor(ApplicationContext context) {
        AuthorControllerImpl controller = context.getBean(AuthorControllerImpl.class);
        log.info("Before operations:\n" + controller.findAll());
        controller.delete("{\"id\":1,\"firstname\":\"William\",\"surname\":\"Shakespeare\",\"patronymic\":null}");
        log.info("After delete:\n" + controller.findAll());
        controller.update("{\"id\":2,\"firstname\":\"Charles\",\"surname\":\"Dickens\",\"patronymic\":\"SomeValue\"}");
        log.info("After update:\n" + controller.findAll());
        controller.insert("{\"id\":3,\"firstname\":\"Anton\",\"surname\":\"Chekhov\",\"patronymic\":\"Pavlovich}\"}");
        log.info("After insert:\n" + controller.findAll());
    }

    @SneakyThrows
    private static void checkBook(ApplicationContext context) {
        BookControllerImpl controller = context.getBean(BookControllerImpl.class);
        log.info("Before operations:\n" + controller.findAll());
        controller.delete("{\"id\":1,\"title\":\"Romeo and Juliet\",\"isbn\":\"9785171025212\",\"quantity\":2,\"releaseDate\":[2020,1,1],\"genreIds\":[9],\"authorIds\":[1]}");
        log.info("After delete:\n" + controller.findAll());
        controller.update("{\"id\":2,\"title\":\"Hamlet. Macbeth\",\"isbn\":\"9785171081591\",\"quantity\":8,\"releaseDate\":[2021,1,1],\"genreIds\":[9,8],\"authorIds\":[1]}");
        log.info("After update:\n" + controller.findAll());
        controller.insert("{\"id\":3,\"title\":\"Вишневый сад\",\"isbn\":\"9785171489496\",\"quantity\":2,\"releaseDate\":[2022,1,1],\"genreIds\":[10],\"authorIds\":[3]}");
        log.info("After insert:\n" + controller.findAll());
    }

    @SneakyThrows
    private static void checkGenre(ApplicationContext context) {
        GenreControllerImpl controller = context.getBean(GenreControllerImpl.class);
        log.info("Before operations:\n" + controller.findAll());
        controller.delete("{\"id\":1,\"genre\":\"Fantasy\"}");
        log.info("After delete:\n" + controller.findAll());
        controller.update("{\"id\":2,\"genre\":\"Sci-Fi2\"}");
        log.info("After update:\n" + controller.findAll());
        controller.insert("{\"id\":3,\"genre\":\"Mystery\"}");
        log.info("After insert:\n" + controller.findAll());
    }

    @SneakyThrows
    private static void checkUser(ApplicationContext context) {
        UserControllerImpl controller = context.getBean(UserControllerImpl.class);
        log.info("Before operations:\n" + controller.findAll());
        controller.delete("{\"id\":1,\"username\":\"addy\",\"password\":\"6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b\",\"role\":\"CUSTOMER\",\"firstname\":\"Addy\",\"surname\":\"Lamb\",\"phone\":\"375332949604\",\"email\":\"rogahn.charlie@franecki.com\",\"birthdate\":[2000,9,16],\"itemIds\":[],\"blocked\":false}");
        log.info("After delete:\n" + controller.findAll());
        controller.update("{\"id\":2,\"username\":\"max\",\"password\":\"d4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35\",\"role\":\"CUSTOMER\",\"firstname\":\"Max\",\"surname\":\"Evans\",\"phone\":\"375446926083\",\"email\":\"terry.maye@stracke.com\",\"birthdate\":[1998,2,13],\"itemIds\":[],\"blocked\":true}");
        log.info("After update:\n" + controller.findAll());
        controller.insert("{\"id\":3,\"username\":\"bernard\",\"password\":\"4e07408562bedb8b60ce05c1decfe3ad16b72230967de01f640b7e4729b49fce\",\"role\":\"CUSTOMER\",\"firstname\":\"Bernard\",\"surname\":\"Ford\",\"phone\":\"375336665432\",\"email\":\"dix-irigero20@yahoo.com\",\"birthdate\":[2005,5,20],\"itemIds\":[],\"blocked\":false}");
        log.info("After insert:\n" + controller.findAll());
    }
}
