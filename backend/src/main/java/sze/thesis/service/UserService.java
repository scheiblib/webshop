package sze.thesis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import sze.thesis.auth.AuthenticationRequest;
import sze.thesis.auth.AuthenticationResponse;
import sze.thesis.configuration.JwtService;
import sze.thesis.model.CreateUserDto;
import sze.thesis.model.UserResponseDto;
import sze.thesis.persistence.entity.Role;
import sze.thesis.persistence.entity.User;
import sze.thesis.persistence.repository.UserRepository;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserResponseDto register(CreateUserDto dto) throws Exception {
        String email = dto.getEmail();
        Optional<User> maybeUser = userRepository.findByEmail(email);

        if(maybeUser.isPresent()) {
            throw new Exception("User with " + email + " already exist.");
        }

        User user = new User(dto);
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        user.setRole(Role.USER);
        user = userRepository.save(user);
        return new UserResponseDto(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
    public UserResponseDto findUserById(long id) throws Exception {
        User maybeUser = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User with " + id + " id not found"));
        return new UserResponseDto(maybeUser);
    }


    public UserResponseDto findUserByEmail(String email) throws Exception {
        User maybeUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("User with " + email + " email address not found"));
        return new UserResponseDto(maybeUser);
    }

    public List<User> findAllUser() throws Exception {
        List<User> userList = userRepository.findAll();
        if(userList != null) {
            return userList;
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
