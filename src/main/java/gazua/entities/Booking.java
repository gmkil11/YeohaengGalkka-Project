package gazua.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.swing.*;
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

    @Column(length = 65, nullable = false)
    private String ro_num; // 객실 번호

    @Column(length = 65, nullable = false)
    private String bu_title; // 숙박업체 이름

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

    @PrePersist
    public void generateBookingNumber() {
        // 현재 날짜를 이용하여 예약 번호 생성
        String currentDate = LocalDateTime.now().toString().replaceAll("[^0-9]", "");

        // 랜덤 숫자 4자리 생성
        String randomDigits = String.format("%04d", new Random().nextInt(10000));

        // 예약 번호 설정 (현재 날짜 + 랜덤 숫자 4자리)
        this.bo_num = currentDate + randomDigits;
    }
}
