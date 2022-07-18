package com.djyun.oopjavabaseballAPI;

import com.djyun.oopjavabaseballAPI.domain.game.*;
import com.djyun.oopjavabaseballAPI.domain.user.User;
import com.djyun.oopjavabaseballAPI.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
@Slf4j
public class ApiController {


    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final GameService gameService;
    private final ValidationUtils validationUtils;
    private final NumberGenerator numberGenerator;


    @PostMapping("/game/start")
    public ResponseEntity startGame(){
        User savedUser = userRepository.save();
        return new ResponseEntity(savedUser.getRoomId(), HttpStatus.CREATED);
    }


    @PostMapping("/game/{roomId}/{answer}")
    public ResponseEntity playGame(@PathVariable int roomId, @PathVariable(required = false) String answer){
        List<Integer> userAnswer = numberGenerator.convertIntList(answer);
        log.info("user answer = {}", userAnswer);
        if (validationUtils.checkValidation(userAnswer)){
            log.info("check validation = {}", true);
            List<Integer> realAnswer = numberGenerator.createRandomNum();
            Game gameResult = gameService.runGame(roomId, userAnswer, realAnswer);
            return new ResponseEntity(gameResult, HttpStatus.CREATED);
        }
        log.info("check validation = {}", false);
        /**
         * TODO validation 경우의 수에 따른 에러처리
         */
        return new ResponseEntity<>("CLOSED_GAME", HttpStatus.BAD_REQUEST);
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
