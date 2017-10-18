package com.tsystems.ecare.controller.manager;

import com.tsystems.ecare.dto.ContractDTO;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.facade.ContractFacade;
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
    private ContractFacade contractFacade;

    @RequestMapping(value = "/allContracts", method = RequestMethod.GET)
    @ResponseBody
    public List<ContractDTO> getAllContracts() throws Exception {
        return contractFacade.getAll();
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    @ResponseBody
    public UserDTO getCustomerByNumber(@RequestParam("number") String number){
        return contractFacade.findUserByNumber(number);
    }

}
