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
        Query query = em.createQuery("select b from Board b order by b.id desc", Board.class);
        return query.getResultList();
    }

    public Board findById() {
        return null;
    }


    @Transactional
    public Board save(Board board) {
        // 1. 비영속 객체
        // Board board = new Board(title, content, username);

        em.persist(board); // persist를 거쳐서 들어오면서 영속객체가 됨
        // 2. board -> 영속 객체
        return board;
        // 래퍼런스니까 board 그 자체 동기화가 되어 있다.
        // 콜바이 밸류가 아니라 콜바이 래퍼런스 이제는 return도 적을 필요가 없다
    }


    @Transactional
    public void deleteById() {

    }

    @Transactional
    public void updateById(){

    }



}
