package com.djyun.oopjavabaseballAPI.domain.game.baseball;

import com.djyun.oopjavabaseballAPI.domain.game.gameresult.Game;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class Balls {
    private final List<Ball> balls;
    public Balls(List<Integer> answer) {
        this.balls = mapBall(answer);
    }

    public List<Ball> mapBall(List<Integer> answer) {
        List<Ball> balls = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            balls.add(new Ball(i+1, answer.get(i)));
        }
        return balls;
    }
    public Game compare(List<Integer> answer){
        Game gameResult = new Game();
        Balls answerBalls = new Balls(answer);
        for (Ball ball : balls){
            Status gameStatus = answerBalls.compare(ball);
            gameResult.report(gameStatus);
        }
        return gameResult;
    }
    public Status compare(Ball answerBall) {
        return balls.stream()
                .map(ball -> ball.compare(answerBall))
                .filter(status-> status != Status.OUT)
                .findFirst()
                .orElse(Status.OUT);
    }

}
