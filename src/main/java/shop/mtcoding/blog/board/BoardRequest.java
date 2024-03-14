package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO{
        private String title;
        private String content;
        private String username;

        // DTO를 Entity로 바꾸는 메서드 만들기 인서트 할때만 필요, 3개만 들고
        // 다른 DTO에서 만들 필요가 없음
        public Board toEntitiy(){ // 비영속 객체 동기화 되지 않음, 인서트 할때 바뀜
            return new Board(title, content, username);
        }

    }
}
