package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardNativeRepository boardNativeRepository;

    @GetMapping("/" )
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardNativeRepository.findAll();
        // List<Board> 실제로는 Board 객체가 아닌 boardDTO를 만들어서 줘야 함
        request.setAttribute("boardList", boardList);
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @PostMapping("/board/save")
    public String save(String title, String content, String username){
//        System.out.println("title : "+title);
//        System.out.println("content : "+content);
//        System.out.println("username : "+username);

        boardNativeRepository.save(title, content, username);
        return "redirect:/";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id) {
        return "board/detail";
    }
}
