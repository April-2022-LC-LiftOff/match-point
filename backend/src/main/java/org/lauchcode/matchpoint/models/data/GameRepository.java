package org.lauchcode.matchpoint.models.data;

import org.lauchcode.matchpoint.models.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

    public interface GameRepository extends CrudRepository<Game, String> {
        @Query(value="select * from game g where g.external_game_id= :externalGameId", nativeQuery=true)
        List<Game> getGameByExternalId(String externalGameId);
    }
