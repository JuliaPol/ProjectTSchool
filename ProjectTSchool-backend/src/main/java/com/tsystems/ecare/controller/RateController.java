package com.tsystems.ecare.controller;

import com.tsystems.ecare.dto.RateDTO;
import com.tsystems.ecare.facade.RateFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tariffs")
public class RateController {

    @Autowired
    private RateFacade rateFacade;


    @Secured("ROLE_CUSTOMER")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<RateDTO> findAllForCustomer(@RequestParam("number") String number) throws Exception{
        return rateFacade.findAllForCustomer(number);
    }

    @Secured("ROLE_CUSTOMER")
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    @ResponseBody
    public RateDTO findForCustomerByNumber(@RequestParam("number") String number) throws Exception{
        return rateFacade.findForCustomerByNumber(number);
    }

//    @Secured("ROLE_MANAGER")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<RateDTO> getAllTariffs() throws Exception {
        return rateFacade.getAll();
    }

    @Secured("ROLE_MANAGER")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public void editRate(@RequestBody RateDTO rateDTO) throws Exception {
        rateFacade.editRate(rateDTO);
    }

    @Secured("ROLE_MANAGER")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createRate(@RequestBody RateDTO rateDTO) throws Exception {
        rateFacade.create(rateDTO);
    }

    @Secured("ROLE_MANAGER")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public void deleteRate(@PathVariable("id") Long id) throws Exception {
        rateFacade.deleteRate(id);
    }
//
//    @Secured("ROLE_MANAGER")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RateDTO getTariffById(@PathVariable("id") Long id) throws Exception {
        return rateFacade.get(id);
    }
}
