package gazua.entities;

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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_seq_generator")
    @SequenceGenerator(name = "room_seq_generator", sequenceName = "ROOM_SEQ", allocationSize = 1)
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

    private String roomInfo1;

    private String roomInfo2;

    @Lob
    private String roomInfo3;

    private boolean active; // 객실 활성화 유무


    @Transient
    private List<FileInfo> mainImages; // 상품 메인 이미지

    @Transient
    private List<FileInfo> listImages; // 목록 이미지

    @Transient
    private List<FileInfo> descImages; // 상세 이미지
}
