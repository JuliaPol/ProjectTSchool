package com.tsystems.ecare.controller.main;

import com.tsystems.ecare.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured({"ROLE_MANAGER","ROLE_CUSTOMER"})
public class MainController {

    @Autowired
    private Util util;

    @RequestMapping(value = "/")
    public String processGet() {

        if (util.isManager())
            return "redirect:/employee";
        else if (util.isCustomer())
            return "redirect:/customer";
        else
            return "redirect:/login";
    }
}

