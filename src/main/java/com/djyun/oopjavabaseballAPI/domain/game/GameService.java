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
    private List<Integer> realAnswer;

    public boolean isPossible(int roomId){
        // remainingCount <=10
        return true;
    }

    public List<Integer> getRealAnswer(int roomId){
        List<Integer> realAnswerByID = gameRepository.findRealAnswerByID(roomId);
        if (realAnswerByID ==null){
            Random random = new Random();
            List<Integer> realAnswer = new ArrayList<>();

            while(realAnswer.size()<3){
                int randomInt = random.nextInt(9)+1;
                if (!realAnswer.contains(randomInt)) {
                    realAnswer.add(randomInt);
                }
            }
            return realAnswer;
        }
        return realAnswerByID;
    }

    // remainingCount 줄이고 answerCount 늘리기
    public Game gameRun(int answer, List<Integer> realAnswer){
        Game game = new Game();
        game.setRealAnswer(realAnswer);


        return null;
    }
}