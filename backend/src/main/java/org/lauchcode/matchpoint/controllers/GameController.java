package org.lauchcode.matchpoint.controllers;

import org.lauchcode.matchpoint.models.Game;
import org.lauchcode.matchpoint.models.data.*;
import org.lauchcode.matchpoint.models.dto.GameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    AuthenticationController authenticationController;

    @GetMapping
    public ResponseEntity<?> getGames() {
        return new ResponseEntity<>(gameRepository.findAll(), HttpStatus.OK);
    }

//    @PutMapping("/games")
//    @CrossOrigin
//    @ResponseBody
//    public ResponseEntity<?> processAddToLibrary(@RequestBody GameDTO gameDTO, HttpServletRequest request) {
//
//        gameRepository.findAll();
//
//        if(gameDTO.getExternalGameId() //IS NOT IN DB) {
//        Game newGame = new Game(gameDTO.getExternalGameId(),gameDTO.getGameName(), gameDTO.getDescription(), gameDTO.getMinPlayers(), gameDTO.getMaxPlayers(), gameDTO.getMinPlaytime(), gameDTO.getMaxPlaytime());
//        Game savedGame= gameRepository.save(newGame);
//        }
//
//        return ResponseEntity.ok(savedGame);
//    }

}
