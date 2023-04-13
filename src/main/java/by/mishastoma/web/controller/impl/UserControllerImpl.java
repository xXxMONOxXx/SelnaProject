package by.mishastoma.web.controller.impl;

import by.mishastoma.service.UserService;
import by.mishastoma.util.JwtToken;
import by.mishastoma.util.JwtUtils;
import by.mishastoma.web.controller.CrudController;
import by.mishastoma.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserControllerImpl implements CrudController<UserDto> {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private final UserService userService;

    @Override
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid UserDto user) {
        return ResponseEntity.status(HttpStatus.LOCKED).body("User signup instead of save");
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User was deleted");
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserDto user = userService.findById(id);
        UserDto currentUser = userService.findUserByUsername(username);
        if (!username.equals(user.getUsername()) && !currentUser.getRole().getRole().equals("admin")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(user);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UserDto user, @PathVariable Long id) {
        user.setId(id);
        userService.update(user);
        return ResponseEntity.status(HttpStatus.OK).body("User was updated");
    }

    @GetMapping
    public ResponseEntity<UserDto> findUserByUserName(@RequestParam @Size(max = 32,
            message = "Username maximum size is 32") String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        UserDto user = userService.findUserByUsername(username);
        UserDto currentUser = userService.findUserByUsername(currentUsername);
        if (!username.equals(user.getUsername()) && !currentUser.getRole().getRole().equals("admin")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody @Valid UserDto user) {
        try {
            Authentication authentication = authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtils.generateJwtToken(userDetails);
            return ResponseEntity.ok(new JwtToken(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid UserDto user) {
        userService.signUp(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User was successfully registered");
    }
}
