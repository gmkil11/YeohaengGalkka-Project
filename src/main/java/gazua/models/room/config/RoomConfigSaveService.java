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
            room.setGid(form.getGid()); // 그룹 아이디
            room.setRoomName(form.getRoomName()); // 객실 이름
            room.setSellerId(form.getSellerId()); // 판매자 아이디
            room.setRoomCount(form.getRoomCount()); // 수용 인원
            room.setRoomPr(form.getRoomPr()); // 객실 가격
            room.setRoomInfo1(form.getRoomInfo1()); // 객실 정보1
            room.setRoomInfo2(form.getRoomInfo2()); // 객실 정보2
            room.setRoomInfo3(form.getRoomInfo3()); // 객실 정보3
            room.setActive(true); // 객실 활성화 설정

            repository.saveAndFlush(room);
        }
    }
}