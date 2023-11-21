package gazua.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    private String bo_num; // 예약번호

    @ManyToOne // Many = Booking, One = Member 한명의 유저는 예약을 여러개 할 수 있다.
    @JoinColumn(name = "email") //foreign key (email) references Member (email)
    @Column(length = 65, nullable = false)
    private Member member; // 예약자 이메일 -> Member FK

    @Column(length = 65, nullable = false)
    private String ro_num; // 객실 번호

    @Column(length = 65, nullable = false)
    private String bu_title; // 숙박업체 이름

    @Column(length = 30, nullable = false)
    private String ro_name; // 객실 이름

    @Column(length = 10, nullable = false)
    private LocalDateTime checkin; // 체크인 날짜

    @Column(length = 10, nullable = false)
    private LocalDateTime checkout; // 체크아웃 날짜

    @Column(length = 10, nullable = false)
    private String status; // 예약 상태

    @Column(length = 30, nullable = false)
    private String payment; // 결제 수단

    @Column(length = 10, nullable = false)
    private String price; // 결제 금액

    @Column(length = 65, nullable = false)
    private String reg_date; // 결제한 날짜
}
