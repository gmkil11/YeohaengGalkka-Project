package gazua.controllers.admins;

import gazua.commons.CommonProcess;
import gazua.commons.ScriptExceptionProcess;
import gazua.commons.menus.Menu;
import gazua.commons.menus.MenuDetail;
import gazua.entities.Member;
import gazua.repositories.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller("adminMemberController")
@RequestMapping("/admin/member")
@RequiredArgsConstructor
public class MemberController implements CommonProcess, ScriptExceptionProcess {

    private final HttpServletRequest request;
    private final MemberRepository repository;



    @GetMapping
    public String list(Model model) {
        commonProcess("list", model);
        List<Member> memberList = repository.findAll();
        model.addAttribute("memberList", memberList);

        return "admin/member/list";
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
