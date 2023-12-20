package gazua.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Booking {
    @Id
    private String bo_num; // 예약번호

    @ManyToOne // Many = Booking, One = Member 한명의 유저는 예약을 여러개 할 수 있다.
    @JoinColumn(name = "email", referencedColumnName = "email") // Member 엔터티의 email 컬럼을 외래키로 지정
    private Member member; // 예약자 이메일 -> Member FK

    @Column(length = 65  )
    private String ro_num; // 객실 번호

    @Column(length = 65  )
    private String bu_title; // 숙박업체 이름

    @Column(length = 12  )
    private String checkin; // 체크인 날짜

    @Column(length = 12  )
    private String checkout; // 체크아웃 날짜

    @Column(length = 10  )
    private String status; // 예약 상태

    @Column(length = 10  )
    private String price; // 결제 금액

    @Column(length = 65  )
    private LocalDateTime reg_date; // 결제한 날짜

}
