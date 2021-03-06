package com.tsystems.ecare.controller;

import com.tsystems.ecare.dto.CustomerDTO;
import com.tsystems.ecare.dto.UserWithPasswordDTO;
import com.tsystems.ecare.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
public class UserController {
    @Autowired
    private UserFacade userFacade;

    @Secured({"ROLE_CUSTOMER", "ROLE_MANAGER"})
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public CustomerDTO getCurrentUser(Principal principal) {
        return userFacade.findByLoginWithContract(principal.getName());
    }

    @RequestMapping(value = "/createNewAccount", method = RequestMethod.POST)
    public void createNewAccount(@RequestBody UserWithPasswordDTO userDTO) {
        userFacade.createCustomer(userDTO);
    }
}
