package by.mishastoma;

import by.mishastoma.controller.AuthorController;
import by.mishastoma.controller.BookController;
import by.mishastoma.controller.GenreController;
import by.mishastoma.controller.UserController;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@Slf4j
@ComponentScan
public class Application {

    private static Gson gson;
    private static JsonParser jsonParser;

    @SneakyThrows
    public static void main(String... args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        gson = context.getBean(Gson.class);
        jsonParser = context.getBean(JsonParser.class);
        checkAuthor(context);
        checkBook(context);
        checkGenre(context);
        checkUser(context);

    }

    @SneakyThrows
    private static void checkAuthor(ApplicationContext context) {
        AuthorController controller = context.getBean(AuthorController.class);
        JsonElement jsonElement = jsonParser.parse(controller.findAll());
        log.info("Before operations:\n" + gson.toJson(jsonElement));
        controller.delete("{\"id\":1,\"firstname\":\"William\",\"surname\":\"Shakespeare\",\"patronymic\":null}");
        jsonElement = jsonParser.parse(controller.findAll());
        log.info("After delete:\n" + gson.toJson(jsonElement));
        controller.update("{\"id\":2,\"firstname\":\"Charles\",\"surname\":\"Dickens\",\"patronymic\":\"SomeValue\"}");
        jsonElement = jsonParser.parse(controller.findAll());
        log.info("After update:\n" + gson.toJson(jsonElement));
        controller.insert("{\"id\":3,\"firstname\":\"Anton\",\"surname\":\"Chekhov\",\"patronymic\":\"Pavlovich}\"}");
        jsonElement = jsonParser.parse(controller.findAll());
        log.info("After insert:\n" + gson.toJson(jsonElement));
    }

    @SneakyThrows
    private static void checkBook(ApplicationContext context) {
        BookController controller = context.getBean(BookController.class);
        JsonElement jsonElement = jsonParser.parse(controller.findAll());
        log.info("Before operations:\n" + gson.toJson(jsonElement));
        controller.delete("{\"id\":1,\"title\":\"Romeo and Juliet\",\"isbn\":\"9785171025212\",\"quantity\":2,\"releaseDate\":[2020,1,1],\"genreIds\":[9],\"authorIds\":[1]}");
        jsonElement = jsonParser.parse(controller.findAll());
        log.info("After delete:\n" + gson.toJson(jsonElement));
        controller.update("{\"id\":2,\"title\":\"Hamlet. Macbeth\",\"isbn\":\"9785171081591\",\"quantity\":8,\"releaseDate\":[2021,1,1],\"genreIds\":[9,8],\"authorIds\":[1]}");
        jsonElement = jsonParser.parse(controller.findAll());
        log.info("After update:\n" + gson.toJson(jsonElement));
        controller.insert("{\"id\":3,\"title\":\"Вишневый сад\",\"isbn\":\"9785171489496\",\"quantity\":2,\"releaseDate\":[2022,1,1],\"genreIds\":[10],\"authorIds\":[3]}");
        jsonElement = jsonParser.parse(controller.findAll());
        log.info("After insert:\n" + gson.toJson(jsonElement));
    }

    @SneakyThrows
    private static void checkGenre(ApplicationContext context) {
        GenreController controller = context.getBean(GenreController.class);
        JsonElement jsonElement = jsonParser.parse(controller.findAll());
        log.info("Before operations:\n" + gson.toJson(jsonElement));
        controller.delete("{\"id\":1,\"genre\":\"Fantasy\"}");
        jsonElement = jsonParser.parse(controller.findAll());
        log.info("After delete:\n" + gson.toJson(jsonElement));
        controller.update("{\"id\":2,\"genre\":\"Sci-Fi2\"}");
        jsonElement = jsonParser.parse(controller.findAll());
        controller.insert("{\"id\":3,\"genre\":\"Mystery\"}");
        log.info("After update:\n" + gson.toJson(jsonElement));
        jsonElement = jsonParser.parse(controller.findAll());
        log.info("After insert:\n" + gson.toJson(jsonElement));
    }

    @SneakyThrows
    private static void checkUser(ApplicationContext context) {
        UserController controller = context.getBean(UserController.class);
        JsonElement jsonElement = jsonParser.parse(controller.findAll());
        log.info("Before operations:\n" + gson.toJson(jsonElement));
        controller.delete("{\"id\":1,\"username\":\"addy\",\"password\":\"6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b\",\"role\":\"CUSTOMER\",\"firstname\":\"Addy\",\"surname\":\"Lamb\",\"phone\":\"375332949604\",\"email\":\"rogahn.charlie@franecki.com\",\"birthdate\":[2000,9,16],\"itemIds\":[],\"blocked\":false}");
        jsonElement = jsonParser.parse(controller.findAll());
        log.info("After delete:\n" + gson.toJson(jsonElement));
        controller.update("{\"id\":2,\"username\":\"max\",\"password\":\"d4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35\",\"role\":\"CUSTOMER\",\"firstname\":\"Max\",\"surname\":\"Evans\",\"phone\":\"375446926083\",\"email\":\"terry.maye@stracke.com\",\"birthdate\":[1998,2,13],\"itemIds\":[],\"blocked\":true}");
        jsonElement = jsonParser.parse(controller.findAll());
        controller.insert("{\"id\":3,\"username\":\"bernard\",\"password\":\"4e07408562bedb8b60ce05c1decfe3ad16b72230967de01f640b7e4729b49fce\",\"role\":\"CUSTOMER\",\"firstname\":\"Bernard\",\"surname\":\"Ford\",\"phone\":\"375336665432\",\"email\":\"dix-irigero20@yahoo.com\",\"birthdate\":[2005,5,20],\"itemIds\":[],\"blocked\":false}");
        log.info("After update:\n" + gson.toJson(jsonElement));
        jsonElement = jsonParser.parse(controller.findAll());
        log.info("After insert:\n" + gson.toJson(jsonElement));
    }
}
