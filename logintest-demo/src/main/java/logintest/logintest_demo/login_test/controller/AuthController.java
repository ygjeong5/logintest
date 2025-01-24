package logintest.logintest_demo.login_test.controller;

import logintest.logintest_demo.login_test.domain.User;
import logintest.logintest_demo.login_test.dto.LoginRequestDto;
import logintest.logintest_demo.login_test.dto.LoginResponseDto;
import logintest.logintest_demo.login_test.dto.UserSignUpDto;
import logintest.logintest_demo.login_test.security.JwtTokenProvider;
import logintest.logintest_demo.login_test.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "auth/signup";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );

            String token = jwtTokenProvider.createToken(authentication);
            log.info("Login successful for email: {}", loginDto.getEmail());
            return ResponseEntity.ok(new LoginResponseDto(token));
        } catch (AuthenticationException e) {
            log.warn("Login failed for email: {}", loginDto.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("로그인 실패", "이메일 또는 비밀번호가 일치하지 않습니다."));
        } catch (Exception e) {
            log.error("Unexpected login error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("서버 오류", "로그인 중 오류가 발생했습니다."));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserSignUpDto signUpDto) {
        try {
            // 실제 DB에 저장되도록 확인
            User savedUser = userService.signup(signUpDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("회원가입 실패", e.getMessage()));
        }
    }

    // 에러 응답을 위한 내부 클래스
    private static class ErrorResponse {
        private String title;
        private String message;

        public ErrorResponse(String title, String message) {
            this.title = title;
            this.message = message;
        }

        // Getters for Jackson serialization
        public String getTitle() { return title; }
        public String getMessage() { return message; }
    }
}