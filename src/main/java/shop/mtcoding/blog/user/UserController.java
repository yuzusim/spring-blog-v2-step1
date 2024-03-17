package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserRepository userRepository;
    private final HttpSession session;



    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDTO){
        User sessionUser = userRepository.save(reqDTO.toEntity());
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO) {
        User sessionUser = userRepository.findByUsernameAndPassword(reqDTO);

//        if (sessionUser == null) {
//            return "redirect:/login-form";
//        }

        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

//    @PostMapping("/user/update")
//    public String update(String password) {
//        return "redirect:/";
//    }

    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userRepository.updateById(sessionUser.getId(), reqDTO.getPassword(), reqDTO.getEmail());
        session.setAttribute("sessionUser", newSessionUser);
        return "redirect:/";
    }

    @GetMapping("/user/update-form")
    public String updateForm(HttpServletRequest request) {
        // 세션에서 사용자 정보를 가져와서 해당 사용자의 세션 정보를 확인하는 용도로 사용
        User sessionUser = (User) session.getAttribute("sessionUser");

        // id는 세션에서 들고 오자!
        // 세션이 있는 정보를 머스태치에 뿌리니까 해당 유저의 정보만 나오겠지!
        User user = userRepository.findById(sessionUser.getId());
        request.setAttribute("user", user);
        return "user/update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate(); // 세션(session)을 무효화(invalidate)하는 작업을 수행
        return "redirect:/";
    }
}

