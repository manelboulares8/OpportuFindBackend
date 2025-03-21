package com.project.restcontrollers;

import com.project.entities.Entrepreneur;
import com.project.entities.Etudiant;
import com.project.services.JwtService;
import com.project.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200") // Allow requests from Angular app

public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register/etudiant")
    public Etudiant registerEtudiant(@RequestBody Etudiant etudiant) {
        return userService.registerEtudiant(etudiant);
    }

    @PostMapping("/register/entrepreneur")
    public Entrepreneur registerEntrepreneur(@RequestBody Entrepreneur entrepreneur) {
        return userService.registerEntrepreneur(entrepreneur);
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        // Delegate the login process to the service to validate the user and generate the token
        String token = userService.login(email, password);
        return token;
    }
}
