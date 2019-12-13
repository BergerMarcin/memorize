package pl.coderslab.memorize.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.memorize.services.WebAppParamService;

import static pl.coderslab.memorize.MemorizeApplication.webAppParamDTOMain;

@Controller
@RequestMapping("/login")
public class LoginController {

    // WebAppParamService DI via constructor
    private final WebAppParamService webAppParamService;
    public LoginController(WebAppParamService webAppParamService) {
        this.webAppParamService = webAppParamService;
    }

    @GetMapping
    public String prepareLoginPage(Model model) {
        //update main app parameters (mainly update of: user's actual level)
        webAppParamDTOMain = webAppParamService.readDBWebAppParamByUserName(
                SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("webAppParamDTO", webAppParamDTOMain);

        return "login";
    }

}
