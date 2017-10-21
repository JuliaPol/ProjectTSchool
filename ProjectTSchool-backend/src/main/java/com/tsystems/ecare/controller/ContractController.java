package com.tsystems.ecare.controller;


import com.tsystems.ecare.dto.ContractDTO;
import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.facade.ContractFacade;
import com.tsystems.ecare.form.BasketForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/contract")
@Secured({"ROLE_CUSTOMER", "ROLE_MANAGER"})
public class ContractController {

    @Autowired
    private ContractFacade contractFacade;

    @RequestMapping(value = "/delete/rate/{number}", method = RequestMethod.POST)
    @ResponseBody
    public void deleteRateFromContract(@PathVariable("number") String number) {
        contractFacade.deleteRate(number);
    }

    @RequestMapping(value = "/delete/option/{number}", method = RequestMethod.POST)
    @ResponseBody
    public void deleteRateFromContract(@PathVariable("number") String number,
                                       @RequestParam("option") String option ) throws Exception {
        contractFacade.deleteOption(number, Long.parseLong(option));
    }

    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    public void create(@PathVariable("id") Long id,
                       @RequestBody ContractDTO contractDTO) throws Exception {
        contractFacade.create(id, contractDTO);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ContractDTO getById(@RequestParam("id") Long id) throws Exception {
        return contractFacade.get(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody ContractDTO contractDTO) throws Exception {
        contractFacade.updateContract(contractDTO);
    }
    @RequestMapping(value = "/update/options/{id}", method = RequestMethod.POST)
    public void updateOptions(@PathVariable("id") Long id, @RequestBody List<OptionDTO> optionDTOS) throws Exception {
        contractFacade.updateContractOptions(id, optionDTOS);
    }
}
