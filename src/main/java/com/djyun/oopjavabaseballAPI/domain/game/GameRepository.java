package com.djyun.oopjavabaseballAPI.domain.game;


import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class GameRepository {
    private static final Map<Integer, ArrayList<Game>> gameStore = new LinkedHashMap<>();

    public boolean isPossible(int roomId){
        // remainingCount <=10
        // correct
        return true;
    }

    public ArrayList<Game> retrieveAll(int roomId){
        if(gameStore.containsKey(roomId)){
            return gameStore.get(roomId);
        }
        return null; // 결과가 없는 경우 (추후 예외처리)
    }

}
