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
        isCorrect();
        updatePlayerInfo(roomId);
        gameStore.saveGameById(roomId, gameResult);
    }

    public void updatePlayerInfo(int roomId) {
        User player = userStore.findUserById(roomId);
        int currentRemainingCnt = player.getRemainingCount();
        int currentAnswerCnt = player.getAnswerCount() + 1;
        player.setRemainingCount(currentRemainingCnt-1);
        player.setAnswerCount(currentAnswerCnt);
    }

    public void isCorrect() {
        if (game.getStrike() == 3 && game.getBall() == 0 && game.getOut() == 0){
            game.setCorrect(true);
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
