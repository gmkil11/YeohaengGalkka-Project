package gazua.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

//@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id @GeneratedValue
    private Long room_No; // 숙소 번호

    @Column(length = 65, nullable = false)
    private String room_Name;   // 숙소 이름

    @Column(nullable = false)
    private String room_Count;  // 이용 가능 인원

    @Column(length = 9, nullable = false)
    private String room_Price; // 객실 가격

    @Column(nullable = false)
    private LocalDateTime checkIn; // 체크인 시간

    @Column(nullable = false)
    private LocalDateTime checkOut; // 체크아웃 시간
}
