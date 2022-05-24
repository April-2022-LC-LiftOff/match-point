package org.lauchcode.matchpoint.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lauchcode.matchpoint.models.Game;
import org.lauchcode.matchpoint.models.GameData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;


@Controller
@RequestMapping(value = "list")
public class GameListController {
    static HashMap<String, String> columnChoices = new HashMap<>();
    static HashMap<String, Object> tableChoices = new HashMap<>();

    public GameListController() {
        columnChoices.put("all", "All");
        columnChoices.put("gameName", "Game Name");
        columnChoices.put("gameGenre", "Genre");
        columnChoices.put("gameLengthInMinutes", "Length of Play");
        columnChoices.put("maxPlayers", "Max Players");
        columnChoices.put("minPlayers", "Min Players");

        tableChoices.put("gameName", GameData.getAllGameNames());
        tableChoices.put("gameGenre", GameData.getAllGameGenres());
        tableChoices.put("gameLengthInMinutes", GameData.getAllGameLengthInMinutes());
        tableChoices.put("maxPlayers", GameData.getAllMaxPlayers());
        tableChoices.put("minPlayers", GameData.getAllMinPlayers());
        tableChoices.put("all", "View All");
    }

    @GetMapping(value = "")
    public String list(Model model) {
        model.addAttribute("columns", columnChoices);
        model.addAttribute("tableChoices", tableChoices);
        model.addAttribute("gameName", GameData.getAllGameNames());
        model.addAttribute("gameGenre", GameData.getAllGameGenres());
        model.addAttribute("gameLengthInMinutes", GameData.getAllGameLengthInMinutes());
        model.addAttribute("maxPlayers", GameData.getAllMaxPlayers());
        model.addAttribute("minPlayers", GameData.getAllMinPlayers());
        model.addAttribute("all", "View All");
        return "list";
    }

    @GetMapping(value = "games")
    public String listJobsByColumnAndValue(Model model, @RequestParam String column, @RequestParam(required = false) String value) {
        ArrayList<Game> games;
        if (column.equals("all")){
            games = GameData.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
            games = GameData.findByColumnAndValue(column, value);
            model.addAttribute("title", "Jobs with " + columnChoices.get(column) + ": " + value);
        }
        model.addAttribute("games", games);

        return "list-games";
    }
}
