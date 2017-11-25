package com.tsystems.ecare.controller.manager;

import com.tsystems.ecare.dto.ContractDTO;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.facade.ContractFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contract")
@Secured("ROLE_MANAGER")
public class ContractEmployeeController {

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

    @RequestMapping(value = "/{number}", method = RequestMethod.POST)
    @ResponseBody
    public void getContractByNumber(@PathVariable("number") String number) {
        contractFacade.changeContractStatusByEmployee(number);
    }

    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    public void create(@PathVariable("id") Long id,
                       @RequestBody ContractDTO contractDTO) throws Exception {
        contractFacade.create(id, contractDTO);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public void delete(@PathVariable("id") Long id) throws Exception {
        contractFacade.delete(id);
    }
}
