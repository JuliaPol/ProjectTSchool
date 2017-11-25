package com.tsystems.ecare.controller.customer;

import com.tsystems.ecare.aop.annotation.CheckCustomerPermission;
import com.tsystems.ecare.dto.ContractDTO;
import com.tsystems.ecare.facade.ContractFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/contract")
@Secured({"ROLE_CUSTOMER"})
public class ContractCustomerController {
    @Autowired
    private ContractFacade contractFacade;

    @CheckCustomerPermission
    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
    @ResponseBody
    public ContractDTO getContractByNumber(@PathVariable("number") String number, Principal principal) {
        return contractFacade.getContractByNumber(number);
    }

    @RequestMapping(value = "/customer/{number}", method = RequestMethod.POST)
    @ResponseBody
    public void getContractForCustomer(@PathVariable("number") String number) {
        contractFacade.changeContractStatusByCustomer(number);
    }
}
