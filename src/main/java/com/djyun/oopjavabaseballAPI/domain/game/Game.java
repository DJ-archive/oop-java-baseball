package com.djyun.oopjavabaseballAPI.domain.game;

import com.djyun.oopjavabaseballAPI.domain.game.baseball.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Game {
    private boolean correct;
    private int strike;
    private int ball;
    private int out;

    @JsonIgnore
    private List<Integer> realAnswer; //

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
}
