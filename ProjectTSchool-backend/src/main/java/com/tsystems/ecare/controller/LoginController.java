package com.tsystems.ecare.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login.jsp";
    }
//
//    @RequestMapping(value = "/customer", method = RequestMethod.GET)
//    public String customer() {
//        return "customer";
//    }

    @Secured("ROLE_MANAGER")
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String employee() {
        return "index.html";
    }

    @Secured("ROLE_MANAGER")
    @RequestMapping(value = "/contract-form", method = RequestMethod.GET)
    public String contractForm() {
        return "index.html";
    }
}