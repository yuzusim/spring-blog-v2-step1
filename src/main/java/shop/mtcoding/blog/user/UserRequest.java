package shop.mtcoding.blog.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class JoinDTO{
        private String username;
        private String password;
        private String email;

        public User toEntity(){ // 컨트롤러의 코드가 엄청나게 줄어 듬
            return User.builder()
                    .username(username) // shift + enter 줄 넘기기
                    .password(password)
                    .email(email)
                    .build();
        }
    } // @Builder 있어야 사용 가능

    @Data
    public static class LoginDTO{
        private String username;
        private String password;
    }

}
