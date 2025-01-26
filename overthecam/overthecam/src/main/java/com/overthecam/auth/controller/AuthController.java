package com.overthecam.auth.controller;

import com.overthecam.auth.domain.dto.*;
import com.overthecam.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<UserResponse>> signup(@RequestBody @Valid SignUpRequest request) {
        UserResponse userResponse = authService.signup(request);
        return ResponseEntity.ok(CommonResponse.<UserResponse>builder()
                .success(true)
                .message("회원가입 성공")
                .data(userResponse)
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<TokenResponse>> login(@RequestBody @Valid LoginRequest request) {
        TokenResponse tokenResponse = authService.login(request);
        return ResponseEntity.ok(CommonResponse.<TokenResponse>builder()
                .success(true)
                .message("로그인 성공")
                .data(tokenResponse)
                .build());
    }

    @PostMapping("/logout")
    public ResponseEntity<CommonResponse<Void>> logout(@RequestHeader("Authorization") String token) {
        authService.logout(token);
        return ResponseEntity.ok(CommonResponse.<Void>builder()
                .success(true)
                .message("로그아웃 성공")
                .data(null)
                .build());
    }
}