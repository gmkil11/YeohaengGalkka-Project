package gazua.controllers.mains;

import gazua.entities.Booking;
import gazua.entities.Member;
import gazua.models.member.MemberInfoService;
import gazua.models.room.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private MemberInfoService memberInfoService;

    @PostMapping(value = "/saveBookingData", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    public String saveBookingData(@RequestParam String bo_num,
                                  @RequestParam String roomName,
                                  @RequestParam String userNm,
                                  @RequestParam String checkin,
                                  @RequestParam String checkout,
                                  @RequestParam String roomNum,
                                  @RequestParam String price) {

        Member member = memberInfoService.findByUserNm(userNm);

        Booking booking = Booking.builder()
                .bo_num(bo_num)
                .bu_title(roomName)
                .member(member)
                .checkin(checkin)
                .status("예약 완료")
                .checkout(checkout)
                .price(price)
                .reg_date(LocalDateTime.now())
                .ro_num(roomNum)
                .build();

        System.out.println(booking.toString() + booking + "booking입니다");

        try {
            bookingService.saveBookingData(booking);
            return "예약 데이터가 성공적으로 저장되었습니다";
        } catch (Exception e) {
            return "예약 데이터 저장 중 오류 발생: " + e.getMessage();
        }
    }
}