package gazua.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id @GeneratedValue
    private Long roomNum; // 숙소 번호

    @Column(length=65, nullable = false)
    private String gid = UUID.randomUUID().toString();

    private String sellerId;

    @Column(length = 65, nullable = false)
    private String roomName;   // 숙소 이름

    @Column(nullable = false)
    private String roomCount;  // 이용 가능 인원

    @Column(length = 9, nullable = false)
    private String roomPr; // 객실 가격

//    @Column(nullable = false)
    @Column(updatable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkIn; // 체크인 시간

//    @Column(nullable = false)
    private LocalDateTime checkOut; // 체크아웃 시간

    private String roomInfo;

    private boolean active; // 객실 활성화 유무


    @Transient
    private List<FileInfo> mainImages; // 상품 메인 이미지

    @Transient
    private List<FileInfo> listImages; // 목록 이미지

    @Transient
    private List<FileInfo> descImages; // 상세 이미지
}
