package pl.coderslab.memorize.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.memorize.dtos.RegistrationDataDTO;
import pl.coderslab.memorize.services.RegistrationService;
import pl.coderslab.memorize.services.WebAppParamService;

import javax.validation.Valid;

import static pl.coderslab.memorize.MemorizeApplication.webAppParamDTOMain;

@Controller
@RequestMapping("/info")
public class InfoController {

    // WebAppParamService DI via constructor
    private final WebAppParamService webAppParamService;
    public InfoController(WebAppParamService webAppParamService) {
        this.webAppParamService = webAppParamService;
    }

    @GetMapping
    public String getInfoPage(Model model) {
//        model.addAttribute("infos", new InfoDTOService());

        //update main app parameters (mainly update of: user's actual level)
        webAppParamDTOMain = webAppParamService.readDBWebAppParamByUserName(
                SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("webAppParamDTO", webAppParamDTOMain);
        return "info-all";
    }
//    @PostMapping
//    public String postRegistrationPage(@ModelAttribute("registrationData")
//                                          @Valid RegistrationDataDTO registrationData,
//                                      BindingResult result) {
//        if (result.hasErrors()) {
//            return "registration/form";
//        }
//        // Zaimplementować zapis użytkownika
//        registrationService.register(registrationData);
//        return "redirect:/";
//    }

}
