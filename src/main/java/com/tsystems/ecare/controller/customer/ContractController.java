package com.tsystems.ecare.controller.customer;


import com.tsystems.ecare.dto.ContractDTO;
import com.tsystems.ecare.facade.ContractFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contract")
@Secured("ROLE_CUSTOMER")
public class ContractController {

    @Autowired
    private ContractFacade contractFacade;

    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
    @ResponseBody
    public ContractDTO getContractByNumber(@PathVariable("number") String number) {
        return contractFacade.getContractByNumber(number);
    }
}
