package gazua.controllers.mains;


import gazua.entities.Room;
import gazua.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class mainController {

    public final RoomRepository repository;

    @GetMapping
    public String index(Model model) {
        List<Room> roomList = repository.findAll();
        System.out.println(roomList);
        model.addAttribute("roomList", roomList);


        return "front/main/index";
    }
}
