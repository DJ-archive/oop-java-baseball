package com.djyun.oopjavabaseballAPI;

import com.djyun.oopjavabaseballAPI.domain.game.*;
import com.djyun.oopjavabaseballAPI.domain.game.baseball.Balls;
import com.djyun.oopjavabaseballAPI.domain.game.gameresult.Game;
import com.djyun.oopjavabaseballAPI.domain.game.gameresult.GameRepository;
import com.djyun.oopjavabaseballAPI.domain.game.gameresult.GameService;
import com.djyun.oopjavabaseballAPI.domain.user.User;
import com.djyun.oopjavabaseballAPI.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final UserRepository userStore;
    private final GameRepository gameStore;

    private final GameService gameService;
    private final ValidationUtils validationUtils;
    private final NumberGenerator numberGenerator;


    @PostMapping("/game/start")
    public ResponseEntity startGame(){
        User savedUser = userStore.save();
        return new ResponseEntity(savedUser.getRoomId(), HttpStatus.CREATED);
    }

    /**
     * TODO validation 경우의 수에 따른 에러 처리
     * @roomId 같은 유저일 경우, realAnswer는 한번만 생성하여 가져와야 함!
     */
    @PostMapping("/game/{roomId}/{answer}")
    public ResponseEntity playGame(@PathVariable int roomId, @PathVariable Integer answer){
        if (validationUtils.checkNumValid(answer) && validationUtils.checkTryNum(roomId)){
            log.info("check validation = {}", true);

            List<Integer> realAnswer = gameService.getRealAnswer(roomId);
            List<Integer> userAnswer = validationUtils.convertIntList(answer);
            log.info("real computer answer={}", realAnswer); // roomId가 같을 경우, 한번만 생성 후 반환
            log.info("current user answer={}", userAnswer);

            Balls newBaseballGame = new Balls(realAnswer);
            Game gameResult = newBaseballGame.compare(userAnswer);
            gameResult.setRealAnswer(realAnswer);
            gameResult.setUserAnswer(userAnswer);

            gameService.endGame(roomId, gameResult);

            return new ResponseEntity(gameResult, HttpStatus.CREATED);
        }
        log.info("check validation = {}", false);
        return new ResponseEntity<>("CLOSED_GAME", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/game/{roomId}")
    public ResponseEntity askResult(@PathVariable int roomId){
        User findingPlayerInfo = userStore.findUserById(roomId);
        return new ResponseEntity(findingPlayerInfo, HttpStatus.OK);
    }

    @GetMapping("/game/{roomId}/history")
    public ResponseEntity askHistory(@PathVariable int roomId){
        ArrayList<Game> gameHistory = gameStore.retrieveAll(roomId);
        return new ResponseEntity<>(gameHistory, HttpStatus.OK);
    }
}
