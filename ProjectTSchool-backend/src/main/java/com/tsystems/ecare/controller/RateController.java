package com.tsystems.ecare.controller;

import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.dto.RateDTO;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.facade.OptionFacade;
import com.tsystems.ecare.facade.RateFacade;
import com.tsystems.ecare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tariffs")
public class RateController {

    @Autowired
    private RateFacade rateFacade;

    @Autowired
    private OptionFacade optionFacade;

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

    @Secured("ROLE_MANAGER")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<RateDTO> getAllTariffs() throws Exception {
        return rateFacade.getAll();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public void editRate(@RequestBody RateDTO rateDTO) throws Exception {
        rateFacade.editRate(rateDTO);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST,
            consumes= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void editRateOptions(@PathVariable("id") Long id,
                         @RequestBody OptionDTO optionDTOS) throws Exception {
        optionFacade.editRateOptions(id, optionDTOS);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createRate(@RequestBody RateDTO rateDTO) throws Exception {
        rateFacade.insert(rateDTO);
    }

    @Secured("ROLE_MANAGER")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RateDTO getTariffById(@PathVariable("id") Long id) throws Exception {
        return rateFacade.get(id);
    }
}
