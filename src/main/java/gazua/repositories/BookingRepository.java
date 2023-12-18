package gazua.repositories;

import gazua.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, String> {
}
