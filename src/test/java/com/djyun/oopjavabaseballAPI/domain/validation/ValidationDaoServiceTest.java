package com.djyun.oopjavabaseballAPI.domain.validation;

import com.djyun.oopjavabaseballAPI.domain.user.UserDao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;

class ValidationDaoServiceTest {

    @Autowired
    private Validation validation;
    private UserDao userDao;

    @BeforeEach
    void init(){
        validation = new Validation(userDao);
    }

    @Test
    void 숫자_범위_검증(){
        boolean result1 = validation.checkRange(Arrays.asList(1,2,3));
        boolean result2 = validation.checkRange(Arrays.asList(9,1,9));
        boolean result3 = validation.checkRange(Arrays.asList(0,1,9));

        Assertions.assertThat(result1).isTrue();
        Assertions.assertThat(result2).isTrue();
        Assertions.assertThat(result3).isFalse();
    }

    @Test
    void 중복_검증() {
        boolean result1 = validation.unDuplicated(Arrays.asList(1,2,3));
        boolean result2 = validation.unDuplicated(Arrays.asList(9,1,9));
        boolean result3 = validation.unDuplicated(Arrays.asList(1,1,1));

        Assertions.assertThat(result1).isTrue();
        Assertions.assertThat(result2).isFalse();
        Assertions.assertThat(result3).isFalse();

    }

    @Test
    void 자릿수_검증(){
        boolean result1 = validation.countDigits(Arrays.asList(1, 2, 3));
        boolean result2 = validation.countDigits(new ArrayList<>());
        boolean result3 = validation.countDigits(Arrays.asList(1, 2, 3, 4));
        boolean result4 = validation.countDigits(Arrays.asList(1, 2));

        Assertions.assertThat(result1).isTrue();
        Assertions.assertThat(result2).isFalse();
        Assertions.assertThat(result3).isFalse();
        Assertions.assertThat(result4).isFalse();
    }

    @Test
    void 입력값_검증(){
        boolean result1 = validation.checkNumValid(123); // t
        boolean result2 = validation.checkNumValid(919); // f
        boolean result3 = validation.checkNumValid(1193); // f
        boolean result4 = validation.checkNumValid(119); // f
        boolean result5 = validation.checkNumValid(011); // f
        boolean result6 = validation.checkNumValid(245); // t
        boolean result7 = validation.checkNumValid(null); // f
        boolean result8 = validation.checkNumValid(1234); // f

        Assertions.assertThat(result1).isTrue();
        Assertions.assertThat(result2).isFalse();
        Assertions.assertThat(result3).isFalse();
        Assertions.assertThat(result4).isFalse();
        Assertions.assertThat(result5).isFalse();
        Assertions.assertThat(result6).isTrue();
        Assertions.assertThat(result7).isFalse();
        Assertions.assertThat(result8).isFalse();

    }
}