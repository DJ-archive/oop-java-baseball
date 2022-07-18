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
    void 숫자_검증(){
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
}