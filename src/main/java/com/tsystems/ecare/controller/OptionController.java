package com.tsystems.ecare.controller;

import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.facade.OptionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/options")
public class OptionController {

    @Autowired
    private OptionFacade optionFacade;

    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public List<OptionDTO> getAllOptions(@RequestParam("number") String number) {
        return optionFacade.getAllOptionsForCustomer(number);
    }
}
