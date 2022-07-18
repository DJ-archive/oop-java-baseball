package com.djyun.oopjavabaseballAPI.domain.game;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private NumberGenerator numberGenerator;
    private List<Integer> realAnswer;

    public boolean isPossible(int roomId){
        // remainingCount <=10
        return true;
    }


    public List<Integer> getRealAnswer(int roomId){
        List<Integer> realAnswerByID = gameRepository.findRealAnswerByID(roomId);
        if (realAnswerByID.isEmpty()){
            return numberGenerator.createRandomNum();
        }
        return realAnswerByID;
    }


    // remainingCount 줄이고 answerCount 늘리기
    public Game runGame(int roomId, List<Integer> userAnswer, List<Integer> realAnswer){
        gameRepository.makeNewGame(roomId, realAnswer);

        Game game = new Game();
        game.setRealAnswer(realAnswer);
        game.setBall(1);
        game.setStrike(1);
        //

        return game;
    }
}
