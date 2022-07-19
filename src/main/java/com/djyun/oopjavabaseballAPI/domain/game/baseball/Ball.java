package com.djyun.oopjavabaseballAPI.domain.game.baseball;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class Ball {
    private final int position;
    private final int number;

    public Status compare(Ball userBall) {
        if (this.number == userBall.number){
            if (this.position == userBall.position){
                return Status.STRIKE;
            }
            return Status.BALL;
        }
        return Status.OUT;
    }

}

/**
* 126 236 -> 1 ball, 1 strike, 1 out
** 1,1 1,2 / 1,1 2,3 / 1,1 3,6 -> x x x
** 2,2 1,2 / 2,2 2,3 / 2,2 3,6 -> 1 ball x x
** 3,6 1,2 / 3,6 2,3 / 3,6 3,6 -> x x 1 strike
**/