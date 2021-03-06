package pl.coderslab.memorize.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import pl.coderslab.memorize.dtos.RegistrationDataDTO;
import pl.coderslab.memorize.services.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.memorize.services.WebAppParamService;

import javax.validation.Valid;

import static pl.coderslab.memorize.MemorizeApplication.webAppParamDTOMain;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    // WebAppParamService DI via constructor
    private final WebAppParamService webAppParamService;
    private final RegistrationService registrationService;
    public RegistrationController(WebAppParamService webAppParamService, RegistrationService registrationService) {
        this.webAppParamService = webAppParamService;
        this.registrationService = registrationService;
    }

    @GetMapping
    public String getRegistrationPage(Model model) {
        model.addAttribute("registrationData", new RegistrationDataDTO());

        //update main app parameters (mainly update of: user's actual level)
        webAppParamDTOMain = webAppParamService.readDBWebAppParamByUserName(
                SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("webAppParamDTO", webAppParamDTOMain);
        return "registration/form";
    }
    @PostMapping
    public String postRegistrationPage(@ModelAttribute("registrationData")
                                          @Valid RegistrationDataDTO registrationData,
                                      BindingResult result) {
        if (result.hasErrors()) {
            return "registration/form";
        }
        // Zaimplementować zapis użytkownika
        registrationService.register(registrationData);
        return "redirect:/";
    }

}
