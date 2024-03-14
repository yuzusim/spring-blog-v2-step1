package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(BoardPersistRepository.class)
@DataJpaTest
public class BoardPersistRepositoryTest {

    @Autowired // DI
    private BoardPersistRepository boardPersistRepository;

    @Autowired // DI
    private EntityManager em;

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Board board = boardPersistRepository.findById(id);
        em.clear(); //비우기
        boardPersistRepository.findById(id);
        System.out.println("findById_test : " + board);

        // then
        assertThat(board.getTitle()).isEqualTo("제목1");
        assertThat(board.getContent()).isEqualTo("내용1");

    }

    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardPersistRepository.findAll();

        // then
        System.out.println("findAll_test/size : " + boardList.size());
        System.out.println("findAll_test/username : " + boardList.get(2).getUsername());

        // org.assertj.core.api
        assertThat(boardList.size()).isEqualTo(4);
        assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");

    }

    @Test
    public void save_test() {
        // given

        // 객체 만들어서 바로 넣기
        Board board = new Board("제목5", "내용5", "ssar");

        // when
        boardPersistRepository.save(board); // 동기화 되어 있는지 검증
        System.out.println("save_test : " + board);

        // then

    }

    @Test
    public void deleteByIdV2_test() {
        // given
        int id = 1;

        // when
        boardPersistRepository.deleteByIdV2(id);

        // 이 라인 쿼리, 트랜잭션 종료되지 않았지만 강제로 날려 보냄
        em.flush();

        Board board = boardPersistRepository.findById(id);
        System.out.println("findById_test " + board);
    }

    @Test
    public void deleteById_test(){
        // given
        int id = 1;

        // when
        boardPersistRepository.deleteById(id);
    }



}
