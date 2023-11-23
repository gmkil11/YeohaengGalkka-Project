package gazua.controllers.admins;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class RoomConfigForm {
    private String mode;

    private Long roomNum;        // 숙소 번호

    @NotBlank(message = "사업자 이메일을 입력하세요.")
    private String sellerId;

    @NotBlank(message = "수용인원을 정하세요.")
    private String roomCount;   // 수용인원

    @NotBlank(message = "숙소 이름을 입력하세요.")
    private String roomName;      // 숙소 이름

    private String roomPr;      // 숙소 가격

    private LocalDateTime checkIn;      // 체크인 시간

    private LocalDateTime checkOut;     // 체크아웃 시간

    private String roomInfo;
}