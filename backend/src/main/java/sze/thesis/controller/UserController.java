package sze.thesis.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sze.thesis.model.CreateUserDto;
import sze.thesis.model.UserDto;
import sze.thesis.model.UserResponseDto;
import sze.thesis.persistence.entity.Role;
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
    public ResponseEntity<UserResponseDto> register(@RequestBody CreateUserDto dto) throws Exception {
        return ResponseEntity.ok(userService.register(dto));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(userService.authenticate(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> profile() {
        return ResponseEntity.ok(new UserDto(userService.getLoggedUser()));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/user/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponseDto> findUserByEmail(@PathVariable("email") String email) throws Exception {
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> findAll() throws Exception {
        if(userService.getLoggedUser().getRole().equals(Role.USER)){
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(userService.findAllUser());
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/update/{email}")
    public void updateUserPersonalData(@PathVariable("email") String email,
                                       @RequestBody CreateUserDto createUserDto) throws Exception {
            userService.updateUserPersonalData(email, createUserDto);
    }
}
