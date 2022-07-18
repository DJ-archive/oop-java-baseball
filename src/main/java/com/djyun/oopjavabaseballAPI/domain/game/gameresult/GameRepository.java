package com.djyun.oopjavabaseballAPI.domain.game.gameresult;


import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
@NoArgsConstructor
public class GameRepository {
    private static final  Map<Integer, ArrayList<Game>> gameStore = new LinkedHashMap<>();

    public ArrayList<Game> retrieveAll(int roomId){
        if(gameStore.containsKey(roomId)){
            return gameStore.get(roomId);
        }
        return null; // 결과가 없는 경우 (추후 예외처리)
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
