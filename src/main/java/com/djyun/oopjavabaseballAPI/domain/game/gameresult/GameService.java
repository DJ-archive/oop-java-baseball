package com.djyun.oopjavabaseballAPI.domain.game.gameresult;

import com.djyun.oopjavabaseballAPI.domain.game.NumberGenerator;
import com.djyun.oopjavabaseballAPI.domain.game.baseball.Status;
import com.djyun.oopjavabaseballAPI.domain.user.User;
import com.djyun.oopjavabaseballAPI.domain.user.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    private final Game game;
    private final UserRepository userStore;
    private final GameRepository gameStore;
    private final NumberGenerator numberGenerator;

    public void endGame(int roomId, Game gameResult){
        isCorrect(gameResult);
        updatePlayerInfo(roomId);
        gameStore.saveGameById(roomId, gameResult);
    }

    public void updatePlayerInfo(int roomId) {
        User player = userStore.findUserById(roomId);
        int currentRemainingCnt = player.getRemainingCount();
        int currentAnswerCnt = player.getAnswerCount() + 1;
        player.setRemainingCount(currentRemainingCnt-1);
        player.setAnswerCount(currentAnswerCnt);
        userStore.updateUser(roomId, player);
    }

    public void isCorrect(Game gameResult) {
        if (gameResult.getStrike() == 3 && gameResult.getBall() == 0 && gameResult.getOut() == 0){
            gameResult.setCorrect(true);
        }
    }

    public List<Integer> getRealAnswer(int roomId) {
        List<Integer> getRealAnswer = gameStore.findRealAnswerById(roomId);
        if (getRealAnswer == null){
            return numberGenerator.createRandomNum();
        }
        return getRealAnswer;
    }

}
