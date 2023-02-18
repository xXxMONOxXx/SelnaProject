package by.mishastoma.util.util;

import by.mishastoma.model.entity.Author;
import by.mishastoma.model.entity.Book;
import by.mishastoma.model.entity.Genre;
import by.mishastoma.model.entity.Item;
import by.mishastoma.model.entity.Role;
import by.mishastoma.model.entity.User;
import lombok.experimental.UtilityClass;

import java.sql.Date;

@UtilityClass
public class TestUtils {
    private final Long DEFAULT_ID = 1L;
    private final String DEFAULT_FIRSTNAME = "William";
    private final String DEFAULT_USERNAME = "addy";
    private final String DEFAULT_PASSWORD = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
    private final Boolean DEFAULT_IS_BLOCKED = false;
    private final String DEFAULT_SURNAME = "Shakespeare";
    private final String DEFAULT_TITLE = "Romeo and Juliet";
    private final String DEFAULT_ISBN = "9785171025212";
    private final String DEFAULT_NAME = "Test";
    private final String DEFAULT_GENRE_NAME = "Fantasy";
    private final String TEST_ISBN = "9999999999999";
    private final Date DEFAULT_DATE = Date.valueOf("2020-01-01");

    public Author buildDefaultAuthor() {
        return Author.builder()
                .id(DEFAULT_ID)
                .firstname(DEFAULT_FIRSTNAME)
                .surname(DEFAULT_SURNAME)
                .build();
    }

    public Author buildSaveAuthor() {
        return Author.builder()
                .firstname(DEFAULT_NAME)
                .surname(DEFAULT_NAME)
                .build();
    }

    public Author buildUpdateAuthor() {
        Author author = buildDefaultAuthor();
        author.setPatronymic(DEFAULT_NAME);
        return author;
    }

    public Book buildDefaultBook() {
        return Book.builder()
                .id(DEFAULT_ID)
                .title(DEFAULT_TITLE)
                .isbn(DEFAULT_ISBN)
                .releaseDate(DEFAULT_DATE)
                .build();
    }

    public Book buildSaveBook() {
        return Book.builder()
                .title(DEFAULT_NAME)
                .isbn(TEST_ISBN)
                .releaseDate(DEFAULT_DATE)
                .build();
    }

    public Book buildUpdateBook() {
        Book book = buildDefaultBook();
        book.setTitle(DEFAULT_NAME);
        return book;
    }

    public Genre buildDefaultGenre() {
        return Genre.builder()
                .id(DEFAULT_ID)
                .name(DEFAULT_GENRE_NAME)
                .build();
    }

    public Genre buildSaveGenre() {
        return Genre.builder()
                .name(DEFAULT_NAME)
                .build();
    }

    public Genre buildUpdateGenre() {
        Genre genre = buildDefaultGenre();
        genre.setName(DEFAULT_NAME);
        return genre;
    }

    public Item buildDefaultItem() {
        return Item.builder()
                .id(DEFAULT_ID)
                .bookId(DEFAULT_ID)
                .userId(DEFAULT_ID)
                .build();
    }

    public Item buildSaveItem() {
        return Item.builder()
                .bookId(DEFAULT_ID)
                .build();
    }

    public Item buildUpdateItem() {
        Item item = buildDefaultItem();
        item.setUserId(null);
        return item;
    }

    public User buildDefaultUser() {
        return User.builder()
                .id(DEFAULT_ID)
                .username(DEFAULT_USERNAME)
                .password(DEFAULT_PASSWORD)
                .isBlocked(DEFAULT_IS_BLOCKED)
                .role(buildDefaultRole())
                .build();
    }

    public User buildSaveUser() {
        return User.builder()
                .username(DEFAULT_NAME)
                .password(DEFAULT_PASSWORD)
                .role(buildDefaultRole())
                .build();
    }

    public User buildUpdateUser() {
        User user = buildDefaultUser();
        user.setIsBlocked(true);
        return user;
    }

    public Role buildDefaultRole() {
        return Role.builder()
                .id(3L)
                .role("admin")
                .build();
    }

}
