package com.djyun.oopjavabaseballAPI.domain.game.gameresult;


import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class GameRepository {
    private static final  Map<Integer, ArrayList<Game>> gameStore = new LinkedHashMap<>();

    public void saveGameById(int roomId, Game gameResult){
        ArrayList<Game> passedGames = gameStore.getOrDefault(roomId, new ArrayList<Game>());
        passedGames.add(gameResult);
        gameStore.put(roomId, passedGames);
    }

    public ArrayList<Game> retrieveAll(int roomId){
        return new ArrayList<>(gameStore.get(roomId));
    }

}
