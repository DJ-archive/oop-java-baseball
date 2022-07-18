package com.djyun.oopjavabaseballAPI.domain.game.gameresult;

import com.djyun.oopjavabaseballAPI.domain.game.NumberGenerator;
import com.djyun.oopjavabaseballAPI.domain.game.baseball.Status;
import com.djyun.oopjavabaseballAPI.domain.user.User;
import com.djyun.oopjavabaseballAPI.domain.user.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Game {
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

}
