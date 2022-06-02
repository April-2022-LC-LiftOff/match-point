package org.lauchcode.matchpoint.controllers;

import org.lauchcode.matchpoint.models.data.GameRepository;
import org.lauchcode.matchpoint.models.dto.GameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/auth")
public class GameController {

    @Autowired
    GameRepository gameRepository;
//
//    @PostMapping("/games/add-to-library")
//    public ResponseEntity<?> processRegistrationForm(@Valid @RequestBody GameDTO gameDTO){
//        if (gameRepository.getGameById(gameDTO.getGameId())){
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Username is already taken."));
//        }
//        if (userRepository.existsByEmail(registerFormDTO.getEmail())){
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Email is already in use."));
//        }
//        User user = new User(
//                registerFormDTO.getUsername(),
//                registerFormDTO.getEmail(),
//                passwordEncoder.encode(registerFormDTO.getPassword())
//        );
//        Set<String> strRoles = registerFormDTO.getRole();
//        Set<Role> roles = new HashSet<>();
//        if (strRoles == null){
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "admin":
//                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//                        break;
//                    default:
//                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                }
//            });
//        }
//        user.setRoles(roles);
//        userRepository.save(user);
//        return ResponseEntity.ok(new MessageResponse("User successfully registered!"));
//    }

}
