package com.djyun.oopjavabaseballAPI.domain.game;


import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class GameCheck {
    private static final Map<Integer, ArrayList<Game>> gameStore = new LinkedHashMap<>();
    public boolean isPossible(int roomId){
        // 로직
        return true;
    }




    public ArrayList<Game> retrieveAll(int roomId){
        if(gameStore.containsKey(roomId)){
            return gameStore.get(roomId);
        }
        return null; // 결과가 없는 경우 (추후 예외처리)
    }

}
