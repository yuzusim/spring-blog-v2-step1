package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardPersistRepository {
    private final EntityManager em;

    public List<Board> findAll() {
        return null;
    }

    public Board findById() {
        return null;
    }

    @Transactional
    public Board save(String title, String content, String username) {

        return null;
    }

    @Transactional
    public void deleteById() {

    }

    @Transactional
    public void updateById(){

    }



}
