package by.mishastoma.service.impl;

import by.mishastoma.exception.UserNotFoundException;
import by.mishastoma.model.dao.UserDao;
import by.mishastoma.model.entity.Role;
import by.mishastoma.model.entity.User;
import by.mishastoma.service.UserService;
import by.mishastoma.util.JwtUtils;
import by.mishastoma.web.dto.RoleDto;
import by.mishastoma.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final JwtUtils jwtUtils;
    private final UserDao userDao;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDto save(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return modelMapper.map(userDao.save(user), UserDto.class);
    }

    @Override
    @Transactional
    public void delete(Serializable id) {
        User user = userDao.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userDao.delete(user);
    }

    @Override
    @Transactional
    public UserDto findById(Serializable id) {
        User userEntity = userDao.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    @Transactional
    public void update(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userDao.update(user);
    }

    @Override
    public UserDto findUserByIdCriteria(Serializable id) {
        User userEntity = userDao.findByIdCriteria(id).orElseThrow(() -> new UserNotFoundException(id));
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    @Transactional
    public UserDto findUserByUsername(String username) {
        User userEntity = userDao.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public List<UserDto> findUsersWithRole(RoleDto role) {
        List<User> userEntities = userDao.findByRole(modelMapper.map(role, Role.class));
        return userEntities.stream().map(x -> modelMapper.map(x, UserDto.class)).toList();
    }

    @Override
    public String signIn(UserDto user) {
        User foundUser = userDao.findByUsername(user.getUsername()).orElseThrow(()
                -> new UserNotFoundException(user.getUsername()));
        if (foundUser.getPassword().equals(user.getPassword())) {
            return jwtUtils.generateJwtToken(foundUser);
        }
        return null;
    }

    @Override
    public boolean tryLogIn(String username, String password) {
        Optional<User> user = userDao.findByUsername(username);
        if (user.isEmpty()) {
            log.info("Fail");
            log.info(username);
            log.info(password);
            return false;
        } else {
            log.info(user.get().getPassword());
            log.info(passwordEncoder.encode(password));
            return user.get().getPassword().equals(passwordEncoder.encode(password));
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }
}
