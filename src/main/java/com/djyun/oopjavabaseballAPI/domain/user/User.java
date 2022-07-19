package com.djyun.oopjavabaseballAPI.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class User {
    private int roomId;
    private boolean correct = false;
    private int remainingCount = 10;
    private int answerCount = 0;

    public User(int roomId) {
        this.roomId = roomId;
    }
}


