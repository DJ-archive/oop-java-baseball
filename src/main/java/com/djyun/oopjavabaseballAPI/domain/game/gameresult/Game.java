package com.djyun.oopjavabaseballAPI.domain.game.gameresult;

import com.djyun.oopjavabaseballAPI.domain.game.baseball.Status;
import com.djyun.oopjavabaseballAPI.domain.user.User;
import com.djyun.oopjavabaseballAPI.domain.user.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @JsonIgnore
    private final UserRepository userStore = new UserRepository();
    private boolean correct = false;
    private int strike;
    private int ball;
    private int out;

    public void report(Status gameStatus) {
        if (gameStatus == Status.STRIKE){
            ++ this.strike;
        }
        if (gameStatus == Status.BALL){
            ++ this.ball;
        }
        if (gameStatus == Status.OUT){
            ++ this.out;
        }
    }

    public void endGame(int roomId){
        isCorrect();
        updatePlayerInfo(roomId);
    }

    private void updatePlayerInfo(int roomId) {
        User player = userStore.findUserById(roomId);
        int currentRemainingCnt = player.getRemainingCount();
        int currentAnswerCnt = player.getAnswerCount() + 1;
        player.setRemainingCount(currentRemainingCnt-1);
        player.setAnswerCount(currentAnswerCnt);
    }

    private void isCorrect() {
        if (this.strike == 3 && this.ball == 0 && this.out == 0){
            this.correct = true;
        }
    }

}
