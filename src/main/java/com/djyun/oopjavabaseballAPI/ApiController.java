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
import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private UserRepository userRepository;
    private GameRepository gameRepository;
    private GameService gameService;
    @PostMapping("/game/start")
    public ResponseEntity createUserId(){
        User savedUser = userRepository.save();
        return new ResponseEntity(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/game/{roomId}/{answer}")
    public ResponseEntity gameStart(@PathVariable int roomId, Integer answer){
        // 1. 게임 진행 여부 결정 (10 이하)
        boolean possible = gameService.isPossible(roomId);

        // 2. 게임 진행
        if (possible){
            List<Integer> realAnswer = gameService.getRealAnswer(roomId);
            Game gameResult = gameService.runGame(roomId, answer, realAnswer);
            return new ResponseEntity(gameResult, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("CLOSED_GAME", HttpStatus.BAD_REQUEST); // 추후 에러 처리
    }

    @GetMapping("GET /game/{roomId}")
    public ResponseEntity retrieveResult(@PathVariable int roomId){
        Game lastResult = gameRepository.findLastGame(roomId);
        return new ResponseEntity(lastResult, HttpStatus.OK);
    }

    @GetMapping("/game/{roomId}/history")
    public ResponseEntity retrieveHistory(@PathVariable int roomId){
        ArrayList<Game> gameHistory = gameRepository.retrieveAll(roomId);
        return new ResponseEntity<>(gameHistory, HttpStatus.OK);
    }
}
