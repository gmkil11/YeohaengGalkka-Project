package gazua.models.member;

import gazua.commons.Utils;
import gazua.commons.constants.MemberType;
import gazua.commons.exceptions.AlertException;
import gazua.controllers.admins.dtos.MemberAdminForm;
import gazua.entities.Member;
import gazua.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberUpdateService {

    private final Utils utils;
    private final MemberRepository repository;

    /**
     * 목록 수정
     */
    public void updates(MemberAdminForm form) {
        List<Long> userNos = form.getUserNos();

        if (userNos == null || userNos.isEmpty()) {
            throw new AlertException("수정할 회원을 선택하세요.");
        }

        for(long userNo : userNos) {
            Member member = repository.findById(userNo).orElse(null);
            if (member == null) continue;

            boolean active = Boolean.parseBoolean(utils.getParam("active_" + userNo));
            MemberType mtype = MemberType.valueOf(utils.getParam("mtype_" + userNo));

            member.setActive(active);
            member.setMtype(mtype);
        }

        repository.flush();
    }
}
