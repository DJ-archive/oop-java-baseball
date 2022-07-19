package com.djyun.oopjavabaseballAPI;

import com.djyun.oopjavabaseballAPI.domain.game.baseball.Balls;
import com.djyun.oopjavabaseballAPI.domain.game.gameresult.Game;
import com.djyun.oopjavabaseballAPI.domain.game.gameresult.GameDao;
import com.djyun.oopjavabaseballAPI.domain.game.gameresult.GameService;
import com.djyun.oopjavabaseballAPI.domain.user.User;
import com.djyun.oopjavabaseballAPI.domain.user.UserDaoService;
import com.djyun.oopjavabaseballAPI.domain.user.UserDaoService;
import com.djyun.oopjavabaseballAPI.domain.validation.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApiController {

    private final UserDaoService userDaoService;
    private final GameDao gameDao;
    private final GameService gameService;
    private final Validation validation;


    @PostMapping("/game/start")
    public ResponseEntity startGame(){
        User savedUser = userDaoService.save();
        return new ResponseEntity(savedUser.getRoomId(), HttpStatus.CREATED);
    }

    @PostMapping("/game/{roomId}/{answer}")
    public ResponseEntity playGame(@PathVariable int roomId, @PathVariable Integer answer) {
        if (gameService.endGame(roomId, answer)){
            return new ResponseEntity<>("CLOSED_GAME", HttpStatus.BAD_REQUEST);
        }
        log.info("remaining count ={}", userDaoService.findUserById(roomId).getRemainingCount());

        List<Integer> realAnswer = gameService.getRealAnswer(roomId);
        List<Integer> userAnswer = validation.convertIntList(answer);
        log.info("real computer answer={}", realAnswer);
        log.info("current user answer={}", userAnswer);

        Balls newBaseballGame = new Balls(realAnswer);
        Game gameResult = newBaseballGame.compare(userAnswer);

        gameResult.setRealAnswer(realAnswer);
        gameResult.setUserAnswer(userAnswer);
        gameService.isCorrect(roomId, gameResult);
        gameService.storeGameResult(roomId, gameResult);

        return new ResponseEntity(gameResult, HttpStatus.CREATED);

    }


    @GetMapping("/game/{roomId}")
    public ResponseEntity askResult(@PathVariable int roomId){
        User findingPlayerInfo = userDaoService.findUserById(roomId);
        return new ResponseEntity(findingPlayerInfo, HttpStatus.OK);
    }

    @GetMapping("/game/{roomId}/history")
    public ResponseEntity askHistory(@PathVariable int roomId){
        ArrayList<Game> gameHistory = gameDao.retrieveAll(roomId);
        return new ResponseEntity<>(gameHistory, HttpStatus.OK);
    }
}
