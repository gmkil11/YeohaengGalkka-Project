package gazua.repositories;

import gazua.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface RoomRepository extends JpaRepository<Room, String>, QuerydslPredicateExecutor<Room> {
}
