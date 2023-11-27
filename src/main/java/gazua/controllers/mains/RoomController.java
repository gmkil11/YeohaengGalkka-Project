package gazua.controllers.mains;

import gazua.entities.Room;
import gazua.models.room.config.RoomConfigInfoService;
import gazua.repositories.FileInfoRepository;
import gazua.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    public final RoomRepository repository;
    public final FileInfoRepository fileInfoRepository;
    public final RoomConfigInfoService roomConfigInfoService;

    @GetMapping("/{roomNum}")
    public String viewRoom(@PathVariable Long roomNum, Model model) {

        Room room = roomConfigInfoService.get(roomNum);


        System.out.println(room);
        model.addAttribute("room", room);


        return "front/room/room";
    }
}
