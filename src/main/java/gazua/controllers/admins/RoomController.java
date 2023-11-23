package gazua.controllers.admins;

import gazua.commons.CommonProcess;
import gazua.commons.ScriptExceptionProcess;
import gazua.commons.menus.Menu;
import gazua.commons.menus.MenuDetail;
import gazua.entities.Room;
import gazua.repositories.RoomRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller("adminRoomController")
@RequestMapping("/admin/room")
@RequiredArgsConstructor
public class RoomController implements CommonProcess, ScriptExceptionProcess {

    private final HttpServletRequest request;

    private final RoomRepository repository;

    @GetMapping
    public String list(Model model) {
        commonProcess("list", model);
        return "admin/room/list";
    }

    /**
     * 객실 등록 양식
     *
     * @return
     */

    @GetMapping("/add")
    public String register(@ModelAttribute RoomConfigForm form, Model model) {
        commonProcess("add", model);

        return "admin/room/add";
    }
    @PostMapping("/save")
    public String save(@Valid RoomConfigForm form, Errors errors, Model model) {
        String mode = form.getMode();
        commonProcess(mode, model);

        if(errors.hasErrors()) {
            return "admin/room/" + mode;
        }



        return "admin/room" + mode;
    }

    public void commonProcess(String mode, Model model) {
        String pageTitle = "숙소 추가";
        mode = Objects.requireNonNullElse(mode,"list");
        if(mode.equals("add")) pageTitle = "숙소 등록";

        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("menuCode","room");
        model.addAttribute("submenus", Menu.gets("room")); // 하위 메뉴
        model.addAttribute("subMenuCode",Menu.getSubMenuCode(request)); //
    }
}