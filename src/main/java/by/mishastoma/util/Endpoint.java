package by.mishastoma.util;

public class Endpoint {

    private Endpoint() {

    }

    public static final String USERS_URL = "/users/**";
    public static final String BOOKS_URL = "/books/**";
    public static final String ITEMS_URL = "/items/**";
    public static final String GENRES_URL = "/genres/**";
    public static final String AUTHORS_URL = "/authors/**";
    public static final String ADMIN = "ROLE_admin";
    public static final String LIBRARIAN = "ROLE_librarian";
    public static final String SIGN_IN = "signin";
    public static final String SIGN_UP = "signup";
}
