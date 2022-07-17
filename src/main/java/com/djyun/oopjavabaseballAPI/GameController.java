package com.djyun.oopjavabaseballAPI;

import com.djyun.oopjavabaseballAPI.domain.game.Game;
import com.djyun.oopjavabaseballAPI.domain.game.GameCalculator;
import com.djyun.oopjavabaseballAPI.domain.game.GameCheck;
import com.djyun.oopjavabaseballAPI.domain.user.User;
import com.djyun.oopjavabaseballAPI.domain.user.UserCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GameController {

    @Autowired
    private UserCheck userCheck;
    private GameCheck gameCheck;
    private GameCalculator gameCalculator;

    @PostMapping("/game/start")
    public User createUserId(){
        User savedUser = userCheck.save();
        return savedUser;
    }

    @PostMapping("/game/{roomId}/{answer}")
    public Game gameStart(@PathVariable int roomId, int answer){
        // 1. 게임 진행 여부 결정
        boolean possible = gameCheck.isPossible(roomId);

        // 2. 게임 진행
        if (possible){
            Game currentGame = new Game();
            Game gameResult = gameCalculator.gameRules(answer, currentGame);
            return gameResult;
        }
        return null; // 추후 에러 처리
    }

    @GetMapping("/game/{}/history")
    public ArrayList<Game> retrieveHistory(int roomId){
        return gameCheck.retrieveAll(roomId);
    }





}
