package org.lauchcode.matchpoint.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/public")
    public String publicTest(){
        return "private content";
    }

    @GetMapping("/private")
    public String privateTest(){
        return "private content";
    }
}
