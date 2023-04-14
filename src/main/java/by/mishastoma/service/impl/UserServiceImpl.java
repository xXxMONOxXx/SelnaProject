package by.mishastoma.service.impl;

import by.mishastoma.exception.UniqueIdentifierIsTakenException;
import by.mishastoma.exception.UserNotFoundException;
import by.mishastoma.model.dao.UserDao;
import by.mishastoma.model.dao.impl.ProfileDao;
import by.mishastoma.model.entity.Profile;
import by.mishastoma.model.entity.Role;
import by.mishastoma.model.entity.User;
import by.mishastoma.service.UserService;
import by.mishastoma.util.JwtUtils;
import by.mishastoma.util.RoleUtil;
import by.mishastoma.web.dto.ProfileDto;
import by.mishastoma.web.dto.RoleDto;
import by.mishastoma.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final RoleUtil roleUtil;
    private final JwtUtils jwtUtils;
    private final UserDao userDao;
    private final ModelMapper modelMapper;
    private final ProfileDao profileDao;

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
    @Transactional
    public Page<UserDto> getAll(int pageNumber, int pageSize) {
        Page<User> users = userDao.getAll(pageNumber, pageSize);
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users.getContent()) {
            userDtos.add(modelMapper.map(user, UserDto.class));
        }
        return new PageImpl<>(userDtos, users.getPageable(), users.getTotalElements());
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
    @Transactional
    public void signUp(UserDto user) {
        areIdentifiersUnoccupied(user);
        registerUser(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }

    private void areIdentifiersUnoccupied(UserDto userDto) throws UniqueIdentifierIsTakenException {
        StringBuilder stringBuilder = new StringBuilder();
        if (userDao.findByUsername(userDto.getUsername()).isPresent()) {
            stringBuilder.append("Username is already taken. ");
        }
        if (userDao.findUserByEmail(userDto.getProfile().getEmail()).isPresent()) {
            stringBuilder.append("Email is already in use. ");
        }
        if (userDao.findUserByPhone(userDto.getProfile().getPhone()).isPresent()) {
            stringBuilder.append("Phone is already is use. ");
        }
        if (!stringBuilder.isEmpty()) {
            throw new UniqueIdentifierIsTakenException(stringBuilder.toString());
        }
    }

    private void registerUser(UserDto user) {
        user.setRole(roleUtil.createDefaultRole());
        user.setItems(null);
        user.setId(null);
        user.setIsBlocked(false);
        ProfileDto profile = user.getProfile();
        user.setProfile(null);
        User saveUser = userDao.save(modelMapper.map(user, User.class));
        profile.setUserId(saveUser.getId());
        profile.setUser(modelMapper.map(saveUser, UserDto.class));
        profileDao.save(modelMapper.map(profile, Profile.class));
    }
}
