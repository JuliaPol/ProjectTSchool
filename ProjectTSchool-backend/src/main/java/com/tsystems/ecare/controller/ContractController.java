package com.tsystems.ecare.controller;


import com.tsystems.ecare.dto.ContractDTO;
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
}
