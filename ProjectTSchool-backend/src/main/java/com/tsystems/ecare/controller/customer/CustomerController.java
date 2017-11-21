package com.tsystems.ecare.controller.customer;

import com.tsystems.ecare.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/customer")
@Secured("ROLE_CUSTOMER")
public class CustomerController {

    @Autowired
    private UserFacade userFacade;

//    @RequestMapping
//    public ModelAndView getUser(Principal principal) {
//        return new ModelAndView("/customer.jsp", "customerInfo", userFacade.findByLogin(principal.getName()));
//    }

    @RequestMapping(value = "/basket")
    public ModelAndView showBagPage(HttpServletRequest request) {
        return new ModelAndView("/basket.jsp",
                "basket", request.getSession().getAttribute("basket"));
    }
}
