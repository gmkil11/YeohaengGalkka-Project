package gazua.controllers.mains;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/room")
public class RoomController {

    // 변경 해야함 !!!
    @GetMapping
    public String viewRoom(Model model) {

        return "front/room/room";
    }
}
