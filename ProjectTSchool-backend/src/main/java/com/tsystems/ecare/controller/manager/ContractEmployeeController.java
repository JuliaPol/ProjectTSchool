package com.tsystems.ecare.controller.manager;

import com.tsystems.ecare.facade.ContractFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contract")
@Secured("ROLE_MANAGER")
public class ContractEmployeeController {

    @Autowired
    private ContractFacade contractFacade;

    @RequestMapping(value = "/{number}", method = RequestMethod.POST)
    @ResponseBody
    public void getContractByNumber(@PathVariable("number") String number) {
        contractFacade.changeContractStatusByEmployee(number);
    }
}
