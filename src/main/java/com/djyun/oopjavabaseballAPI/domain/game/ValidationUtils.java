package com.djyun.oopjavabaseballAPI.domain.game;

import java.util.List;

public class ValidationUtils {
    public boolean checkRange(List<Integer> userAnswer){
        for (int i = 0; i < userAnswer.size(); i++) {
            if (1>userAnswer.get(i) || userAnswer.get(i)>9){
                return false; // 예외처리
            }
        }
        return true;
    }

    public boolean unDuplicated(List<Integer> userAnswer){
        for (int i = 0; i < userAnswer.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (userAnswer.get(i)==userAnswer.get(j)){
                    return false; // 예외처리
                }
            }
        }
        return true;
    }


    public boolean countDigits(List<Integer> userAnswer) {
        if (userAnswer.size()==3){
            return true;
        }
        return false;
    }
}
