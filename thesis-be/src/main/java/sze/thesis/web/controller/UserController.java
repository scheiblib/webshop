package sze.thesis.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import sze.thesis.model.CreateUserDto;
import sze.thesis.model.UserResponseDto;
import sze.thesis.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/users")
public class UserController {

    private UserService userService;

    @GetMapping("/findUserByEmail/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto findUserByEmail(@PathVariable("email") String email) throws Exception {
        return userService.findUserByEmail(email);
    }
    @GetMapping("/findAllUsers")
    public List<UserResponseDto> findAll() throws Exception {
        return userService.findAllUser();
    }

    @PutMapping("/update/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserPersonalData(@PathVariable("email") String email,
                                       @RequestBody CreateUserDto createUserDto) throws Exception {
            userService.updateUserPersonalData(email, createUserDto);
    }
}
