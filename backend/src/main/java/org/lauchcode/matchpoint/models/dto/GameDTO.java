package org.lauchcode.matchpoint.models.dto;

import javax.validation.constraints.NotNull;

public class GameDTO {
    @NotNull
    private String externalGameId;

    @NotNull
    private String gameName;

    @NotNull
    private int minPlayers;

    @NotNull
    private int maxPlayers;

    @NotNull
    private int minPlaytime;

    @NotNull
    private int maxPlaytime;

    public String getExternalGameId() {
        return externalGameId;
    }

    public void setExternalGameId(String externalGameId) {
        this.externalGameId = externalGameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMinPlaytime() {
        return minPlaytime;
    }

    public void setMinPlaytime(int minPlaytime) {
        this.minPlaytime = minPlaytime;
    }

    public int getMaxPlaytime() {
        return maxPlaytime;
    }

    public void setMaxPlaytime(int maxPlaytime) {
        this.maxPlaytime = maxPlaytime;
    }
}
