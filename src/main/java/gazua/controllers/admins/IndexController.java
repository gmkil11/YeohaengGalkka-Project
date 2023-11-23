package gazua.controllers.admins;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminIndexController")
@RequestMapping("/admin")
public class IndexController {
    @GetMapping
    public String index() {
        return "admin/main/index";
    }
}