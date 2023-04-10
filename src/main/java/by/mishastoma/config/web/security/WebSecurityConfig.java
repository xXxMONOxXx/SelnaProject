package by.mishastoma.config.web.security;

import by.mishastoma.web.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan("by.mishastoma")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String USERS_URL = "/users/**";
    private static final String BOOKS_URL = "/books/**";
    private static final String ITEMS_URL = "/items/**";
    private static final String GENRES_URL = "/genres/**";
    private static final String AUTHORS_URL = "/authors/**";
    public static final String ADMIN = "ROLE_admin";
    public static final String LIBRARIAN = "ROLE_librarian";

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();

        httpSecurity.authorizeRequests()

                .antMatchers(USERS_URL + "signin", USERS_URL + "signup")
                .permitAll()

                .antMatchers(HttpMethod.GET, GENRES_URL, BOOKS_URL, AUTHORS_URL)
                .permitAll()

                .antMatchers(HttpMethod.PUT, GENRES_URL, BOOKS_URL, AUTHORS_URL, ITEMS_URL)
                .hasAnyAuthority(LIBRARIAN, ADMIN)

                .antMatchers(HttpMethod.DELETE, GENRES_URL, BOOKS_URL, AUTHORS_URL, ITEMS_URL)
                .hasAnyAuthority(LIBRARIAN, ADMIN)

                .antMatchers(HttpMethod.POST, GENRES_URL, BOOKS_URL, AUTHORS_URL, ITEMS_URL)
                .hasAnyAuthority(LIBRARIAN, ADMIN)

                .antMatchers(HttpMethod.PUT, USERS_URL)
                .hasAuthority(ADMIN)

                .anyRequest()
                .authenticated()

                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}