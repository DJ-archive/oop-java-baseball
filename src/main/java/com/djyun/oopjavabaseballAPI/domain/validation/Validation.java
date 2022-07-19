package com.djyun.oopjavabaseballAPI.domain.validation;

import com.djyun.oopjavabaseballAPI.domain.user.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class Validation {
    private final UserDao userDao;

    public boolean checkNumValid(Integer answer){
        List<Integer> userAnswer = convertIntList(answer);
        boolean checkRange = checkRange(userAnswer);
        boolean unDuplicated = unDuplicated(userAnswer);
        boolean countDigits = countDigits(userAnswer);
        if (checkRange && unDuplicated && countDigits){
            return true;
        }
        return false;
    }

    public boolean checkNumValid(int roomId, Integer answer){
        List<Integer> userAnswer = convertIntList(answer);
        boolean checkRange = checkRange(userAnswer);
        boolean unDuplicated = unDuplicated(userAnswer);
        boolean countDigits = countDigits(userAnswer);
        if (checkRange && unDuplicated && countDigits){
            return true;
        }else {
            return false;
        }
    }

    public boolean checkRemainingCnt(int roomId) {
        if ((userDao.findUserById(roomId).getRemainingCount()>=1)){
            return true;
        } else {
            return false;
        }
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
                return false;
            }
        }
        return true;
    }

    public boolean unDuplicated(List<Integer> userAnswer){
        for (int i = 0; i < userAnswer.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (userAnswer.get(i)==userAnswer.get(j)){
                    return false;
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
