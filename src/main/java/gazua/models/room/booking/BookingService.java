package gazua.models.room.booking;

import gazua.entities.Booking;
import gazua.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public void saveBookingData(Booking booking) {
        System.out.println("Saving booking data: " + booking.toString());
        bookingRepository.saveAndFlush(booking);
    }
}
