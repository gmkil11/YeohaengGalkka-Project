package gazua.controllers.members;

import gazua.commons.CommonProcess;
import gazua.commons.MemberUtil;
import gazua.commons.Utils;
import gazua.controllers.members.dtos.RequestJoin;
import gazua.entities.Member;
import gazua.models.member.MemberInfoService;
import gazua.models.member.MemberSaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController implements CommonProcess {

    private final Utils utils;
    private final MemberUtil memberUtil;
    private final MemberSaveService saveService;
    private final MemberInfoService memberInfoService;  // 추가: MemberInfoService 주입

    @GetMapping("/join")
    public String join(@ModelAttribute RequestJoin form, Model model) {
        commonProcess(model, "회원가입");

        return utils.tpl("member/join");
    }

    @PostMapping("/join")
    public String joinPs(@Valid RequestJoin form, Errors errors, Model model) {
        commonProcess(model, "회원가입");

        saveService.join(form, errors);

        if (errors.hasErrors()) {
            return utils.tpl("member/join");
        }

        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login(String redirectURL, Model model) {
        commonProcess(model, "로그인");

        model.addAttribute("redirectURL", redirectURL);

        return utils.tpl("member/login");
    }

    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) {
        List<Member> searchResults = memberInfoService.searchMembers(query);
        model.addAttribute("members", searchResults);
        return "member/searchResults";
    }
}
