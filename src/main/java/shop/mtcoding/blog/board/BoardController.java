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
    private final BoardPersistRepository boardPersistRepository;

    @GetMapping("/")
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
    public String save(BoardRequest.SaveDTO reqDTO) { // 값을 받는 건 DTO로 받음
//        System.out.println("title : "+title);
//        System.out.println("content : "+content);
//        System.out.println("username : "+username);

        boardPersistRepository.save(reqDTO.toEntitiy()); // DTO 받아서 toEntity 메서드로 호출
        return "redirect:/";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable int id, HttpServletRequest request) {
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board", board);
        return "/board/update-form";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, String title, String content, String username){
//        System.out.println("id : " +id);
//        System.out.println("title : " +title);
//        System.out.println("content : " +content);
//        System.out.println("username : " +);

        return "redirect:/board/" + id;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id) {
        boardNativeRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        Board board = boardNativeRepository.findById(id);
        request.setAttribute("board", board);
        return "board/detail";
    }
}
