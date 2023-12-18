package gazua.controllers.mains;

import gazua.models.room.booking.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/saveBookingData")
    public ResponseEntity<String> saveBookingData(@RequestParam String roomName,
                                                  @RequestParam String email,
                                                  @RequestParam String userNm,
                                                  @RequestParam String mobile,
                                                  @RequestParam String totalPr) {
        try {
            // 여기서 BookingService를 통해 데이터를 저장하도록 호출
            bookingService.saveBookingData(roomName, email, userNm, mobile, totalPr);
            return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}