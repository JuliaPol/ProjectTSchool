package com.tsystems.ecare.controller;

import com.tsystems.ecare.dao.impl.OptionDaoImpl;
import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.facade.OptionFacade;
import com.tsystems.ecare.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/options")
public class OptionController {

    @Autowired
    private OptionFacade optionFacade;

    @Autowired
    private OptionService optionService;

    @RequestMapping(method = RequestMethod.GET)
    public List<OptionDTO> getAllAvailableOptions(@RequestParam("number") String number) {
        return optionFacade.getAllAvailableOptionsForCustomer(number);
    }

    @RequestMapping(value = "/compatible", method = RequestMethod.GET)
    public Set<Option> getAllAvailableOptions() {
        return optionService.getBy(3L);
    }

    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public List<OptionDTO> getAllOptions(@RequestParam("number") String number) {
        return optionFacade.getAllOptionsForCustomer(number);
    }
}
