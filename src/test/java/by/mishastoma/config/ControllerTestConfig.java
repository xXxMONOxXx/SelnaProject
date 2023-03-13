package by.mishastoma.config;

import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.dao.BookDao;
import by.mishastoma.model.dao.GenreDao;
import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.dao.UserDao;
import by.mishastoma.service.AuthorService;
import by.mishastoma.service.BookService;
import by.mishastoma.service.GenreService;
import by.mishastoma.service.ItemService;
import by.mishastoma.service.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan({"by.mishastoma.web.controller", "by.mishastoma.web.handler"})
public class ControllerTestConfig {

    @Bean
    public AuthorService authorService(){
        return Mockito.mock(AuthorService.class);
    }

    @Bean
    public BookService bookService(){
        return Mockito.mock(BookService.class);
    }

    @Bean
    public GenreService genreService(){
        return Mockito.mock(GenreService.class);
    }

    @Bean
    public ItemService itemService(){
        return Mockito.mock(ItemService.class);
    }

    @Bean
    public UserService userService(){
        return Mockito.mock(UserService.class);
    }
}
