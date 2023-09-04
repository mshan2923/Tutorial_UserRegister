package com.tutorial.tutorial_userregister.Controller;

import com.tutorial.tutorial_userregister.Dto.UserRequestDto;
import com.tutorial.tutorial_userregister.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/view")
public class ViewController {

    private final UserService userService;

    public ViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/view/login")
    public String loginPage() {
        System.out.println("----");
        return "login";//----------------- 왜 텍스트가 떠
    }
    //LoginController의 이하 부분 삭제! 필요없어짐.

    @PostMapping("/view/login-process")
    public String login(UserRequestDto dto) {
        boolean isValidMember = userService.isVailMember(dto.getUserid(), dto.getPw());
        if (isValidMember)
            return "dashboard";
        return "login";
    }

    @PostMapping("/auth/join")
    public ResponseEntity<String> join(@RequestBody UserRequestDto dto) {
        try {
            //registerMemberService.join(dto.getUserid(), dto.getPw());
            return ResponseEntity.ok("join success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("view/dashboard")
    public String dashboardPage(Model model) {
        return "dashboard";
    }
}