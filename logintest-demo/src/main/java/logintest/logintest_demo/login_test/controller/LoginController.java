package logintest.logintest_demo.login_test.controller;
com.yes.overthecam

import logintest.logintest_demo.login_test.dto.LoginRequestDto;
import logintest.logintest_demo.login_test.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import logintest.logintest_demo.login_test.domain.User;

import java.util.Optional;

@Controller
@Slf4j
public class LoginController {

    UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @PostMapping("/login2")
    public ResponseEntity<?> login(LoginRequestDto loginRequestDto){
        Optional<User> user = userService.login(loginRequestDto);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "auth/signup";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }
}