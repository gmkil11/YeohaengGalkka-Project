package gazua.controllers.admins;

import gazua.commons.CommonProcess;
import gazua.commons.ScriptExceptionProcess;
import gazua.commons.menus.Menu;
import gazua.commons.menus.MenuDetail;
import gazua.entities.Room;
import gazua.models.room.config.RoomConfigSaveService;
import gazua.repositories.RoomRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller("adminRoomController")
@RequestMapping("/admin/room")
@RequiredArgsConstructor
public class RoomController implements CommonProcess, ScriptExceptionProcess {

    private final HttpServletRequest request;

    private final RoomConfigSaveService saveService;

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
    public String addRoom(Model model) {
        commonProcess("add", model);
        model.addAttribute("room",new Room());
        return "admin/room/add";
    }


    @PostMapping("/save")
    public String addRoom(@Valid RoomConfigForm form, Errors errors, Model model) {
        String mode = form.getMode();
        commonProcess(mode, model);
        if (errors.hasErrors()){
            return "admin/room/" + mode;
        }

        saveService.save(form);

        return "redirect:/admin/room"; // 객실 목록을 addRoom으로 옮기고 DB에도 반영
    }

    @GetMapping("/edit/{roomNum}")
    @Transactional
    public String editRoom(@PathVariable String roomNum, Model model) {
        commonProcess("edit", model);
        Room room = Room.builder()
                .roomNum(1L)
                .sellerId("user01@test.org")
                .roomName("숙소명1")
                .roomCount("5")
                .roomPr("15000")
                .checkIn(LocalDateTime.now())
                .checkOut(LocalDateTime.now().plusDays(1))
                .active(true)
                .roomInfo("객실 정보")
                .build();

        repository.saveAndFlush(room);

        return "admin/room/edit";
    }

    public void commonProcess(String mode, Model model) {

        String pageTitle = "객실 목록";


        if (mode.equals("add")) {
            pageTitle = "객실 등록";}
        else if (mode.equals("edit")) {
            pageTitle = "객실 수정";}


            model.addAttribute("pageTitle", pageTitle);
            model.addAttribute("menuCode", "room");

            String subMenuCode = Menu.getSubMenuCode(request);
            model.addAttribute("subMenuCode", subMenuCode);

            List<MenuDetail> submenus = Menu.gets("room");
            model.addAttribute("submenus", submenus);
        }

    }