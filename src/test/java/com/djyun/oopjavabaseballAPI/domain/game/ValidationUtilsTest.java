package com.djyun.oopjavabaseballAPI.domain.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilsTest {

    private ValidationUtils validationUtils = new ValidationUtils();

    @Test
    void 숫자_범위_검증(){
        boolean result1 = validationUtils.checkRange(Arrays.asList(1,2,3));
        boolean result2 = validationUtils.checkRange(Arrays.asList(9,1,9));
        boolean result3 = validationUtils.checkRange(Arrays.asList(0,1,9));

        Assertions.assertThat(result1).isTrue();
        Assertions.assertThat(result2).isTrue();
        Assertions.assertThat(result3).isFalse();
    }

    @Test
    void 중복_검증() {
        boolean result1 = validationUtils.unDuplicated(Arrays.asList(1,2,3));
        boolean result2 = validationUtils.unDuplicated(Arrays.asList(9,1,9));
        boolean result3 = validationUtils.unDuplicated(Arrays.asList(1,1,1));

        Assertions.assertThat(result1).isTrue();
        Assertions.assertThat(result2).isFalse();
        Assertions.assertThat(result3).isFalse();

    }

    @Test
    void 자릿수_검증(){
        boolean result1 = validationUtils.countDigits(Arrays.asList(1, 2, 3));
        boolean result2 = validationUtils.countDigits(new ArrayList<>());
        boolean result3 = validationUtils.countDigits(Arrays.asList(1, 2, 3, 4));
        boolean result4 = validationUtils.countDigits(Arrays.asList(1, 2));

        Assertions.assertThat(result1).isTrue();
        Assertions.assertThat(result2).isFalse();
        Assertions.assertThat(result3).isFalse();
        Assertions.assertThat(result4).isFalse();
    }

    @Test
    void 전체_검증(){
        boolean result1 = validationUtils.checkValidation(123); // t
        boolean result2 = validationUtils.checkValidation(919); // f
        boolean result3 = validationUtils.checkValidation(1193); // f
        boolean result4 = validationUtils.checkValidation(119); // f
        boolean result5 = validationUtils.checkValidation(011); // f
        boolean result6 = validationUtils.checkValidation(245); // t
        boolean result7 = validationUtils.checkValidation(null); // f
        boolean result8 = validationUtils.checkValidation(1234); // f

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