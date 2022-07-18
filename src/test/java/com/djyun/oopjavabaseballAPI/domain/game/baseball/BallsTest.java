package com.djyun.oopjavabaseballAPI.domain.game.baseball;

import com.djyun.oopjavabaseballAPI.domain.game.Game;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BallsTest {

    /**
     * @Error
     * 하나씩 비교하므로, OUT일 경우 제외하고 비교한 뒤 STRIKE, BALL 에 해당 없을 경우 OUT 해야 함
     */

    @Test
    void 스트라이크_Balls(){
        Balls comBalls = new Balls(Arrays.asList(1,2,3));
        Ball userBall = new Ball(2,2);
        Status status = comBalls.compare(userBall);
        Assertions.assertThat(status).isEqualTo(Status.STRIKE);
    }

    @Test
    void 볼_Balls(){
        Balls comBalls = new Balls(Arrays.asList(1,2,3));
        Ball userBall = new Ball(1,2);
        Status status = comBalls.compare(userBall);
        Assertions.assertThat(status).isEqualTo(Status.BALL);
    }

    @Test
    void 아웃_Balls(){
        Balls comBalls = new Balls(Arrays.asList(1,2,3));
        Ball userBall = new Ball(1,4);
        Status status = comBalls.compare(userBall);
        Assertions.assertThat(status).isEqualTo(Status.OUT);
    }

    @Test
    void 아웃3_Game_Result(){
        Balls comBalls = new Balls(Arrays.asList(1,2,3));
        Game gameResult = comBalls.compare(Arrays.asList(4, 5, 6));
        Assertions.assertThat(gameResult.getOut()).isEqualTo(3);
    }

    @Test
    void 볼1_스트라이크1_Game_Result(){
        Balls comBalls = new Balls(Arrays.asList(1,2,3));
        Game gameResult = comBalls.compare(Arrays.asList(4, 1, 3));
        Assertions.assertThat(gameResult.getStrike()).isEqualTo(1);
        Assertions.assertThat(gameResult.getBall()).isEqualTo(1);
        Assertions.assertThat(gameResult.getOut()).isEqualTo(1);
    }

    @Test
    void 스트라이크3_Game_Result(){
        Balls comBalls = new Balls(Arrays.asList(1,2,3));
        Game gameResult = comBalls.compare(Arrays.asList(1, 2, 3));
        Assertions.assertThat(gameResult.getStrike()).isEqualTo(3);
        Assertions.assertThat(gameResult.getBall()).isEqualTo(0);
        Assertions.assertThat(gameResult.getOut()).isEqualTo(0);

    }

}