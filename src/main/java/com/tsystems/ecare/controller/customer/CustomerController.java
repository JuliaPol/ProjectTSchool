package com.tsystems.ecare.controller.customer;

import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@Secured("ROLE_CUSTOMER")
public class CustomerController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    @ResponseBody
    public UserDTO getByUserLogin(@PathVariable("login") String login) {
        return userService.findByLogin(login);
    }

}
