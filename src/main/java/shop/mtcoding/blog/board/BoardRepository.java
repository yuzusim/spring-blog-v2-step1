package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository // new BoardRepository() -> IoC 컨테이너 등록
public class BoardRepository {
    private final EntityManager em;

    @Transactional
    public void updeteById(int id, String title, String content){
        Board board = findById(id); // 주어진 id에 해당하는 게시글을 조회
        board.setTitle(title);
        board.setContent(content);
        // 터티체킹
    }

    @Transactional
    public void deleteById(int id){
        // delete는 em.remove 사용하지 말고, 쿼리문을 적어야함!
        // 게시글(Board) 테이블에서 특정 ID에 해당하는 게시글을 삭제하는 쿼리
        Query query = em.createQuery("delete from Board b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public Board save(Board board){
        em.persist(board);
        return board;
    }

    public List<Board> findAll(){
        Query query =
                em.createQuery("select b from Board b order by b.id desc", Board.class);
        return query.getResultList();
    }

    public Board findByIdJoinUser(int id){
        Query query =
                em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class);
        query.setParameter("id", id);
        return (Board) query.getSingleResult();
        // 알아서 pk, fk 키 연결
    }

    public Board findById(int id){
        // 일단 조회
        // id, title, content, user_id(이질감), created_at
        Board board = em.find(Board.class, id);
        return board;

        // em.find(Board.class, id)는 EntityManager(em)을 사용하여
        // Board 클래스에서 주어진 ID와 일치하는 엔티티를 데이터베이스에서 검색
        // 검색된 Board 객체가 반환되어 해당 메서드의 호출자에게 반환
    }


}