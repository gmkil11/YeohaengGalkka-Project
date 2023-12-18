package gazua.models.room.booking;

import gazua.entities.Booking;
import gazua.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public void saveBookingData(String roomName, String email, String userNm, String mobile, String totalPr) {
        // 이 부분에서 Booking 엔티티를 생성하여 데이터베이스에 저장
        Booking booking = new Booking();
        // Set 필요한 데이터
        bookingRepository.save(booking);
    }
}
