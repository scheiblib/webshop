package sze.thesis.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sze.thesis.model.CreateUserDto;
import sze.thesis.model.UserResponseDto;
import sze.thesis.persistence.entity.User;
import sze.thesis.service.UserService;
import sze.thesis.auth.AuthenticationRequest;
import sze.thesis.auth.AuthenticationResponse;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public UserResponseDto register(@RequestBody CreateUserDto dto) throws Exception {
        return userService.register(dto);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(userService.authenticate(request));
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/user/{email}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto findUserByEmail(@PathVariable("email") String email) throws Exception {
        return userService.findUserByEmail(email);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public List<User> findAll() throws Exception {
        return userService.findAllUser();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/update/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserPersonalData(@PathVariable("email") String email,
                                       @RequestBody CreateUserDto createUserDto) throws Exception {
            userService.updateUserPersonalData(email, createUserDto);
    }
}
