package com.djyun.oopjavabaseballAPI.domain.game.gameresult;

import com.djyun.oopjavabaseballAPI.domain.game.baseball.NumberGenerator;
import com.djyun.oopjavabaseballAPI.domain.user.User;
import com.djyun.oopjavabaseballAPI.domain.user.UserDao;
import com.djyun.oopjavabaseballAPI.domain.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    private final Game game;
    private final UserDao userDao;
    private final GameDao gameDao;

    private final Validation validation;
    private final NumberGenerator numberGenerator;

    public void storeGameResult(int roomId, Game gameResult){
        updatePlayerInfo(roomId);
        gameDao.saveGameById(roomId, gameResult);
    }

    public void updatePlayerInfo(int roomId) {
        User player = userDao.findUserById(roomId);
        int currentRemainingCnt = player.getRemainingCount();
        int currentAnswerCnt = player.getAnswerCount() + 1;
        player.setRemainingCount(currentRemainingCnt-1);
        player.setAnswerCount(currentAnswerCnt);
        userDao.updateUser(roomId, player);
    }

    public void isCorrect(int roomId, Game gameResult) {
        if (gameResult.getStrike() == 3 && gameResult.getBall() == 0 && gameResult.getOut() == 0){
            userDao.findUserById(roomId).setCorrect(true);
        }
    }

    public boolean endGame(int roomId, Integer answer) {
        return !validation.checkNumValid(roomId, answer) || !validation.checkRemainingCnt(roomId) || userDao.findUserById(roomId).isCorrect();
    }

    public List<Integer> getRealAnswer(int roomId) {
        List<Integer> getRealAnswer = gameDao.findRealAnswerById(roomId);
        if (getRealAnswer == null){
            return numberGenerator.createRandomNum();
        }
        return getRealAnswer;
    }

}
