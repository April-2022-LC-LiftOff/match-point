package org.lauchcode.matchpoint.models;

import java.util.Objects;

public class Game {

    private Integer gameId;
    private static Integer nextId=1;
    private String gameName;
    private String gameDescription;
    private Integer minPlayers;
    private Integer maxPlayers;
    private String gameGenre;
    private int gameLengthInMinutes;

    // Initialize a unique ID.
    public Game() {
        gameId = nextId;
        nextId++;
    }

    public Game(int gameId, String gameName, String gameDescription, int minPlayers, int maxPlayers, String gameGenre, int gameLengthInMinutes) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.gameGenre = gameGenre;
        this.gameLengthInMinutes = gameLengthInMinutes;
    }


//    // Custom toString method.
//    @Override
//    public String toString(){
//        String output = "";
//        if (gameName.equals("")){
//            gameName = "Data not available";
//        }
//        if (gameDescription.getValue().equals("") || gameDescription.getValue() == null){
//            gameDescription.setValue("Data not available");
//        }
//        if (minPlayers.getValue().equals("") || minPlayers.getValue() == null){
//            minPlayers.setValue("Data not available");
//        }
//        if (maxPlayers.getValue().equals("") || maxPlayers.getValue() == null){
//            maxPlayers.setValue("Data not available");
//        }
//        if (gameGenre.getValue().equals("") || gameGenre.getValue() == null){
//            gameGenre.setValue("Data not available");
//        }
//
//        if (gameLengthInMinutes.getValue().equals("") || gameLengthInMinutes.getValue() == null){
//            gameLengthInMinutes.setValue("Data not available");
//        }
//
//        output = String.format("\n Game ID: %d\n" +
//                "Name: %s\n" +
//                "Description: %s\n" +
//                "Min No. of Players: %s\n" +
//                "Max No. of Players: %s\n" +
//                "Game Length(mins): %s\n", gameId, gameName, gameDescription, minPlayers, maxPlayers, gameLengthInMinutes);
//        return output;
//    }

    // Custom equals and hashCode methods. Two Job objects are "equal" when their id fields match.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return gameId == game.gameId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId);
    }







    public Integer getGame_id() {
        return gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public Integer getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getGameGenre() {
        return gameGenre;
    }

    public void setGameGenre(String gameGenre) {
        this.gameGenre = gameGenre;
    }

    public Integer getGameLengthInMinutes() {
        return gameLengthInMinutes;
    }

    public void setGameLengthInMinutes(int gameLengthInMinutes) {
        this.gameLengthInMinutes = gameLengthInMinutes;
    }
}
