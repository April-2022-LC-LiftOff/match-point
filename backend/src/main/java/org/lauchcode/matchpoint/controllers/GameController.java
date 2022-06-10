package org.lauchcode.matchpoint.controllers;

import org.lauchcode.matchpoint.models.Game;
import org.lauchcode.matchpoint.models.User;
import org.lauchcode.matchpoint.models.data.*;
import org.lauchcode.matchpoint.models.dto.GameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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


    @GetMapping("/games")
    public ResponseEntity<Iterable<Game>> loadUserGames() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> optionalUser = userRepository.findByUsername(userDetails.getUsername());
        User user = optionalUser.orElseThrow();

        return ResponseEntity.status(HttpStatus.OK).body(user.getGames());
    }

    @PostMapping("/games")
    @ResponseBody
    public ResponseEntity<?> processAddToLibrary(@RequestBody GameDTO gameDTO) {

        //getting user information
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> optionalUser = userRepository.findByUsername(userDetails.getUsername());
        User user = optionalUser.orElseThrow();
        user.getId();

        
        List<Game> gameByExternalId = gameRepository.getGameByExternalId(gameDTO.getExternalGameId());
        boolean gameNotInTable = gameByExternalId.isEmpty();

        Game newUserGame = null;

        if(gameNotInTable) {
        Game newGame = new Game(gameDTO.getExternalGameId(),gameDTO.getGameName(), gameDTO.getMinPlayers(), gameDTO.getMaxPlayers(), gameDTO.getMinPlaytime(), gameDTO.getMaxPlaytime());

        newUserGame= gameRepository.save(newGame);

        }else{
            newUserGame=gameByExternalId.get(0);
        }

        List<Game> userGames = user.getGames();
        boolean userHasGame = false;

        for(Game userGame : userGames){
            if(newUserGame.getExternalGameId().equalsIgnoreCase(userGame.getExternalGameId())){
                userHasGame = true;
                break;
            }
        }

        if(!userHasGame){
            userGames.add(newUserGame);
            userRepository.save(user);
        } else {
            System.out.println("Game already in library.");
        }
        return ResponseEntity.ok().build();
    }
}
