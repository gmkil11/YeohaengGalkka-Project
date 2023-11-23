package gazua.models.room.config;

import gazua.controllers.admins.RoomConfigForm;
import gazua.entities.Room;
import gazua.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class RoomConfigSaveService {
    private final RoomRepository roomRepository;

    public void save(RoomConfigForm form) {

        String roomName = form.getRoomName();
        String mode = form.getMode();
        Room room = null;
        if (mode.equals("edit") && StringUtils.hasText(roomName)) {
            room = roomRepository.findById(roomName).orElseThrow();
        } else {        // 추가
            room = new Room();
            room.setRoomName(roomName);
        }

        room.setRoomNum(form.getRoomNum());
        room.setSellerId(form.getSellerId());
        room.setRoomName(form.getRoomName());
        room.setRoomCount(form.getRoomCount());
        room.setRoomPr(form.getRoomPr());
        room.setCheckIn(form.getCheckIn());
        room.setCheckOut(form.getCheckOut());
        room.setRoomInfo(form.getRoomInfo());
    }
}