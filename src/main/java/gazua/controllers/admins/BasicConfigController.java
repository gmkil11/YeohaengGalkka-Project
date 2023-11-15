package gazua.controllers.admins;

import gazua.commons.configs.ConfigInfoService;
import gazua.commons.configs.ConfigSaveService;
import gazua.controllers.admins.dtos.ConfigForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminBasicConfig")
@RequestMapping("/admin/config")
@RequiredArgsConstructor
public class BasicConfigController {
    private final ConfigSaveService saveService;
    private final ConfigInfoService infoService;

    private final String code = "config";

    @GetMapping
    public String config(Model model) {
        commonProcess(model);
        ConfigForm configForm = infoService.get(code, ConfigForm.class);

        model.addAttribute("configForm", configForm == null ? new ConfigForm() : configForm);
        return "admin/basic/index";
    }

    @PostMapping
    public String configPs(ConfigForm configForm, Model model) {
        commonProcess(model);

        saveService.save(code, configForm);

        model.addAttribute("message", "설정이 저장되었습니다.");

        return "admin/basic/index";
    }

    private void commonProcess(Model model) {
        model.addAttribute("pageTitle", "사이트 설정");
        model.addAttribute("menuCode", code);
    }
}
