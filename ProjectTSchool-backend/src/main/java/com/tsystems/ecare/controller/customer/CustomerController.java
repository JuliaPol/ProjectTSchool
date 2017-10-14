package com.tsystems.ecare.controller.customer;

import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.facade.UserFacade;
import com.tsystems.ecare.form.BasketForm;
import com.tsystems.ecare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@RestController
@RequestMapping("/customer")
@Secured("ROLE_CUSTOMER")
public class CustomerController {

    @Autowired
    private UserFacade userFacade;

    @RequestMapping
    public ModelAndView getUser(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("customerInfo", userFacade.findByLogin(principal.getName()));
        modelAndView.setViewName("/customer.jsp");
        return modelAndView;
    }
}
