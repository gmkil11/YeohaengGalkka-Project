package gazua.repositories;

import gazua.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long>, QuerydslPredicateExecutor<Room> {


    Optional<Room> findByRoomName(String roomName);

}

