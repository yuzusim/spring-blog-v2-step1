package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    @Transactional
    public User save(User user){
        em.persist(user);
        return user; // return이 필요 없긴 한데… return 넣어주자! 재사용 위해
    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO reqDTO){

        // "User" 엔티티를 선택하고, 해당 유저의 유저네임과 비밀번호가 주어진 값과 일치하는지 확인합니다.
        // 따라서 이 메서드는 주어진 유저네임과 비밀번호가 일치하는 경우 해당 유저 엔티티를 반환하고,
        // 일치하는 사용자가 없는 경우에는 예외가 발생할 것
        Query query =
                em.createQuery("select u from User u where u.username = :username and u.password = :password", User.class);
        query.setParameter("username", reqDTO.getUsername());
        query.setParameter("password", reqDTO.getPassword());
        return (User) query.getSingleResult();
    }

    public User findByUsernameAndPassword(String username, String password) {
        Query query =
                em.createQuery("select u from User u where u.username = :username and u.password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return (User) query.getSingleResult();
    }
}
