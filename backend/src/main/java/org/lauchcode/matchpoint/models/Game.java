package org.lauchcode.matchpoint.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

    @Entity
    public class Game {

        @Id
        @GeneratedValue
        private int gameId;

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

        @ManyToMany(mappedBy = "games")
        private List<User> user = new ArrayList<>();

        @ManyToMany(mappedBy = "games")
        private List<Event> event = new ArrayList<>();

        public Game(String externalGameId, String gameName, int minPlayers, int maxPlayers, int minPlaytime, int maxPlaytime) {
            this.externalGameId = externalGameId;
            this.gameName = gameName;
            this.minPlayers = minPlayers;
            this.maxPlayers = maxPlayers;
            this.minPlaytime = minPlaytime;
            this.maxPlaytime = maxPlaytime;
        }

        public Game(){};

        public int getGameId() {
            return gameId;
        }

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