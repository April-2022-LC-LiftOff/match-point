package org.lauchcode.matchpoint.models;

import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class GameData {
    private static final String DATA_FILE = "TestGameDataFile.json";
    private static boolean isDataLoaded = false;

    private static ArrayList<Game> allGames;
    private static ArrayList<GameName> allGameNames = new ArrayList<>();
    private static ArrayList<MinPlayers> allMinPlayers = new ArrayList<>();
    private static ArrayList<MaxPlayers> allMaxPlayers = new ArrayList<>();
    private static ArrayList<GameGenre> allGameGenres = new ArrayList<>();
    private static ArrayList<GameLengthInMinutes> allGameLengthInMinutes = new ArrayList<>();


    /*
     * Fetch list of all job objects from loaded data,
     * without duplicates, then return a copy.
     */

    public static ArrayList<Game> findAll() {

        // load data, if not already loaded
        loadData();

        // Bonus mission; normal version returns allJobs
        return new ArrayList<>(allGames);
    }

    /*
     * Returns the results of searching the Game data by field and search term.
     *
     * For example, searching for game name  "Monopoly" will include results
     * with "Mr. Monopoly".
     *
     * @param column Game field that should be searched.
     * @param value Value of the field to search for.
     * @return List of all games matching the criteria.
     */

    public static ArrayList<Game> findByColumnAndValue(String column, String value) {

        // load data, if not already loaded
        loadData();

        ArrayList<Game> games = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return findAll();
        }

        if (column.equals("all")){
            games = findByValue(value);
            return games;
        }
        for (Game game : allGames) {

            String aValue = getFieldValue(game, column);

            if (aValue != null && aValue.toLowerCase().contains(value.toLowerCase())) {
                games.add(game);
            }
        }

        return games;
    }

    public static String getFieldValue(Game game, String fieldName){
        String theValue;
        if (fieldName.equals("game name")){
            theValue = game.getGameName();
        } else if (fieldName.equals("genre")){
            theValue = game.getGameGenre().toString();
        } else if (fieldName.equals("game length")){
            theValue = game.getGameLengthInMinutes().toString();
        } else if (fieldName.equals("max players")){
            theValue = game.getMaxPlayers().toString();
        } else {
            theValue = game.getMinPlayers().toString();
        }

        return theValue;
    }
/**
 * Search all Game fields for the given term.
 *
 * @param value The search term to look for.
 * @return      List of all games with at least one field containing the value.
 */

public static ArrayList<Game> findByValue(String value) {

    // load data, if not already loaded
    loadData();

    ArrayList<Game> games = new ArrayList<>();

    for (Game game : allGames) {

        if (game.getGameName().toLowerCase().contains(value.toLowerCase())) {
            games.add(game);
        } else if (game.getGameGenre().toString().toLowerCase().contains(value.toLowerCase())) {
            games.add(game);
        } else if (game.getGameLengthInMinutes().toString().toLowerCase().contains(value.toLowerCase())) {
            games.add(game);
        } else if (game.getMinPlayers().toString().toLowerCase().contains(value.toLowerCase())) {
            games.add(game);
        } else if (game.getMaxPlayers().toString().toLowerCase().contains(value.toLowerCase())) {
            games.add(game);
        }

    }

    return games;
}

//    private static Object findExistingObject(ArrayList list, String value){
//        for (Object item : list){
//            if (item.toString().toLowerCase().equals(value.toLowerCase())){
//                return item;
//            }
//        }
//        return null;
//    }

    /**
     * Read in data from a JSON file and store it in an ArrayList of Game objects.
     */
    private static void loadData() {
        ObjectMapper mapper = new ObjectMapper();
        // Only load data once
        if (isDataLoaded) {
            return;
        }

        try {
            Game newGame = mapper.readValue(new File("src/main/resources/TestGameDataFile.json"), Game.class);
            allGames.add(newGame);
            // flag the data as loaded, so we don't do it twice
            isDataLoaded = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Game> getAllGames() {
        return allGames;
    }

    public static void setAllGames(ArrayList<Game> allGames) {
        GameData.allGames = allGames;
    }

    public static ArrayList<GameName> getAllGameNames() {
        return allGameNames;
    }

    public static void setAllGameNames(ArrayList<GameName> allGameNames) {
        GameData.allGameNames = allGameNames;
    }

    public static ArrayList<MinPlayers> getAllMinPlayers() {
        return allMinPlayers;
    }

    public static void setAllMinPlayers(ArrayList<MinPlayers> allMinPlayers) {
        GameData.allMinPlayers = allMinPlayers;
    }

    public static ArrayList<MaxPlayers> getAllMaxPlayers() {
        return allMaxPlayers;
    }

    public static void setAllMaxPlayers(ArrayList<MaxPlayers> allMaxPlayers) {
        GameData.allMaxPlayers = allMaxPlayers;
    }

    public static ArrayList<GameGenre> getAllGameGenres() {
        return allGameGenres;
    }

    public static void setAllGameGenres(ArrayList<GameGenre> allGameGenres) {
        GameData.allGameGenres = allGameGenres;
    }

    public static ArrayList<GameLengthInMinutes> getAllGameLengthInMinutes() {
        return allGameLengthInMinutes;
    }

    public static void setAllGameLengthInMinutes(ArrayList<GameLengthInMinutes> allGameLengthInMinutes) {
        GameData.allGameLengthInMinutes = allGameLengthInMinutes;
    }
}