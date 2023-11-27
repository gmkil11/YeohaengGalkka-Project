package gazua.repositories;

import gazua.entities.Member;
import gazua.entities.QMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {
    Optional<Member> findByEmail(String email);

    /**
     * 등록된 회원 여부 체크
     *
     * @param email
     * @return
     */
    default boolean exists(String email) {
        return exists(QMember.member.email.eq(email));
    }


    boolean existsByEmail(String email);

        // 검색을 처리하는 쿼리메서드
        List<Member> findByUserNmContainingIgnoreCaseOrEmailContainingIgnoreCase (String userNm, String email);
    }


