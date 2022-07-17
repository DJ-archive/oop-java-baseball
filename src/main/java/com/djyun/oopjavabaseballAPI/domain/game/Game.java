package com.djyun.oopjavabaseballAPI.domain.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Game {
    private boolean correct;
    private int remainingCount;
    private int strike;
    private int ball;
    private int out;

    private List<Integer> realAnswer;
}
