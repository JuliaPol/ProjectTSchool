package com.tsystems.ecare.controller.manager;

import com.tsystems.ecare.facade.ContractFacade;
import com.tsystems.ecare.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contract")
@Secured("ROLE_MANAGER")
public class ContractEmployeeController {

    @Autowired
    private ContractFacade contractFacade;

    @Autowired
    private ContractService contractService;

    @RequestMapping(value = "/{number}", method = RequestMethod.POST)
    @ResponseBody
    public void getContractByNumber(@PathVariable("number") String number) {
        contractFacade.changeContractStatusByEmployee(number);
    }

    @RequestMapping(value = "add/rate/{number}", method = RequestMethod.POST)
    @ResponseBody
    public void addTariffForCustomer(@PathVariable("number") String number, @RequestParam("rate") String  rate) throws Exception {
        contractService.addRateInContract(number,Long.parseLong(rate));
    }

    @RequestMapping(value = "add/option/{number}", method = RequestMethod.POST)
    @ResponseBody
    public void addOptionForCustomer(@PathVariable("number") String number, @RequestParam("rate") String  rate) throws Exception {
        contractService.addRateInContract(number,Long.parseLong(rate));
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    public void createContract(@RequestBody String number, @RequestParam("rate") String  rateId) throws Exception {
//        contractService.createContract(number,Long.parseLong(rate));
    }
}
