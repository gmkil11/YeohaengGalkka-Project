package gazua.controllers.admins;

import gazua.commons.CommonProcess;
import gazua.commons.ScriptExceptionProcess;
import gazua.commons.menus.Menu;
import gazua.commons.menus.MenuDetail;
import gazua.entities.Member;
import gazua.repositories.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller("adminRoomController")
@RequestMapping("/admin/room")
@RequiredArgsConstructor
public class RoomController implements CommonProcess, ScriptExceptionProcess {

    private final HttpServletRequest request;


    @GetMapping
    public String list(Model model) {
        commonProcess("list", model);
        return "admin/room/list";
    }

    /**
     * 객실 등록 양식
     * @return
     */
    @GetMapping("/add")
    public String addRoom(Model model) {
        commonProcess("add", model);
        return "admin/room/add";
    }


    public void commonProcess(String mode, Model model) {

        String pageTitle = "객실 목록";
        if (mode.equals("add")) {
            pageTitle = "객실 등록";
        }

        CommonProcess.super.commonProcess(model, pageTitle);

        model.addAttribute("menuCode", "room");

        String subMenuCode = Menu.getSubMenuCode(request);
        model.addAttribute("subMenuCode", subMenuCode);

        List<MenuDetail> submenus = Menu.gets("room");
        model.addAttribute("submenus", submenus);
    }
}
