package by.mishastoma.config;

import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.dao.BookDao;
import by.mishastoma.model.dao.GenreDao;
import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.dao.UserDao;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan("by.mishastoma.service")
public class ServiceTestConfig {

    @Bean
    public AuthorDao authorDao() {
        return Mockito.mock(AuthorDao.class);
    }

    @Bean
    public BookDao bookDao() {
        return Mockito.mock(BookDao.class);
    }

    @Bean
    public GenreDao genreDao() {
        return Mockito.mock(GenreDao.class);
    }

    @Bean
    public ItemDao itemDao() {
        return Mockito.mock(ItemDao.class);
    }

    @Bean
    public UserDao userDao() {
        return Mockito.mock(UserDao.class);
    }
}
