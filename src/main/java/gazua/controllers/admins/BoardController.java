package gazua.controllers.admins;

import gazua.commons.ScriptExceptionProcess;
import gazua.commons.menus.Menu;
import gazua.commons.menus.MenuDetail;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

@Controller("adminBoardController")
@RequestMapping("/admin/board")
@RequiredArgsConstructor
public class BoardController implements ScriptExceptionProcess {

    private final HttpServletRequest request;

    @GetMapping
    public String index(Model model) {
        commonProcess(model, "list");
        return "admin/board/index";
    }

    private void commonProcess(Model model, String mode) {
        mode = Objects.requireNonNullElse(mode, "list");
        
        String pageTitle = "게시판 목록";

        if (mode.equals("register")) {
            pageTitle = "게시판 등록";
        } else if (mode.equals("update")) {
            pageTitle = "게시글 수정";
        } else if (mode.equals("posts")) {
            pageTitle = "게시글 관리";
        }

        model.addAttribute("menuCode", "board");

        // 서브 메뉴 처리
        String subMenuCode = Menu.getSubMenuCode(request);
        subMenuCode = mode.equals("update") ? "register" : subMenuCode;
        model.addAttribute("subMenuCode", subMenuCode);

        List<MenuDetail> submenus = Menu.gets("board");
        model.addAttribute("submenus", submenus);

        model.addAttribute("pageTitle", pageTitle);
    }
}
