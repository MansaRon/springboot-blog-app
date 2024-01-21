package com.springboot.blog.utils;

import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @author Thendo
 * @date 2024/01/21
 */
public class OneTimePasswordHelp {

    private final static Integer LENGTH = 6;

    @Bean
    public static Supplier<Integer> createRandomOneTimePassword() {
        return () -> {
            Random random = new Random();
            StringBuilder oneTimePassword = new StringBuilder();
            for (int i = 0; i < LENGTH; i++) {
                int randomNumber = random.nextInt(10);
                oneTimePassword.append(randomNumber);
            }
            return Integer.parseInt(oneTimePassword.toString().trim());
        };
    }

}
