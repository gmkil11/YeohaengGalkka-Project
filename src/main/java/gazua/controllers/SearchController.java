package gazua.controllers;

import gazua.repositories.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    private final MemberRepository memberRepository;

    public SearchController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/admin/member")
    public String adminMemberList(@RequestParam(name = "search", required = false) String search, Model model) {
        if (search != null && !search.isEmpty()) {
            model.addAttribute("memberList", MemberRepository.findByUserNmContainingIgnoreCaseOrEmailContainingIgnoreCase(search, search));
        } else {
            model.addAttribute("memberList", memberRepository.findAll());
        }

        return "admin/memberList"; // 실제 뷰의 이름에 맞게 수정
    }
}
