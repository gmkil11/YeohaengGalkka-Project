package gazua.models.room.config;

import com.querydsl.core.BooleanBuilder;
import gazua.commons.ListData;
import gazua.commons.Pagination;
import gazua.commons.Utils;
import gazua.controllers.rooms.SearchRoom;
import gazua.entities.FileInfo;
import gazua.entities.Room;
import gazua.models.file.FileInfoService;
import gazua.repositories.RoomRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class RoomConfigInfoService {

    private final RoomRepository repository;
    private final FileInfoService infoService;
    private final HttpServletRequest request;

    // 숙소 상세 정보 조회
    public Room get(Long roomNum) {
        // 숙소 번호를 이용해 숙소를 찾고, 없으면 RoomNotFoundException을 던집니다.
        Room room = repository.findById(roomNum).orElseThrow(RoomNotFoundException::new);
        // 숙소에 파일 정보를 추가합니다.
        addFileInfo(room);

        return room;
    }

    // 숙소 목록 조회
    public ListData<Room> getList(SearchRoom search) {
        // QueryDSL을 사용하여 동적 쿼리를 생성하기 위한 빌더
        BooleanBuilder andBuilder = new BooleanBuilder();
        int page = Utils.getNumber(search.getPage(), 1);
        int limit = Utils.getNumber(search.getLimit(), 20);

        // 페이징 및 정렬 설정
        Pageable pageable = PageRequest.of(page-1, limit, Sort.by(desc("createdAt")));

        // 숙소 목록을 페이징하여 가져옵니다.
        Page<Room> data = repository.findAll(andBuilder, pageable);
        // 페이징 정보를 생성합니다.
        Pagination pagination = new Pagination(page, (int)data.getTotalElements(), 10, limit, request);

        // 가져온 숙소 목록에 파일 정보를 추가합니다.
        data.getContent().forEach(this::addFileInfo);

        // ListData에 데이터와 페이징 정보를 설정하여 반환합니다.
        ListData<Room> listData = new ListData<>();
        listData.setContent(data.getContent());
        listData.setPagination(pagination);
        return listData;
    }

    // 숙소에 파일 정보 추가
    private void addFileInfo(Room room) {
        // 숙소의 고유 식별자를 이용해 해당 숙소의 다양한 종류의 파일 정보를 가져옵니다.
        String gid = room.getGid();
        List<FileInfo> mainImages = infoService.getListDone(gid, "main");
        List<FileInfo> listImages = infoService.getListDone(gid, "list");
        List<FileInfo> descImages = infoService.getListDone(gid, "desc");
        // 가져온 파일 정보를 숙소에 설정합니다.
        room.setMainImages(mainImages);
        room.setListImages(listImages);
        room.setDescImages(descImages);
    }
}
