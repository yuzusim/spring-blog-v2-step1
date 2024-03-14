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

    public Board findById(int id) {
        Board board = em.find(Board.class, id); // 앞이 클래스, 뒤가 PK
        return board;
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

    //이거 안 쓸 거다. 밑에 있는 deleteById를 사용할 것이다!
    @Transactional
    public void deleteByIdV2(int id) {
        Board board = findById(id);
        //근데 remove가 어떻게 동작하는지 궁금하니 테스트 해보자!
        em.remove(board);
    }

    @Transactional
    public void deleteById(int id) {
        Query query = em.createQuery("delete from Board b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public void updateById(){

    }



}
