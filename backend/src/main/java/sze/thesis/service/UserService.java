package sze.thesis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import sze.thesis.model.CreateUserDto;
import sze.thesis.model.UserResponseDto;
import sze.thesis.persistence.entity.User;
import sze.thesis.persistence.repository.UserRepository;
import sze.thesis.service.mapper.UserMapper;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public UserResponseDto findUserById(long id) throws Exception {
        User maybeUser = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User with " + id + " id not found"));
        return userMapper.mapUserEntityToUserResponseDto(maybeUser);
    }


    public UserResponseDto findUserByEmail(String email) throws Exception {
        User maybeUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("User with " + email + " email address not found"));
        return userMapper.mapUserEntityToUserResponseDto(maybeUser);
    }

    public List<UserResponseDto> findAllUser() throws Exception {
        List<User> userList = userRepository.findAll();
        if(userList != null) {
            return userMapper.userResponseDtoList(userList);
        } else {
            throw new Exception("User list is empty.");
        }
    }

    public Optional<User> updateUserPersonalData(String email, CreateUserDto createUserDto) throws Exception {
        User maybeUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("User with " + email + " email address not found"));
        return Optional.of(userRepository.save(updateUserPersonalData(maybeUser, createUserDto)));
    }

    private User updateUserPersonalData(User current, CreateUserDto createUserdto) {
        current.setFirstName(createUserdto.getFirstName());
        current.setLastName(createUserdto.getLastName());
        current.setEmail(createUserdto.getEmail());
        current.setPhone(createUserdto.getPhone());
        current.setCity(createUserdto.getCity());
        current.setAddress(createUserdto.getAddress());
        return current;
    }
    public User getLoggedUser() {
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
    }
}
