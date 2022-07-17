package com.djyun.oopjavabaseballAPI.domain.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Game {
    private int strike;
    private int ball;
    private int out;
    private int answer;
}
