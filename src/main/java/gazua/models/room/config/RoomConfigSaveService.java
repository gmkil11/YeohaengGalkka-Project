package gazua.models.room.config;

import gazua.controllers.admins.RoomConfigForm;
import gazua.entities.Room;
import gazua.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class RoomConfigSaveService {

   private final RoomRepository repository;

    public void save(RoomConfigForm form) {
        String roomName = form.getRoomName();
        String mode = form.getMode();
        Room room = null;
        if (mode.equals("edit") && StringUtils.hasText(roomName)) {
            room = repository.findByRoomName(roomName).orElseThrow();
        } else {        // 추가
            room = new Room();
            room.setGid(form.getGid());
            room.setRoomName(form.getRoomName());
            room.setRoomNum(form.getRoomNum());
            room.setSellerId(form.getSellerId());
            room.setRoomCount(form.getRoomCount());
            room.setRoomPr(form.getRoomPr());
            //room.setCheckIn(form.getCheckIn());
            room.setRoomInfo(form.getRoomInfo());
            room.setActive(true);
            room.setMainImages(form.getMainImages());
            room.setListImages(form.getListImages());
            room.setDescImages(form.getDescImages());

            repository.saveAndFlush(room);
        }
    }
}
