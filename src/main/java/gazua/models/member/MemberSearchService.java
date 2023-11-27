package gazua.models.member;

import gazua.entities.Member;
import gazua.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberSearchService {

    private final MemberRepository memberRepository;

    public List<Member> searchMembers(String query) {
        return MemberRepository.findByUserNmContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
