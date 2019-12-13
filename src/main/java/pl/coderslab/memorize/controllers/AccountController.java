package pl.coderslab.memorize.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.memorize.services.WebAppParamService;

import java.security.Principal;

import static pl.coderslab.memorize.MemorizeApplication.webAppParamDTOMain;

@Controller
@RequestMapping("/user")
public class AccountController {

    // WebAppParamService DI via constructor
    private final WebAppParamService webAppParamService;
    public AccountController(WebAppParamService webAppParamService) {
        this.webAppParamService = webAppParamService;
    }

    // principal - zwraca użytkownika zweryfikowanego
    @GetMapping
    public String prepareAccountPage(Model model) {
        //update main app parameters (mainly update of: user's actual level)
        webAppParamDTOMain = webAppParamService.readDBWebAppParamByUserName(
                SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("webAppParamDTO", webAppParamDTOMain);

        return "user/account";
    }
}
