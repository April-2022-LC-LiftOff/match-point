package org.lauchcode.matchpoint.models.dto;

public class GameDTO {
    private Integer gameId;
    private String gameName;
    private String gameDescription;
    private Integer minPlayers;
    private Integer maxPlayers;
    private String gameGenre;
    private int gameLengthInMinutes;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
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

    public void setMinPlayers(Integer minPlayers) {
        this.minPlayers = minPlayers;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getGameGenre() {
        return gameGenre;
    }

    public void setGameGenre(String gameGenre) {
        this.gameGenre = gameGenre;
    }

    public int getGameLengthInMinutes() {
        return gameLengthInMinutes;
    }

    public void setGameLengthInMinutes(int gameLengthInMinutes) {
        this.gameLengthInMinutes = gameLengthInMinutes;
    }
}
