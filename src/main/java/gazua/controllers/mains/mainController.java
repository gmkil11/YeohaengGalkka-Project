package gazua.controllers.mains;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {

    @GetMapping
    public String index() {

        return "front/main/index";
    }
}
