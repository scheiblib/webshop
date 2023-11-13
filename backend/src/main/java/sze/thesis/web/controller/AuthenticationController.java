//package sze.thesis.web.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import sze.thesis.auth.AuthenticationRequest;
//import sze.thesis.auth.AuthenticationResponse;
//import sze.thesis.service.AuthenticationService;
//import sze.thesis.auth.RegisterRequest;
//
//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//public class AuthenticationController {
//
//    private final AuthenticationService service;
//
//    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping("/register")
//    public String register(
//            @RequestBody RegisterRequest request
//    ) {
//        return ResponseEntity.ok(service.register(request));
//    }
//
//    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> authenticate(
//            @RequestBody AuthenticationRequest request
//    ) {
//        return ResponseEntity.ok(service.authenticate(request));
//    }
//}
