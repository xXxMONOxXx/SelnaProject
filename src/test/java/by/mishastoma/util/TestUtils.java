package by.mishastoma.util;

import by.mishastoma.model.entity.Author;
import by.mishastoma.model.entity.Book;
import by.mishastoma.model.entity.Genre;
import by.mishastoma.model.entity.Item;
import by.mishastoma.model.entity.Role;
import by.mishastoma.model.entity.User;
import by.mishastoma.web.dto.AuthorDto;
import by.mishastoma.web.dto.BookDto;
import by.mishastoma.web.dto.GenreDto;
import by.mishastoma.web.dto.ItemDto;
import by.mishastoma.web.dto.RoleDto;
import by.mishastoma.web.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

import java.sql.Date;

@UtilityClass
public class TestUtils {
    private final Long DEFAULT_ROLE_ID = 3L;
    private final String DEFAULT_ROLE_NAME = "admin";
    private final Long DEFAULT_ID = 1L;
    private final Long GET_ID = 2L;
    private final String DEFAULT_FIRSTNAME = "William";
    private final String DEFAULT_USERNAME = "addy";
    private final String DEFAULT_PASSWORD = "6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b";
    private final Boolean DEFAULT_IS_BLOCKED = false;
    private final String DEFAULT_SURNAME = "Shakespeare";
    private final String DEFAULT_TITLE = "Romeo and Juliet";
    private final String DEFAULT_ISBN = "9785171025212";
    private final String DEFAULT_NAME = "Test";
    private final String DEFAULT_GENRE_NAME_UPDATE = "update";
    private final String DEFAULT_GENRE_NAME = "Fantasy";
    private final String TEST_ISBN = "9999999999999";
    private final Date DEFAULT_DATE = Date.valueOf("2020-01-01");
    private final long UPDATE_ID = 2;
    private final String DEFAULT_PASSWORD_IT = "$2a$12$/tuGAxIFvhUE9HukNjYN0.jrP4VqC4nleBsucXkth./a.xtxOscx2";
    private final String SECRET = "bd2b1aaf7ef4f09be9f52ce2d8d599674d81aa9d6a4421696dc4d93dd0619d682ce56b4d64a9ef097761ced99e0f67265b5f76085e5b0ee7ca4696b2ad6fe2b2";
    public final Long NOT_FOUND_ID = 99L;
    public final String NOT_FOUND_USERNAME = "not_found";
    public final String ISBN_NOT_FOUND = "12223344512";
    public final String GENRE_NOT_FOUND = "ggg";
    public final String ADMIN_USERNAME = "addy";
    public final String ADMIN_PASSWORD = "123456";
    public final String ADMIN_ROLE = "admin";
    public final long DELETE_ID = 3;


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

