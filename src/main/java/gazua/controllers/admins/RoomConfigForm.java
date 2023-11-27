package gazua.controllers.admins;

import gazua.entities.FileInfo;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class RoomConfigForm {
    private String mode;

    private String gid = UUID.randomUUID().toString();

    private Long roomNum;        // 객실 번호

    @NotBlank(message = "사업자 이메일을 입력하세요.")
    private String sellerId;

    @NotBlank(message = "수용인원을 정하세요.")
    private String roomCount;   // 수용인원

    @NotBlank(message = "숙소 이름을 입력하세요.")
    private String roomName;      // 숙소 이름


    private String roomPr;      // 숙소 가격


    private LocalTime checkIn;  // 이용 시작 시간


    private String roomInfo;

    private List<FileInfo> mainImages; // 상품 메인 이미지

    private List<FileInfo> listImages; // 목록 이미지

    private List<FileInfo> descImages; // 상세 이미지

}
