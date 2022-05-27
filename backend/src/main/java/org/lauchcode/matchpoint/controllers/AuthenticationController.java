package org.lauchcode.matchpoint.controllers;

import org.lauchcode.matchpoint.models.ERole;
import org.lauchcode.matchpoint.models.Role;
import org.lauchcode.matchpoint.models.User;
import org.lauchcode.matchpoint.models.data.RoleRepository;
import org.lauchcode.matchpoint.models.data.UserRepository;
import org.lauchcode.matchpoint.models.dto.JwtResponse;
import org.lauchcode.matchpoint.models.dto.LoginRequestDTO;
import org.lauchcode.matchpoint.models.dto.MessageResponse;
import org.lauchcode.matchpoint.models.dto.RegisterRequestDTO;
import org.lauchcode.matchpoint.security.jwt.JwtUtils;
import org.lauchcode.matchpoint.security.services.UserDetailsImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired AuthenticationManager authenticationManager;
    @Autowired UserRepository userRepository;
    @Autowired RoleRepository roleRepository;
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired JwtUtils jwtUtils;

//    private static final String userSessionKey = "user";
//
//    public User getUserFromSession(HttpSession session){
//
//        Integer userId = (Integer) session.getAttribute(userSessionKey);
//        if (userId == null){
////            return null;
//            return new User("No ID", "No ID");
//        }
//
//        Optional<User> user = userRepository.findById(userId);
//        if (user.isEmpty()){
////            return null;
//            return new User("User not found", "User Not Found");
//        }
//
//        return user.get();
//
//    }
//
//    public void setUserSessionKey(HttpSession session, User user){
//        session.setAttribute(userSessionKey, user.getId());
//    }

//    @GetMapping("/user")
//    public ResponseEntity<User> getCurrentUser(HttpServletRequest request){
//        User currentUser = getUserFromSession(request.getSession());
//        return new ResponseEntity<>(currentUser, HttpStatus.OK);
//    }

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
    public ResponseEntity<?> processRegistrationForm(@Valid @RequestBody RegisterRequestDTO registerFormDTO){
        if (userRepository.existsByUsername(registerFormDTO.getUsername())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken."));
        }
        if (userRepository.existsByEmail(registerFormDTO.getEmail())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use."));
        }
        User user = new User(
                registerFormDTO.getUsername(),
                registerFormDTO.getEmail(),
                passwordEncoder.encode(registerFormDTO.getPassword())
                );
        Set<String> strRoles = registerFormDTO.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null){
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User successfully registered!"));
    }

//    @GetMapping("/test")
//    public ResponseEntity<?> getTest(){
//        Map<String, String> map = new HashMap<>();
//        map.put("message", "Connected to the backend!");
//        return new ResponseEntity<>(map, HttpStatus.OK);
//    }
//
//    @PostMapping("/test")
//    public ResponseEntity<?> postTest(){
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> processLoginForm(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwt(authentication);

        UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles
        ));
    }


//    @GetMapping("/login")
//    @ResponseBody
//    public String displayLoginForm(){
//        return "login";
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

//    @GetMapping("/signout")
//    @ResponseBody
//    public String signout(HttpServletRequest request){
//        request.getSession().invalidate();
//        return "User successfully signed out!";
//    }

}
