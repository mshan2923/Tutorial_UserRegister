package com.tutorial.tutorial_userregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//기본
        //(exclude = {SecurityAutoConfiguration.class})//spring boot security 강제 로그인 창 해제 (인증 되지 않아서 그럼)
public class TutorialUserRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorialUserRegisterApplication.class, args);
    }

}
