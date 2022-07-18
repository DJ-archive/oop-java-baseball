package com.djyun.oopjavabaseballAPI.domain.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberGenerator {
    public List<Integer> createRandomNum(){
        Random random = new Random();
        List<Integer> randomNumLi = new ArrayList<>();

        while(randomNumLi.size()<3){
            int randomInt = random.nextInt(9)+1;
            if (!randomNumLi.contains(randomInt)) {
                randomNumLi.add(randomInt);
            }
        }
        return randomNumLi;
    }
}
