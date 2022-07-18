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

    /**
     * TODO validation 경우의 수에 따른 에러 처리
     * @roomId 같은 유저일 경우, realAnswer는 한번만 생성하여 가져와야 함!
     */
    @PostMapping("/game/{roomId}/{answer}")
    public ResponseEntity playGame(@PathVariable int roomId, @PathVariable(required = false) String answer){
        if (validationUtils.checkValidation(answer)){
            log.info("check validation = {}", true);

            List<Integer> realAnswer = numberGenerator.createRandomNum();
            List<Integer> userAnswer = validationUtils.convertIntList(answer);
            log.info("real computer answer={}", realAnswer); // roomId가 같을 경우, 한번만 생성 후 반환
            log.info("current user answer={}", userAnswer);

            Balls newBaseballGame = new Balls(realAnswer);
            Game gameResult = newBaseballGame.compare(userAnswer);


            gameService.endGame(roomId);

            return new ResponseEntity(gameResult, HttpStatus.CREATED);
        }
        log.info("check validation = {}", false);
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
