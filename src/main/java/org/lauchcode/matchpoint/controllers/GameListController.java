package org.lauchcode.matchpoint.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;


@Controller
@RequestMapping(value = "list-of-games")
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

        tableChoices.put("gameName", GameData.get());
        tableChoices.put("gameGenre", JobData.getAllLocations());
        tableChoices.put("gameLengthInMinutes", JobData.getAllPositionTypes());
        tableChoices.put("maxPlayers", JobData.getAllCoreCompetency());
        tableChoices.put("maxPlayers", JobData.getAllCoreCompetency());
        tableChoices.put("all", "View All");
    }
}
