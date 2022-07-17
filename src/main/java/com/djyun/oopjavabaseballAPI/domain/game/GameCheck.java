package com.djyun.oopjavabaseballAPI.domain.game;


import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class GameCheck {
    private static final Map<Integer, Game> store = new HashMap<>();
    public boolean isPossible(int roomId){
        // 로직
        return true;
    }

    /*
    public void saveRemainingCnt(int roodId){
    }


    public List<Game> retrieveAll(){

    }
     */
}
