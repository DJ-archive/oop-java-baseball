package com.djyun.oopjavabaseballAPI.domain.game;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ValidationUtils {

    /**
     * TODO 게임 횟수 검증
     */

    public boolean checkValidation(Integer answer){
        List<Integer> userAnswer = convertIntList(answer);
        boolean checkRange = checkRange(userAnswer);
        boolean unDuplicated = unDuplicated(userAnswer);
        boolean countDigits = countDigits(userAnswer);
        if (!(checkRange && unDuplicated && countDigits)){
            return false;
        }
        return true;
    }

    public List<Integer> convertIntList(Integer answer){
        if (answer != null){
            List<Integer> userAnswerLi = new ArrayList<>();
            String userAnswer = Integer.toString(answer);
            for (String num : userAnswer.split("")){
                userAnswerLi.add(Integer.valueOf(num));
            }
            return userAnswerLi;
        }
        return new ArrayList<>();
    }

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
