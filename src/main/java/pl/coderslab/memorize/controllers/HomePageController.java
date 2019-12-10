package pl.coderslab.memorize.controllers;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "", "/home", "/index"})
public class HomePageController {

    @GetMapping
    public String getHomePage() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails);
        return "index";
    }
}
