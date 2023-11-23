package gazua.controllers.admins;

import gazua.commons.CommonProcess;
import gazua.commons.ScriptExceptionProcess;
import gazua.commons.menus.Menu;
import gazua.commons.menus.MenuDetail;
import gazua.entities.Member;
import gazua.entities.Room;
import gazua.repositories.RoomRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Optional;

@Controller("adminRoomController")
@RequestMapping("/admin/room")
@RequiredArgsConstructor
public class RoomController implements CommonProcess, ScriptExceptionProcess {

    private final HttpServletRequest request;
    private final RoomRepository repository;


    @GetMapping
    public String list(Model model) {
        commonProcess("list", model);
        List<Room> roomList = repository.findAll();
        model.addAttribute("roomList", roomList);
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

    @GetMapping("/edit")
    public String editRoom(Model model) {
        commonProcess("edit", model);
        return "admin/room/edit";
    }

    @GetMapping("/active")
    @Transactional
    public String activeRoom(Model model) {
        commonProcess("active", model);
        // 테스트용 객실 등록 시작
        Room room1 = Room.builder()
                .roomNum(1L)
                .sellerId("user01@test.org")
                .roomName("숙소명1")
                .roomCount("5")
                .roomPr("15000")
                .checkIn(LocalDateTime.now())
                .checkOut(LocalDateTime.now().plusDays(1))
                .active(true)
                .roomInfo("객실 정보1")
                .build();

        repository.saveAndFlush(room1);

        Room room2 = Room.builder()
                .roomNum(2L)
                .sellerId("user02@test.org")
                .roomName("숙소명2")
                .roomCount("10")
                .roomPr("50000")
                .checkIn(LocalDateTime.now().plusDays(2))
                .checkOut(LocalDateTime.now().plusDays(5))
                .active(true)
                .roomInfo("객실 정보2")
                .build();

        repository.saveAndFlush(room2);
        // 테스트용 개실 등록 끝

        return "admin/room/active";
    }

    @PostMapping("/active")
    @Transactional
    public String deletePost(@RequestParam(name = "email", required = false) String email, @RequestParam(name = "submitButton", required = false) String submitButton, Model model, HttpSession session) {
        commonProcess("delete", model);
        if("search".equals(submitButton)) {
            Optional<Room> room = repository.findBySellerId(email);
            model.addAttribute("room", room.orElse(null)); // Optional을 null로 변환
            session.setAttribute("deleteRoom", room.get());
        }
        if("delete".equals(submitButton)) {
            Room room = (Room) session.getAttribute("deleteRoom");
            System.out.println(room);
            room.setActive(false);
            repository.saveAndFlush(room); // 변경 내용을 데이터베이스에 저장
            session.removeAttribute("deleteRoom");
        }

        return "admin/room/active";
    }





    @PostMapping("/roomAdd")
    public String addRoom(Room room) {
        repository.save(room);
        return "redirect:/roomList"; // 객실 목록을 보여주는 페이지로 리다이렉트
    }

    public void commonProcess(String mode, Model model) {

        String pageTitle = "객실 목록";
        if (mode.equals("add")) {
            pageTitle = "객실 등록";
        } else if (mode.equals("delete")){
            pageTitle = "객실 삭제";
        } else if (mode.equals("edit")) {
            pageTitle = "객실 수정";
        }

        CommonProcess.super.commonProcess(model, pageTitle);

        model.addAttribute("menuCode", "room");

        String subMenuCode = Menu.getSubMenuCode(request);
        model.addAttribute("subMenuCode", subMenuCode);

        List<MenuDetail> submenus = Menu.gets("room");
        model.addAttribute("submenus", submenus);
    }
}