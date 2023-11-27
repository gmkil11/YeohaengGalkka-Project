package gazua.repositories;

import gazua.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {
    Optional<Member> findByEmail(String email);

        boolean existsByEmail(String email);

        // 검색을 처리하는 쿼리메서드
        List<Member> findByUserNmContainingIgnoreCaseOrEmailContainingIgnoreCase (String userNm, String email);
    }
