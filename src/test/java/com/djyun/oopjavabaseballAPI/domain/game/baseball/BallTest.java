package com.djyun.oopjavabaseballAPI.domain.game.baseball;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BallTest {
    private Ball comBall;

    @BeforeEach
    void setComBall(){
        comBall = new Ball(1,2);
    }

    @Test
    void Strike(){
        Ball userBall = new Ball(1,2);
        Status status = comBall.compare(userBall);
        Assertions.assertThat(status).isEqualTo(Status.STRIKE);
    }

    @Test
    void Ball(){
        Ball userBall = new Ball(2,2);
        Status status = comBall.compare(userBall);
        Assertions.assertThat(status).isEqualTo(Status.BALL);
    }

    @Test
    void Out(){
        Ball userBall = new Ball(1,3);
        Status status = comBall.compare(userBall);
        Assertions.assertThat(status).isEqualTo(Status.OUT);
    }
}