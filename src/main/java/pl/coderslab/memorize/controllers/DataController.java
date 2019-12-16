package pl.coderslab.memorize.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.memorize.services.WebAppParamService;

import static pl.coderslab.memorize.MemorizeApplication.webAppParamDTOMain;

@Controller
@RequestMapping("/data")
public class DataController {

    // WebAppParamService DI via constructor
    private final WebAppParamService webAppParamService;
    public DataController(WebAppParamService webAppParamService) {
        this.webAppParamService = webAppParamService;
    }

    @GetMapping
    public String getDataPage(Model model) {
//        model.addAttribute("datas", new DataDTOService());

        //update main app parameters (mainly update of: user's actual level)
        webAppParamDTOMain = webAppParamService.readDBWebAppParamByUserName(
                SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("webAppParamDTO", webAppParamDTOMain);
        return "data-all";
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