    public Author buildGetAuthor() {
        Author author = buildUpdateAuthor();
        author.setId(GET_ID);
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

    public Book buildGetBook() {
        Book book = buildUpdateBook();
        book.setId(GET_ID);
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

    public Genre buildGetGenre() {
        Genre genre = buildUpdateGenre();
        genre.setId(GET_ID);
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

    public Item buildGetItem() {
        Item item = buildUpdateItem();
        item.setId(GET_ID);
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

    public User buildGetUser() {
        User user = buildUpdateUser();
        user.setId(GET_ID);
        return user;
    }

    public Role buildDefaultRole() {
        return Role.builder()
                .id(DEFAULT_ROLE_ID)
                .role(DEFAULT_ROLE_NAME)
                .build();
    }

    public AuthorDto buildSaveAuthorDto() {
        return AuthorDto.builder()
                .firstname(DEFAULT_NAME)
                .surname(DEFAULT_NAME)
                .build();
    }

    public AuthorDto buildDefaultAuthorDto() {
        return AuthorDto.builder()
                .id(DEFAULT_ID)
                .firstname(DEFAULT_FIRSTNAME)
                .surname(DEFAULT_SURNAME)
                .build();
    }

    public AuthorDto buildGetAuthorDto() {
        AuthorDto authorDto = buildUpdateAuthorDto();
        authorDto.setId(GET_ID);
        return authorDto;
    }

    public AuthorDto buildUpdateAuthorDto() {
        AuthorDto authorDto = buildDefaultAuthorDto();
        authorDto.setPatronymic(DEFAULT_NAME);
        return authorDto;
    }

    public AuthorDto buildDefaultItForAuthor() {
        return AuthorDto.builder()
                .id(DEFAULT_ID)
                .firstname("William")
                .surname("Shakespeare")
                .build();
    }

    public BookDto buildDefaultItForBook() {
        return BookDto.builder()
                .id(DEFAULT_ID)
                .isbn("9785171025212")
                .title("Romeo and Juliet")
                .build();
    }

    public BookDto buildSaveBookDto() {
        return BookDto.builder()
                .title(DEFAULT_NAME)
                .isbn(TEST_ISBN)
                .releaseDate(DEFAULT_DATE)
                .build();
    }

    public BookDto buildDefaultBookDto() {
        return BookDto.builder()
                .id(DEFAULT_ID)
                .title(DEFAULT_TITLE)
                .isbn(DEFAULT_ISBN)
                .releaseDate(DEFAULT_DATE)
                .build();
    }

    public BookDto buildGetBookDto() {
        BookDto bookDto = buildUpdateBookDto();
        bookDto.setId(GET_ID);
        return bookDto;
    }

    public BookDto buildUpdateBookDto() {
        BookDto bookDto = buildDefaultBookDto();
        bookDto.setTitle(DEFAULT_NAME);
        return bookDto;
    }

    public GenreDto buildSaveGenreDto() {
        return GenreDto.builder()
                .name(DEFAULT_NAME)
                .build();
    }

    public GenreDto buildDefaultGenreDto() {
        return GenreDto.builder()
                .id(DEFAULT_ID)
                .name(DEFAULT_GENRE_NAME)
                .build();
    }

    public GenreDto buildUpdateGenreDto() {
        GenreDto genreDto = buildDefaultGenreDto();
        genreDto.setName(DEFAULT_NAME);
        return genreDto;
    }

    public GenreDto buildGetGenreDto() {
        GenreDto genreDto = buildUpdateGenreDto();
        genreDto.setId(GET_ID);
        return genreDto;
    }

    public GenreDto buildDefaultToFindGenre() {
        return GenreDto.builder()
                .id(DEFAULT_ID)
                .name(DEFAULT_GENRE_NAME)
                .build();
    }

    public GenreDto buildUpdateGenreIT() {
        return GenreDto.builder()
                .id(UPDATE_ID)
                .name(DEFAULT_GENRE_NAME_UPDATE)
                .build();
    }

    public ItemDto buildDefaultItemDto() {
        return ItemDto.builder()
                .id(DEFAULT_ID)
                .bookId(DEFAULT_ID)
                .userId(DEFAULT_ID)
                .build();
    }

    public ItemDto buildUpdateItemDto() {
        ItemDto itemDto = buildDefaultItemDto();
        itemDto.setUserId(null);
        return itemDto;
    }

    public ItemDto buildSaveItemDto() {
        return ItemDto.builder()
                .bookId(DEFAULT_ID)
                .build();
    }

    public ItemDto buildGetItemDto() {
        ItemDto itemDto = buildUpdateItemDto();
        itemDto.setId(GET_ID);
        return itemDto;
    }

    public RoleDto buildDefaultRoleDto() {
        return RoleDto.builder()
                .id(3L)
                .role("admin")
                .build();
    }

    public UserDto buildDefaultUserDto() {
        return UserDto.builder()
                .id(DEFAULT_ID)
                .username(DEFAULT_USERNAME)
                .password(DEFAULT_PASSWORD)
                .isBlocked(DEFAULT_IS_BLOCKED)
                .role(buildDefaultRoleDto())
                .build();
    }

    public UserDto buildUpdateUserDto() {
        UserDto userDto = buildDefaultUserDto();
        userDto.setIsBlocked(true);
        return userDto;
    }

    public UserDto buildSaveUserDto() {
        return UserDto.builder()
                .username(DEFAULT_NAME)
                .password(DEFAULT_PASSWORD)
                .role(buildDefaultRoleDto())
                .build();
    }

    public UserDto buildGetUserDto() {
        UserDto userDto = buildUpdateUserDto();
        userDto.setId(GET_ID);
        return userDto;
    }

    public UserDto buildDefaultUserIT() {
        return UserDto.builder()
                .id(DEFAULT_ID)
                .username(DEFAULT_USERNAME)
                .password(DEFAULT_PASSWORD_IT)
                .isBlocked(DEFAULT_IS_BLOCKED)
                .role(buildDefaultRoleDto())
                .build();
    }

    public String buildDefaultAuthorJson() {
        return String.format("{" +
                "    \"firstname\": \"%s\"," +
                "    \"surname\": \"%s\" " +
                "}", DEFAULT_NAME, DEFAULT_NAME);
    }

    public String buildUpdateAuthorJson() {
        return String.format("{" +
                "    \"id\": %d," +
                "    \"firstname\": \"%s\"," +
                "    \"surname\": \"%s\"," +
                "    \"patronymic\": \"%s\"" +
                "    \n" +
                "}", DEFAULT_ID, DEFAULT_FIRSTNAME, DEFAULT_SURNAME, DEFAULT_NAME);
    }

    public String buildDefaultBookJson() {
        System.out.println(DEFAULT_DATE.toString());
        return String.format("{" +
                "    \"title\": \"%s\"," +
                "    \"isbn\": \"%s\"," +
                "    \"releaseDate\": %s," +
                "    \"authors\": null," +
                "    \"genres\": null" +
                "}", DEFAULT_NAME, TEST_ISBN, DEFAULT_DATE.getTime());
    }

    public String buildUpdateBookJson() {
        return String.format("{" +
                "    \"id\": %d," +
                "    \"title\": \"%s\"," +
                "    \"isbn\": \"%s\"," +
                "    \"releaseDate\": %s," +
                "    \"authors\": null," +
                "    \"genres\": null" +
                "}", DEFAULT_ID, DEFAULT_NAME, DEFAULT_ISBN, DEFAULT_DATE.getTime());
    }

    public String buildDefaultGenreJson() {
        return String.format("{" +
                "    \"name\": \"%s\"" +
                "}", DEFAULT_NAME);
    }

    public String buildUpdateGenreJsonIT() {
        return String.format("{" +
                "    \"id\": \"%d\"," +
                "    \"name\": \"%s\"" +
                "}", UPDATE_ID, DEFAULT_GENRE_NAME_UPDATE);
    }

    public String buildUpdateGenreJson() {
        return String.format("{" +
                "    \"id\": \"%d\"," +
                "    \"name\": \"%s\"" +
                "}", DEFAULT_ID, DEFAULT_NAME);
    }

    public String buildDefaultItemJson() {
        return String.format("{" +
                "    \"bookId\": %d" +
                "}", DEFAULT_ID);
    }

    public String buildUpdateItemJson() {
        return String.format("{" +
                "    \"id\": %d," +
                "    \"bookId\": %d" +
                "}", DEFAULT_ID, DEFAULT_ID);
    }

    public String buildUpdateUserJson() {
        return String.format("    {" +
                        "        \"id\": %d," +
                        "            \"isBlocked\": %b," +
                        "            \"username\": \"%s\"," +
                        "            \"password\": \"%s\"," +
                        "            \"role\": {" +
                        "        \"id\": %d," +
                        "                \"role\": \"%s\"" +
                        "    }," +
                        "        \"profile\": null," +
                        "        \"items\": null" +
                        "    }", DEFAULT_ID, true, DEFAULT_USERNAME, DEFAULT_PASSWORD,
                DEFAULT_ROLE_ID, DEFAULT_ROLE_NAME);
    }

    public String buildDefaultUserJson() {
        return String.format("    {" +
                "            \"username\": \"%s\"," +
                "            \"password\": \"%s\"," +
                "            \"role\": {" +
                "        \"id\": %d," +
                "                \"role\": \"%s\"" +
                "    }," +
                "        \"profile\": null," +
                "        \"items\": null" +
                "    }", DEFAULT_NAME, DEFAULT_PASSWORD, DEFAULT_ROLE_ID, DEFAULT_ROLE_NAME);
    }

    public String buildValidToken() {
        Claims claims = Jwts.claims().setSubject(DEFAULT_USERNAME);

        java.util.Date now = new java.util.Date();
        java.util.Date exp = new java.util.Date(System.currentTimeMillis() + 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

}
