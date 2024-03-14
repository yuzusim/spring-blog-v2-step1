package shop.mtcoding.blog.board;

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

    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardPersistRepository.findAll();

        // then
        System.out.println("findAll_test/size : " +boardList.size());
        System.out.println("findAll_test/username : " +boardList.get(2).getUsername());

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


    // org.assertj.core.api
//        assertThat(boardList.size()).isEqualTo(4);
//        assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
}
