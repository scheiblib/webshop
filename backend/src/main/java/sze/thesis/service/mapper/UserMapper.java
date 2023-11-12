package sze.thesis.service.mapper;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import sze.thesis.model.CreateUserDto;
import sze.thesis.model.UserResponseDto;
import sze.thesis.persistence.entity.Role;
import sze.thesis.persistence.entity.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public User mapForUserRegister (CreateUserDto createUserDto){
        return User.builder()
                .firstName(createUserDto.getFirstName())
                .lastName(createUserDto.getLastName())
                .email(createUserDto.getEmail())
                .password(createUserDto.getPassword())
                .phone(createUserDto.getPhone())
                .city(createUserDto.getCity())
                .address(createUserDto.getAddress())
                .role(Role.USER)
                .build();
    }

    public UserResponseDto mapUserEntityToUserResponseDto (User user){
        return UserResponseDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .city(user.getCity())
                .address(user.getAddress())
                .role(Role.USER.toString())
                .build();
    }

    public List<UserResponseDto> userResponseDtoList(List<User> userList){
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for(User user : userList){
            userResponseDtoList.add(mapUserEntityToUserResponseDto(user));
        }
        return userResponseDtoList;
    }
}
