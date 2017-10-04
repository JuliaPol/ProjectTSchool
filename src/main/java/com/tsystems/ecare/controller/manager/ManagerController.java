package com.tsystems.ecare.controller.manager;

import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.facade.UserFacade;
import com.tsystems.ecare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Secured("ROLE_MANAGER")
public class ManagerController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    @ResponseBody
    public UserDTO getByUserLogin(@PathVariable("login") String login) {
        return userService.findByLogin(login);
    }

    @RequestMapping(value = "/allCustomers", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> getAllCustomers() {
        return userFacade.getAllUsersByRole("ROLE_CUSTOMER");
    }
}
