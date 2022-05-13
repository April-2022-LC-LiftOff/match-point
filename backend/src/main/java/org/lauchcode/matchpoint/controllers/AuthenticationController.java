package org.lauchcode.matchpoint.controllers;

import org.lauchcode.matchpoint.models.User;
import org.lauchcode.matchpoint.models.data.UserRepository;
import org.lauchcode.matchpoint.models.dto.LoginFormDTO;
import org.lauchcode.matchpoint.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session){

        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null){
            return null;
        }

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()){
            return null;
        }

        return user.get();

    }

    public void setUserSessionKey(HttpSession session, User user){
        session.setAttribute(userSessionKey, user.getId());
    }

    @GetMapping("/user")
    public ResponseEntity<User> getCurrentUser(HttpServletRequest request){
        User currentUser = getUserFromSession(request.getSession());
        return new ResponseEntity<User>(currentUser, HttpStatus.FOUND);
    }

    @GetMapping("/register")
    @ResponseBody
    public String displayRegistrationForm(){
        return "<html>" +
                "<body>" +
                "<h1>User Registration</h1>" +
                "<form action='/register' method='post'>" +
                "<label> Username <input type='text' name='username' > </label>" +
                "<label> Password <input type='password' name='password' > </label>" +
                "<label> Confirm Password <input type='password' name='verifyPassword' > </label>" +
                "<input type='submit' value='Register' >" +
                "</form>" +
                "</body>" +
                "</html>";
    }

//    @PostMapping("/register")
//    @ResponseBody
//    public String processRegistrationForm(@ModelAttribute RegisterFormDTO registerFormDTO, HttpServletRequest request){
//
//        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());
//        if (existingUser != null){
//            return "A user with that username already exists!";
//        }
//
//        String password = registerFormDTO.getPassword();
//        String verifyPassword = registerFormDTO.getVerifyPassword();
//        if(!password.equals(verifyPassword)){
//            return "Passwords do not match!";
//        }
//
//        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
//        userRepository.save(newUser);
//        setUserSessionKey(request.getSession(), newUser);
//
//        return "User " + newUser.getUsername() + " successfully created!";
//    }

    @PostMapping("/register")
    public ResponseEntity<?> processRegistrationForm(@RequestBody RegisterFormDTO registerFormDTO, HttpServletRequest request){
        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
        userRepository.save(newUser);
        setUserSessionKey(request.getSession(), newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/test")
    public ResponseEntity<?> getTest(){
        Map<String, String> map = new HashMap<>();
        map.put("message", "Connected to the backend!");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/test")
    public ResponseEntity<?> postTest(){
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @GetMapping("/login")
//    @ResponseBody
//    public String displayLoginForm(){
//        return "<html>" +
//                "<body>" +
//                "<h1>User Login</h1>" +
//                "<form action = '/login' method = 'post'>" +
//                "<label> Username <input type='text' name='username' > </label>" +
//                "<label> Password <input type='password' name='password' > </label>" +
//                "<input type='submit' value='Log In' >" +
//                "</form>" +
//                "</body>" +
//                "</html>";
//    }

//    @PostMapping("/login")
//    @ResponseBody
//    public String processLoginOFrm(@ModelAttribute LoginFormDTO loginFormDTO, HttpServletRequest request){
//
//        User aUser = userRepository.findByUsername(loginFormDTO.getUsername());
//        if(aUser == null){
//            return "The provided username and/ or password does not match our records";
//        }
//
//        String password = loginFormDTO.getPassword();
//        if(!aUser.isMatchingPassword(password)){
//            return "The provided username and/ or password does not match our records";
//        }
//
//        setUserSessionKey(request.getSession(), aUser);
//        return "User " + aUser.getUsername() + " successfully logged in!";
//    }

    @GetMapping("/signout")
    @ResponseBody
    public String signout(HttpServletRequest request){
        request.getSession().invalidate();
        return "User successfully signed out!";
    }

}
