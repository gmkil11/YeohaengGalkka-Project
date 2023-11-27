package gazua.models.room.config;

import gazua.commons.ListData;
import gazua.controllers.rooms.SearchRoom;
import gazua.entities.FileInfo;
import gazua.entities.Room;
import gazua.models.file.FileInfoService;
import gazua.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomConfigInfoService {

    private final RoomRepository repository;
    private final FileInfoService infoService;

    public Room get(Long roomNum) {
        Room room = repository.findById(roomNum).orElseThrow(RoomNotFoundException::new);
        addFileInfo(room);

        return room;
    }

    public ListData<Room> getList(SearchRoom search) {

        return null;
    }

    private void addFileInfo(Room room) {
        String gid = room.getGid();
        List<FileInfo> mainImages = infoService.getListDone(gid, "main");
        List<FileInfo> listImages = infoService.getListDone(gid, "list");
        List<FileInfo> descImages = infoService.getListDone(gid, "desc");
        room.setMainImages(mainImages);
        room.setListImages(listImages);
        room.setDescImages(descImages);
    }
}