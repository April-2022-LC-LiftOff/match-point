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
import java.util.Objects;
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

        // use the gameId from the front end to search the game repo for a match
        List<Game> gameByExternalId = gameRepository.getGameByExternalId(gameDTO.getExternalGameId());

        //check to see if gameByExternalId is empty which would indicate no match in table
        boolean gameNotInTable = gameByExternalId.isEmpty();

        //initialize a new User Game
        Game newUserGame = null;

        //if gameNotInTable came back true, use the game details to create a new game object
        if(gameNotInTable) {
        Game newGame = new Game(gameDTO.getExternalGameId(),gameDTO.getGameName(), gameDTO.getMinPlayers(), gameDTO.getMaxPlayers(), gameDTO.getMinPlaytime(), gameDTO.getMaxPlaytime());

        //save the game to the game repo
        newUserGame= gameRepository.save(newGame);

        }else{
            //this gets the game from position zero in the list of games that were retrieved from the DB, there should only be one match (ie position 0)
            newUserGame=gameByExternalId.get(0);
        }

        //get a list of all the users games
        List<Game> userGames = user.getGames();

        //initialize a boolean for whether the user already has the game in their library
        boolean userHasGame = false;

        //loop through the games in the user's library to check for a match, if yes, boolean changes to true, loop stops
        for(Game userGame : userGames){
            if(newUserGame.getExternalGameId().equalsIgnoreCase(userGame.getExternalGameId())){
                userHasGame = true;
                break;
            }
        }
        //if user does not have the game, adds the new game to the user's library
        if(!userHasGame){
            userGames.add(newUserGame);
            userRepository.save(user);
        } else {
            System.out.println("Game already in library.");
        }

        //tells ResponseEntity to build and send back OK response to indicate success
        return ResponseEntity.ok().build();
    }

    @PostMapping("/games/delete")
    @ResponseBody
    public ResponseEntity<?> processDeleteFromLibrary(@RequestBody GameDTO gameDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<User> optionalUser = userRepository.findByUsername(userDetails.getUsername());
        User user = optionalUser.orElseThrow();
        List<Game> userGames = user.getGames();

        //List<Game> gameByExternalId = gameRepository.getGameByExternalId(gameDTO.getExternalGameId());

        boolean userHasGame = false;

        for(Game userGame : userGames){
            if(gameDTO.getExternalGameId().equalsIgnoreCase(userGame.getExternalGameId())){
                userHasGame = true;
                break;
            }
        }

        if(userHasGame){
            userGames.removeIf(game -> Objects.equals(game.getExternalGameId(), gameDTO.getExternalGameId()));
            System.out.println("Game deleted from library.");
            userRepository.save(user);
        }
        return ResponseEntity.ok().build();
    }
}
