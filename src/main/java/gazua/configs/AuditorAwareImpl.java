package gazua.configs;

import gazua.models.member.MemberInfo;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String email = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof MemberInfo) {
            MemberInfo memberInfo = (MemberInfo)auth.getPrincipal();
            email = memberInfo.getMember().getEmail();
        }

        return Optional.ofNullable(email);
    }
}
