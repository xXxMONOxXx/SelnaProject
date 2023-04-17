package by.mishastoma.config;

import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.dao.BookDao;
import by.mishastoma.model.dao.GenreDao;
import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.dao.UserDao;
import by.mishastoma.model.dao.impl.ProfileDao;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;

@EnableWebMvc
@ComponentScan({"by.mishastoma.util", "by.mishastoma.service"})
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
    public ProfileDao profileDao() {
        return Mockito.mock(ProfileDao.class);
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Mockito.mock(PasswordEncoder.class);
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Mockito.mock(EntityManagerFactory.class);
    }
}
