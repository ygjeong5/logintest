package logintest.logintest_demo.login_test.service;

import logintest.logintest_demo.login_test.domain.Provider;
import logintest.logintest_demo.login_test.domain.Role;
import logintest.logintest_demo.login_test.domain.User;
import logintest.logintest_demo.login_test.dto.LoginRequestDto;
import logintest.logintest_demo.login_test.dto.UserSignUpDto;
import logintest.logintest_demo.login_test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j  // 추가된 어노테이션
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> login(LoginRequestDto loginRequestDto){
        return userRepository.findByEmail(loginRequestDto.getEmail());
    }

    @Transactional
    public User signup(UserSignUpDto signUpDto) {
        // 이메일 중복 체크
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        // 회원 저장
        User user = User.builder()
                .email(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .name(signUpDto.getName())
                .role(Role.USER)
                .provider(Provider.LOCAL)
                .build();

        // 저장 로그 추가
        log.info("회원가입 시도: {}", user.getEmail());
        User savedUser = userRepository.save(user);
        log.info("회원가입 완료: {}", savedUser.getEmail());

        return savedUser;
    }
}