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


    public Game findLastGame(int roomId){
        for ( int id : gameStore.keySet()){
            if (id == roomId){
                int lastIdx = gameStore.get(roomId).size()-1;
                return gameStore.get(roomId).get(lastIdx);
            }
        }
        return new Game(); // 추후 예외처리?
    }


}
