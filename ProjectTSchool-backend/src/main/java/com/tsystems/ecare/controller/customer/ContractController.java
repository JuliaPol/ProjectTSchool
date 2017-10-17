package com.tsystems.ecare.controller.customer;


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
@Secured("ROLE_CUSTOMER")
public class ContractController {

    @Autowired
    private ContractFacade contractFacade;

    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
    @ResponseBody
    public ContractDTO getContractByNumber(@PathVariable("number") String number) {
        return contractFacade.getContractByNumber(number);
    }

    @RequestMapping(value = "/customer/{number}", method = RequestMethod.POST)
    @ResponseBody
    public void getContractForCustomer(@PathVariable("number") String number) {
        contractFacade.changeContractStatusByCustomer(number);
    }

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

    @RequestMapping(value = "/add/{number}", method = RequestMethod.POST)
    @ResponseBody
    public void addRateOrOption(HttpSession httpSession, @PathVariable("number") String number) throws Exception {
        BasketForm basket = (BasketForm) httpSession.getAttribute(number);
        if (basket != null) {
            contractFacade.addRateOrOptionsInContract(basket);
            basket.setRate(null);
            basket.getOptions().clear();
        }
    }
}
