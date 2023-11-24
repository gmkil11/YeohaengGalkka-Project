package gazua.controllers.admins;

import gazua.commons.CommonProcess;
import gazua.commons.ScriptExceptionProcess;
import gazua.commons.constants.MemberType;
import gazua.commons.menus.Menu;
import gazua.commons.menus.MenuDetail;
import gazua.controllers.admins.dtos.MemberAdminForm;
import gazua.entities.Member;
import gazua.models.member.MemberUpdateService;
import gazua.repositories.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
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
    private final MemberRepository repository;

    private final MemberUpdateService updateService;

    @GetMapping
    public String list(Model model) {
        commonProcess("list", model);
        List<Member> memberList = repository.findAll();
        model.addAttribute("memberList", memberList);

        return "admin/member/list";
    }

    @PostMapping("/update")
    public String listPs(MemberAdminForm form, Model model) {

        updateService.updates(form);

        // 수정 성공시에는 새로고침
        model.addAttribute("script", "parent.location.reload()");

        return "common/_execute_script";
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
        }
        if("delete".equals(submitButton)) {
            Member member = (Member) session.getAttribute("deleteMember");
            System.out.println(member);
            member.setActive(false);
            repository.saveAndFlush(member); // 변경 내용을 데이터베이스에 저장
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
    public String rolePost(@RequestParam(name = "email", required = false)String email,
                           @RequestParam(name = "submitButton", required = false) String submitButton,
                           @RequestParam(name = "mtype", required = false) String mtype,
                           Model model, HttpSession session) {
        commonProcess("role", model);
        if("search".equals(submitButton)) {
            Optional<Member> member = repository.findByEmail(email);
            model.addAttribute("member", member.orElse(null)); // Optional을 null로 변환
            session.setAttribute("editMember", member.get());
        }
        if("delete".equals(submitButton)) {
            Member member = (Member) session.getAttribute("editMember");
            if(mtype.equals("USER")) {member.setMtype(MemberType.USER);}
            else if(mtype.equals("ADMIN")) {member.setMtype(MemberType.ADMIN);}

            repository.saveAndFlush(member); // 변경 내용을 데이터베이스에 저장
            session.removeAttribute("editMember");
        }

        return "admin/member/role";
    }



    public void commonProcess(String mode, Model model) {

        String pageTitle = "회원 목록";

        if (mode.equals("delete")) {
            pageTitle = "활성화 여부";
        } else if (mode.equals("role")) {
            pageTitle = "회원 권한";
        }

        CommonProcess.super.commonProcess(model, pageTitle);

        if (mode.equals("list")) {
            model.addAttribute("memberTypes", MemberType.getList());
        }

        model.addAttribute("menuCode", "member");

        String subMenuCode = Menu.getSubMenuCode(request);
        model.addAttribute("subMenuCode", subMenuCode);

        List<MenuDetail> submenus = Menu.gets("member");
        model.addAttribute("submenus", submenus);
    }
}
