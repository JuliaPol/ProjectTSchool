package com.tsystems.ecare.controller.customer;

import com.tsystems.ecare.facade.ContractFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contract")
@Secured({"ROLE_CUSTOMER"})
public class ContractCustomerController {
    @Autowired
    private ContractFacade contractFacade;

    @RequestMapping(value = "/customer/{number}", method = RequestMethod.POST)
    @ResponseBody
    public void getContractForCustomer(@PathVariable("number") String number) {
        contractFacade.changeContractStatusByCustomer(number);
    }
}
