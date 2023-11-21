package gazua.commons.menus;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 메뉴와 관련된 유틸리티 클래스.
 */
public class Menu {

    /**
     * 주어진 코드에 따라 메뉴 목록을 반환.
     *
     * @param code 메뉴 코드
     * @return 주어진 메뉴 코드에 따른 메뉴 목록
     */
    public static List<MenuDetail> gets(String code) {
        List<MenuDetail> menus = new ArrayList<>();
        
        if (code.equals("member")) { // 회원 하위 메뉴
            menus.add(new MenuDetail("member", "회원 목록", "/admin/member"));
            menus.add(new MenuDetail("delete", "회원 삭제", "/admin/member/delete"));
            menus.add(new MenuDetail("role", "회원 권한", "/admin/member/role"));

        } else if (code.equals("board")) { // 게시판 하위 메뉴
            menus.add(new MenuDetail("board", "게시판 목록", "/admin/board"));
            menus.add(new MenuDetail("register", "게시판 등록", "/admin/board/add"));
            menus.add(new MenuDetail("posts", "게시글 관리", "/admin/board/posts"));
        } else if (code.equals("room")) {
            menus.add(new MenuDetail("room", "객실 목록", "/admin/room"));
            menus.add(new MenuDetail("add", "객실 등록", "/admin/room/add" ));
            menus.add(new MenuDetail("category", "객실 분류", "/admin/room/category"));
        }

        return menus;
    }

    /**
     * 주어진 요청에 대한 하위 메뉴 코드를 반환.
     *
     * @param request HttpServletRequest 객체
     * @return 요청에 대한 하위 메뉴 코드
     */
    public static String getSubMenuCode(HttpServletRequest request) {
        String URI = request.getRequestURI();

        return URI.substring(URI.lastIndexOf('/') + 1);
    }
}
