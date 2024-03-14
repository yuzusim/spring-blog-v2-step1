package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.util.MyDateUtil;

import java.sql.Timestamp;

@NoArgsConstructor // 기본생성자 있어야 함
@Data
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String username;

    // 날짜 자동 넣고 싶을때 pc 통해서 insert 될때 -> db (날짜자동주입)
    @CreationTimestamp // pc -> db (날짜주입)
    private Timestamp createdAt;

//    public String getTime(){
//        return MyDateUtil.timestampFormat(createdAt);
//    }

    // 생성자 만들기 (필요한 것만)
    public Board(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }

    // 오브젝트 지향 프로그램이라서 업데이트 메서드를 만들어 주고 한번에 변경
    // 다른 곳에서 재사용하려면 DTO 이름을 적을 수 없다!
    public void update(BoardRequest.UpdateDTO reqDTO) {
        this.title = reqDTO.getTitle();
        this.content = reqDTO.getContent();
        this.username = reqDTO.getUsername();
    }
}
