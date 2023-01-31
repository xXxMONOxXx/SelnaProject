package by.mishastoma;

import by.mishastoma.controller.BookControllerImpl;
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
        Thread task1 = new Thread() {
            @Override
            public void run() {
                BookControllerImpl controller = context.getBean(BookControllerImpl.class);
                log.info("Before operations:\n" + controller.findAll());
            }
        };
        task1.start();
        Thread task2 = new Thread() {
            @Override
            public void run() {
                BookControllerImpl controller = context.getBean(BookControllerImpl.class);
                controller.delete("{\"id\":1,\"title\":\"Romeo and Juliet\",\"isbn\":\"9785171025212\",\"quantity\":2,\"releaseDate\":[2020,1,1],\"genreIds\":[9],\"authorIds\":[1]}");
                log.info("After delete:\n" + controller.findAll());
            }
        };
        task2.start();
        Thread task3 = new Thread() {
            @Override
            public void run() {
                BookControllerImpl controller = context.getBean(BookControllerImpl.class);
                controller.update("{\"id\":2,\"title\":\"Hamlet. Macbeth\",\"isbn\":\"9785171081591\",\"quantity\":8,\"releaseDate\":[2021,1,1],\"genreIds\":[9,8],\"authorIds\":[1]}");
                log.info("After update:\n" + controller.findAll());
            }
        };
        task3.start();
        Thread task4 = new Thread() {
            @Override
            public void run() {
                BookControllerImpl controller = context.getBean(BookControllerImpl.class);
                controller.insert("{\"title\":\"New Book\",\"isbn\":\"9785170009496\",\"quantity\":2,\"releaseDate\":[2022,1,1],\"genreIds\":[10],\"authorIds\":[3]}");
                log.info("After insert:\n" + controller.findAll());
            }
        };
        task4.start();

    }
}
