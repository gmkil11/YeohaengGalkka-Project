package gazua.models.member;

import gazua.entities.Member;
import gazua.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberInfoService implements UserDetailsService {

    private final MemberRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

        List<GrantedAuthority> authorities
                = Arrays.asList(new SimpleGrantedAuthority(member.getMtype().name()));


        return MemberInfo.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .authorities(authorities)
                .member(member)
                .build();
    }

    public List<Member> searchMembers(String query) {
        // 회원 검색 로직을 구현
        // 예시: 이름 또는 이메일이 검색어에 포함되는 회원을 찾음
        return repository.findByUserNmContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query);
    }

    public Member findByUserNm(String userNm) {
        // 적절한 로직을 통해 Member를 찾아서 반환하는 코드
        // 예를 들어, repository.findByEmail(email) 등을 사용할 수 있습니다.
        return repository.findByUserNm(userNm);
    }

}
