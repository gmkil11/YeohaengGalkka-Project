package gazua.repositories;

import gazua.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminUserRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByAdminId(String adminId);
}
