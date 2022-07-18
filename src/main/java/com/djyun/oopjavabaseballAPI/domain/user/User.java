package com.djyun.oopjavabaseballAPI.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int roomId;
    private int remainingCount = 10;
    private int answerCount = 0;

}


