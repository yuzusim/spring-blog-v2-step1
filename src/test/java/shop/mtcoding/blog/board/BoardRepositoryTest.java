package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void updeteById_test() {
       // given
       int id = 1;
       String title = "title1";
       String content = "content1";

       // when
        boardRepository.updeteById(id, title, content);
        em.flush();
        // 업데이트가 된 건지 확인이 안 되기 때문에 (트랜젝션 종료 후 쿼리가 날아가서)
        // 실제 코드는 작성할 필요가 없다. 트랜젝션 종료될 거라!
        // em.flush를 꼭 해줘야함!

       // then

    }

    @Test
    public void deleteById_test() {
       // given
        int id = 1;

       // when
        boardRepository.deleteById(id); // delete query 발동함

       // then
        System.out.println("deleteById_test : "+boardRepository.findAll().size());

    }

    @Test
    public void findAll_lazyloding_test() {
       // given

       // when
        List<Board> boardList = boardRepository.findAll();
        boardList.forEach(board -> {
            System.out.println(board.getUser().getUsername());
        });


       // then

    }

    @Test
    public void findAll_test() {
       // given

       // when
        List<Board> boardList = boardRepository.findAll();

       // then

    }

    @Test
    public void findById_test() {
       // given
        int id = 1;
        System.out.println("start-1");
        Board board = boardRepository.findById(1);
        System.out.println("start-2");
        System.out.println(board.getUser().getUsername());
        System.out.println("start-3");

       // when

       // then

    }
}
