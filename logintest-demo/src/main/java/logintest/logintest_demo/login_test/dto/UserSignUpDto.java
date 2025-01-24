package logintest.logintest_demo.login_test.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpDto {
    @Email(message = "유효한 이메일 주소를 입력해주세요")
    @NotBlank(message = "이메일은 필수 입력 값입니다")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다")
    private String password;

    @NotBlank(message = "이름은 필수 입력 값입니다")
    private String name;
}