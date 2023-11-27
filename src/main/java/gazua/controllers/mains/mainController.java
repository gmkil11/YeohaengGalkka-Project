package gazua.controllers.mains;


import gazua.entities.FileInfo;
import gazua.entities.Room;
import gazua.models.room.config.RoomConfigInfoService;
import gazua.repositories.FileInfoRepository;
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
    public final FileInfoRepository fileInfoRepository;
    public final RoomConfigInfoService roomConfigInfoService;

    @GetMapping
    public String index(Model model) {
        List<Room> roomList = repository.findAll();

        roomList.forEach(room -> roomConfigInfoService.get(room.getRoomNum()));

        System.out.println("객실!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+roomList);

        model.addAttribute("roomList", roomList);


        return "front/main/index";
    }
}
