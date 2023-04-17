package by.mishastoma.util;


import lombok.experimental.UtilityClass;

@UtilityClass
public class Endpoint {
    public static final String USERS_URL = "/users/**";
    public static final String BOOKS_URL = "/books/**";
    public static final String ITEMS_URL = "/items/**";
    public static final String GENRES_URL = "/genres/**";
    public static final String AUTHORS_URL = "/authors/**";
    public static final String ADMIN = "ROLE_admin";
    public static final String LIBRARIAN = "ROLE_librarian";
    public static final String USER = "ROLE_user";
    public static final String SIGN_IN = "signin";
    public static final String SIGN_UP = "signup";
    public static final String ASSIGN = "assign/**";
    public static final String UNASSIGN = "unassign";
    public static final String GET_ALL = "browse";
    public static final String GET_BY_BOOK_ID = "bookid/**";
}
