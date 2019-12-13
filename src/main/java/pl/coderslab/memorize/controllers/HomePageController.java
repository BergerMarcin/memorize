package pl.coderslab.memorize.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.memorize.MemorizeApplication;
import pl.coderslab.memorize.services.WebAppParamService;

import static pl.coderslab.memorize.MemorizeApplication.webAppParamDTOMain;

@Controller
@RequestMapping({"/", "", "/home", "/index"})
@Slf4j
public class HomePageController {

    // WebAppParamService DI via constructor
    private final WebAppParamService webAppParamService;
    public HomePageController(WebAppParamService webAppParamService) {
        this.webAppParamService = webAppParamService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        //checking login user at console
        log.debug("Login?: {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails);
        log.debug("User name: {}", SecurityContextHolder.getContext().getAuthentication().getName());

        //update main app parameters (mainly update of: user's actual level)
        webAppParamDTOMain = webAppParamService.readDBWebAppParamByUserName(
                SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("webAppParamDTO", webAppParamDTOMain);
        return "index";
    }
}
