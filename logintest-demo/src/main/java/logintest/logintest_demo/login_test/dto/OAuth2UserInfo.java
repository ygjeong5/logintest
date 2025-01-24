package logintest.logintest_demo.login_test.dto;
import java.util.Map;

public class OAuth2UserInfo {
    private Map<String, Object> attributes;
    private String providerId;
    private String email;
    private String name;

    public OAuth2UserInfo(Map<String, Object> attributes, String provider) {
        this.attributes = attributes;

        if ("kakao".equalsIgnoreCase(provider)) {
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

            this.providerId = attributes.get("id").toString();
            this.email = (String) kakaoAccount.get("email");
            this.name = (String) profile.get("nickname");
        }
    }

    public String getProviderId() {
        return providerId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}