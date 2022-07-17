package com.djyun.oopjavabaseballAPI.domain.game;


import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;
@Repository
public class GameRepository {
    private static final Map<Integer, ArrayList<Game>> gameStore = new LinkedHashMap<>();

    public ArrayList<Game> retrieveAll(int roomId){
        if(gameStore.containsKey(roomId)){
            return gameStore.get(roomId);
        }
        return null; // 결과가 없는 경우 (추후 예외처리)
    }

    public Game makeNewGame(int roomId, List<Integer> realAnswer){
        Game game = new Game();
        game.setRealAnswer(realAnswer);
        ArrayList<Game> passedGames = gameStore.getOrDefault(roomId, new ArrayList<>());
        passedGames.add(game);
        gameStore.put(roomId, passedGames);
        return game;
    }

    public List<Integer> findRealAnswerByID(int roomId){
        if (gameStore.containsKey(roomId)){
            return gameStore.get(roomId).stream()
                    .findFirst()
                    .get().getRealAnswer();
        }else {
            return null;
        }
    }

}
