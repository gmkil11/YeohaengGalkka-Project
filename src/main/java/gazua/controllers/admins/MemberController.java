package gazua.controllers.admins;

import gazua.commons.CommonProcess;
import gazua.commons.ScriptExceptionProcess;
import gazua.commons.menus.Menu;
import gazua.commons.menus.MenuDetail;
import gazua.entities.Member;
import gazua.repositories.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller("adminMemberController")
@RequestMapping("/admin/member")
@RequiredArgsConstructor
public class MemberController implements CommonProcess, ScriptExceptionProcess {

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final MemberRepository repository;



    @GetMapping
    public String list(Model model) {
        commonProcess("list", model);
        List<Member> memberList = repository.findAll();
        model.addAttribute("memberList", memberList);

        return "admin/member/list";
    }

    @PostMapping
    public String listPost(@RequestParam(name = "submitButton", required = false) String submitButton,@RequestParam String email, Model model){
        Optional<Member> member = repository.findByEmail(email);
        model.addAttribute("member", member.orElse(null)); // Optional을 null로 변환
        if ("delete".equals(submitButton)){
            return "redirect:/admin/member/delete";
        } else if ("role".equals(submitButton)){
            return "redirect:/admin/member/role";
        }
            return "redirect:/admin/member/list";
    }

    /**
     * 회원 삭제 양식
     * @param model
     * @return
     */
    @GetMapping("/delete")
    public String delete(Model model) {
        commonProcess("delete", model);
        return "admin/member/delete";
    }

    @PostMapping("/delete")
    @Transactional
    public String deletePost(@RequestParam(name = "email", required = false) String email, @RequestParam(name = "submitButton", required = false) String submitButton, Model model, HttpSession session) {
        commonProcess("delete", model);
        if("search".equals(submitButton)) {
            Optional<Member> member = repository.findByEmail(email);
            model.addAttribute("member", member.orElse(null)); // Optional을 null로 변환
            session.setAttribute("deleteMember", member.get());
            System.out.println("검색 버튼!");
        }
        if("delete".equals(submitButton)) {
            System.out.println("삭제 버튼!");
            Member member = (Member) session.getAttribute("deleteMember");
            System.out.println(member);
            repository.delete(member);
            session.removeAttribute("deleteMember");
        }

        return "admin/member/delete";
    }

    /**
     * 회원 권한 양식
     * @param model
     * @return
     */
    @GetMapping("/role")
    public String role(Model model) {
        commonProcess("role", model);
        return "admin/member/role";
    }

    @PostMapping("/role")
    public String rolePost(@RequestParam String email, Model model) {
        commonProcess("role", model);
        Optional<Member> member = repository.findByEmail(email);
        model.addAttribute("member", member.orElse(null)); // Optional을 null로 변환

        return "admin/member/role";
    }



    public void commonProcess(String mode, Model model) {

        String pageTitle = "회원 목록";

        if (mode.equals("delete")) {
            pageTitle = "회원 삭제";
        } else if (mode.equals("role")) {
            pageTitle = "회원 권한";
        }

        CommonProcess.super.commonProcess(model, pageTitle);

        model.addAttribute("menuCode", "member");

        String subMenuCode = Menu.getSubMenuCode(request);
        model.addAttribute("subMenuCode", subMenuCode);

        List<MenuDetail> submenus = Menu.gets("member");
        model.addAttribute("submenus", submenus);
    }
}
