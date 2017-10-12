package com.tsystems.ecare.controller.main;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@Secured({"ROLE_MANAGER","ROLE_CUSTOMER"})
public class MainController {

    @RequestMapping(value = "/")
    public String processGet() {

        if (userInRole("ROLE_MANAGER"))
            return "redirect:/employee";
        else if (userInRole("ROLE_CUSTOMER"))
            return "redirect:/customer";
        else
            return "redirect:/login";
    }

    private boolean userInRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream().filter(ga ->
                Objects.equals(ga.getAuthority(), role)).findAny().isPresent();
    }
}

