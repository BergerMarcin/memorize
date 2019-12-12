package pl.coderslab.memorize.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.memorize.MemorizeApplication;
import pl.coderslab.memorize.dtos.WebAppParamDTO;
import pl.coderslab.memorize.services.WebAppParamService;

import static pl.coderslab.memorize.MemorizeApplication.webAppParamDTOMain;

@Controller
@RequestMapping({"/", "", "/home", "/index"})
public class HomePageController {

    // WebAppParamService DI via constructor
    private final WebAppParamService webAppParamService;
    public HomePageController(WebAppParamService webAppParamService) {
        this.webAppParamService = webAppParamService;
    }

    @GetMapping
    public String getHomePage(Model model) {
        //checking login user at console
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails);
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());

        //update main app parameters (user data mainly)
// TODO
//        webAppParamDTOMain = webAppParamService.readWebAppParamByUserName(
//                SecurityContextHolder.getContext().getAuthentication().getName());
//        model.addAttribute("webAppParamDTO", webAppParamDTOMain);
        return "index";
    }



//    public Model model;
//    // DI via constructor of Service
//    private final WebAppParamService webAppParamService;
//    public HomePageController(WebAppParamService webAppParamService) {
//        this.webAppParamService = webAppParamService;
//    }
//
//    @GetMapping
//    public String getHomePage(@ModelAttribute("webAppParamDTO") WebAppParamDTO webAppParamDTOModel, BindingResult result) {
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails);
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
//
//        // Checking if "webAppParamDTOModel" exists. If not then create and add to the model
//        if (webAppParamDTOModel == null || result.hasErrors()) {
//            WebAppParamDTO webAppParamDTO = webAppParamService.createWebAppParam(SecurityContextHolder.getContext().getAuthentication().getName());
//            model.addAttribute("webAppParamDTO", webAppParamDTO);
//        } else {
//            // Check if login user is the same in model - if not update required (create and add to the model)
//            if (! webAppParamDTOModel.getUserName().equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
//                WebAppParamDTO webAppParamDTO = webAppParamService.createWebAppParam(SecurityContextHolder.getContext().getAuthentication().getName());
//                model.addAttribute("webAppParamDTO", webAppParamDTO);
//            }
//        }
//        return "index";
//    }
}
