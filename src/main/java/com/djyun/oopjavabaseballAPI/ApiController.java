package com.djyun.oopjavabaseballAPI;

import com.djyun.oopjavabaseballAPI.domain.game.*;
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
    private ValidationUtils validationUtils;
    private NumberGenerator numberGenerator;

    @PostMapping("/game/start")
    public ResponseEntity startGame(){
        User savedUser = userRepository.save();
        return new ResponseEntity(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/game/{roomId}/{answer}")
    public ResponseEntity playGame(@PathVariable int roomId, String answer){
        List<Integer> userAnswer = numberGenerator.convertIntList(answer);

        if (validationUtils.checkValidation(userAnswer)){
            List<Integer> realAnswer = gameService.getRealAnswer(roomId);
            Game gameResult = gameService.runGame(roomId, userAnswer, realAnswer);
            return new ResponseEntity(gameResult, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("CLOSED_GAME", HttpStatus.BAD_REQUEST); // 추후 에러 처리
    }

    @GetMapping("GET /game/{roomId}")
    public ResponseEntity askResult(@PathVariable int roomId){
        Game lastResult = gameRepository.findLastGame(roomId);
        return new ResponseEntity(lastResult, HttpStatus.OK);
    }

    @GetMapping("/game/{roomId}/history")
    public ResponseEntity askHistory(@PathVariable int roomId){
        ArrayList<Game> gameHistory = gameRepository.retrieveAll(roomId);
        return new ResponseEntity<>(gameHistory, HttpStatus.OK);
    }
}
