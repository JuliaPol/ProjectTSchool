package com.tsystems.ecare.controller.manager;

import com.tsystems.ecare.dto.CustomerDTO;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
@Secured("ROLE_MANAGER")
public class CustomersController {

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "/allCustomers", method = RequestMethod.GET)
    @ResponseBody
    public List<CustomerDTO> getAllCustomers() {
        return userFacade.getAllCustomers();
    }
}
