package com.djyun.oopjavabaseballAPI.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int roomId;
    private int remainingCount;
    private int answerCount;
    @JsonIgnore
    private List<Integer> realAnswer; //
}
