package com.djyun.oopjavabaseballAPI;

import com.djyun.oopjavabaseballAPI.domain.user.User;
import com.djyun.oopjavabaseballAPI.domain.user.UserCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @Autowired
    private UserCheck userCheck;


    @PostMapping("/game/start")
    public User createUserId(){
        User savedUser = userCheck.save();
        return savedUser;
    }
}
