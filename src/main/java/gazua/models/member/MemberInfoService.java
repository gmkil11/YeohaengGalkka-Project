package gazua.models.member;

import gazua.entities.Member;
import gazua.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberInfoService {

    private final MemberRepository memberRepository;

    public List<Member> searchMembers(String query) {
        // 회원 검색 로직을 구현
        // 예시: 이름 또는 이메일이 검색어에 포함되는 회원을 찾음
        return memberRepository.findByUserNmContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query);
    }
}
