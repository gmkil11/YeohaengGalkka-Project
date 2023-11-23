package gazua.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    private Long roomNum; // 숙소 번호

    private String sellerId;

    @Id @GeneratedValue
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
}