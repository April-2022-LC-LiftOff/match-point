package org.lauchcode.matchpoint.controllers;

import org.lauchcode.matchpoint.models.User;
import org.lauchcode.matchpoint.models.data.UserRepository;
import org.lauchcode.matchpoint.models.dto.RegisterRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/public")
    public String publicTest(){
        return "private content";
    }

    @GetMapping("/private")
    public String privateTest(){
        return "private content";
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId){
        userRepository.deleteById(userId);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }
}
