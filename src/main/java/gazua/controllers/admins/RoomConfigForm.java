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
    private String mode; // 모드

    private String gid = UUID.randomUUID().toString(); // 그룹아이디

    private Long roomNum; // 객실 번호

    @NotBlank(message = "사업자 이메일을 입력하세요.")
    private String sellerId; // 판매자 이메일

    @NotBlank(message = "수용인원을 정하세요.")
    private String roomCount;   // 수용인원

    @NotBlank(message = "숙소 이름을 입력하세요.")
    private String roomName;      // 숙소 이름

    private String roomPr;      // 숙소 가격

    private String roomInfo1; // 객실 설명1

    private String roomInfo2; // 객실 설명2

    private String roomInfo3; // 객실 설명3

    private List<FileInfo> mainImages; // 상품 메인 이미지

    private List<FileInfo> listImages; // 목록 이미지

    private List<FileInfo> descImages; // 상세 이미지

}
