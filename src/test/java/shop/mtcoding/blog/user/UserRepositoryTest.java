package shop.mtcoding.blog.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserRepository.class) // IoC 등록코드
@DataJpaTest // Datasource(connection pool), EntityManager -> 필요한 것만 띄운다.
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsername_test() {
       // given
        String username = "ssar";
        String password = "1234";

       // when
        User user = userRepository.findByUsernameAndPassword(username, password);
        // 주어진 username과 password에 해당하는 유저를 조회

        // then
        Assertions.assertThat(user.getUsername()).isEqualTo("ssar");
    }



}
