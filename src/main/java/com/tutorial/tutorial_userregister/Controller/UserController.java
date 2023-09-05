package com.tutorial.tutorial_userregister.Controller;

import com.tutorial.tutorial_userregister.Dto.UserRequestDto;
import com.tutorial.tutorial_userregister.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired//의존관계를 자동으로 설정 (Service 객체 주입)
    UserService userService;

    @GetMapping("/")
    public String StartPage()
    {
        //return "redirect:/Register";
        System.out.println("Start Page");
        return "login";
    }

    @GetMapping("/login/login")
    public String loginPage()
    {
        //return "redirect:/Register";
        System.out.println("login Page");
        return "dashboard";
    }

    @PostMapping("/login/login")
    public String OnLogin(@RequestBody UserRequestDto data)
    {
        System.out.println("-----------> " + data.getUserid() + "/ " + data.getPw());
        return "dashboard";
    }//loginProcessingUrl으로 값을 넘겨 로그인 처리
    //----------NotWork - 도중에 넘어가는듯?

    @GetMapping("/login/logout")
    public String logoutPage()
    {
        System.out.println("LogOut");
        return "login";
    }

    /**
     * 회원가입 폼
     * @return
     */
    @GetMapping("login/signUp")
    public String signUpForm() {
        System.out.println("SignUp");
        return "Regist";
    }

    /**
     * 회원가입 진행
     * @param userRequestDto
     * @return
     */
    @PostMapping("/login/signUp")
    public String signUp(@RequestBody UserRequestDto userRequestDto)
    {
        System.out.println("Send signup data \n" + userRequestDto.getUserid() + " / " + userRequestDto.getPw());

        userService.joinUser(userRequestDto);
        return "redirect:/login"; //로그인 구현 예정
    }
    @PostMapping("/login/login-process")
    public String login(@RequestBody UserRequestDto dto) {
        boolean isValidMember = userService.isVailMember(dto.getUserid(), dto.getPw());
        System.out.println("processing");
        if (isValidMember)
            return "dashboard";
        return "login";
    }//================== 이거 중심으로 파기 왜 작동 안함?
    @GetMapping("/login/fail")
    public String LoginFail()
    {
        System.out.println("LoginFail");
        return "LoginFail";
    }
}
