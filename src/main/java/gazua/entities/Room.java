package gazua.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id @GeneratedValue
    private Long roomNum; // 숙소 번호

    private String sellerId; // 사업자 이메일

    @Column(length = 65, nullable = false)
    private String roomName;   // 숙소 이름

    @Column(nullable = false)
    private String roomCount;  // 이용 가능 인원

    @Column(length = 9, nullable = false)
    private String roomPr; // 객실 가격

    @Column(nullable = false)
    private LocalDateTime checkIn; // 체크인 시간

    @Column(nullable = false)
    private LocalDateTime checkOut; // 체크아웃 시간

    private String roomInfo;

    @Column(nullable = false)
    private boolean active = true; // 객실 활성화 여부
}