package com.djyun.oopjavabaseballAPI;

import com.djyun.oopjavabaseballAPI.domain.game.Game;
import com.djyun.oopjavabaseballAPI.domain.game.GameService;
import com.djyun.oopjavabaseballAPI.domain.game.GameRepository;
import com.djyun.oopjavabaseballAPI.domain.user.User;
import com.djyun.oopjavabaseballAPI.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class GameController {

    @Autowired
    private UserRepository userRepository;
    private GameRepository gameRepository;
    private GameService gameService;
    private BasicResponse basicResponse = new BasicResponse();
    @PostMapping("/game/start")
    public ResponseEntity<BasicResponse> createUserId(){
        User savedUser = userRepository.save();
        basicResponse = BasicResponse.builder().success(true).data(savedUser.getRoomId()).build();
        return new ResponseEntity<>(basicResponse, HttpStatus.CREATED);
    }

    @PostMapping("/game/{roomId}/{answer}")
    public Game gameStart(@PathVariable int roomId, int answer){
        // 1. 게임 진행 여부 결정
        boolean possible = gameRepository.isPossible(roomId);

        // 2. 게임 진행
        if (possible){
            Game currentGame = new Game();
            Game gameResult = gameService.gameRules(answer, currentGame);
            return gameResult;
        }
        return null; // 추후 에러 처리
    }

    @GetMapping("/game/{roomId}/history")
    public ArrayList<Game> retrieveHistory(@PathVariable int roomId){
        return gameRepository.retrieveAll(roomId);
    }





}
