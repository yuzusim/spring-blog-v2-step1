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

}
