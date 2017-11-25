package com.tsystems.ecare.controller;


import com.tsystems.ecare.dto.ContractDTO;
import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.facade.ContractFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contract")
@Secured({"ROLE_CUSTOMER", "ROLE_MANAGER"})
public class ContractController {

    @Autowired
    private ContractFacade contractFacade;

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
