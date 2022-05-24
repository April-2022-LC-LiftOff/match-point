package org.lauchcode.matchpoint.controllers;

import org.lauchcode.matchpoint.models.User;
import org.lauchcode.matchpoint.models.data.UserRepository;
import org.lauchcode.matchpoint.models.dto.LoginFormDTO;
import org.lauchcode.matchpoint.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
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

//    @GetMapping("/register")
//    @ResponseBody
//    public String displayRegistrationForm(){
//        return "<html>" +
//                "<body>" +
//                "<h1>User Registration</h1>" +
//                "<form action='/register' method='post'>" +
//                "<label> Username <input type='text' name='username' > </label>" +
//                "<label> Password <input type='password' name='password' > </label>" +
//                "<label> Confirm Password <input type='password' name='verifyPassword' > </label>" +
//                "<input type='submit' value='Register' >" +
//                "</form>" +
//                "</body>" +
//                "</html>";
//    }

    @PostMapping("/register")
    @CrossOrigin
    @ResponseBody
    public User processRegistrationForm(@RequestBody RegisterFormDTO registerFormDTO, HttpServletRequest request) throws Exception {

        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());
        if (existingUser != null){
            throw new Exception("A user with that username already exists!");
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if(!password.equals(verifyPassword)){
            throw new Exception("Passwords do not match!");
        }

        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
        User savedUser = userRepository.save(newUser);
        setUserSessionKey(request.getSession(), newUser);

        return savedUser;
    }

    @GetMapping("/login")
    @ResponseBody
    public String displayLoginForm(){
        return "<html>" +
                "<body>" +
                "<h1>User Login</h1>" +
                "<form action = '/login' method = 'post'>" +
                "<label> Username <input type='text' name='username' > </label>" +
                "<label> Password <input type='password' name='password' > </label>" +
                "<input type='submit' value='Log In' >" +
                "</form>" +
                "</body>" +
                "</html>";
    }

    @PostMapping("/login")
    @ResponseBody
    public String processLoginOFrm(@ModelAttribute LoginFormDTO loginFormDTO, HttpServletRequest request){

        User aUser = userRepository.findByUsername(loginFormDTO.getUsername());
        if(aUser == null){
            return "The provided username and/ or password does not match our records";
        }

        String password = loginFormDTO.getPassword();
        if(!aUser.isMatchingPassword(password)){
            return "The provided username and/ or password does not match our records";
        }

        setUserSessionKey(request.getSession(), aUser);
        return "User " + aUser.getUsername() + " successfully logged in!";
    }

    @GetMapping("/signout")
    @ResponseBody
    public String signout(HttpServletRequest request){
        request.getSession().invalidate();
        return "User successfully signed out!";
    }

}
