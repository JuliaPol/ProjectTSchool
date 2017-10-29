package com.tsystems.ecare.controller;

import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.facade.UserFacade;
import com.tsystems.ecare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Controller for {@link com.tsystems.ecare.entities.User}'s pages.
 *
 * @author Julia Polushina
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
//@Secured({"ROLE_CUSTOMER", "ROLE_MANAGER"})
public class UserController {
    @Autowired
    private UserFacade userFacade;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public UserDTO getCurrentUser(Principal principal) {
        return userFacade.findByLogin(principal.getName());
    }


    @RequestMapping(value = "/createNewAccount", method = RequestMethod.POST)
    public void createNewAccount(@RequestBody UserDTO userDTO) {
        userFacade.createCustomer(userDTO);
    }
}
