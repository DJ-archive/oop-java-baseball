package com.djyun.oopjavabaseballAPI.domain.game;

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

}
