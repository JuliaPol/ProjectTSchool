package com.tsystems.ecare.controller.manager;

import com.tsystems.ecare.dto.CustomerDTO;
import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody UserDTO userDTO) {
        userFacade.createCustomer(userDTO);
    }
}
