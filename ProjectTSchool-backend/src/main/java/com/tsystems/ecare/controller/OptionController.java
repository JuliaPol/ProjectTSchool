package com.tsystems.ecare.controller;

import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.facade.OptionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/options")
public class OptionController {

    @Autowired
    private OptionFacade optionFacade;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<OptionDTO> getAll() throws Exception {
        return optionFacade.getAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    public OptionDTO getById(@RequestParam("id") Long id) throws Exception {
        return optionFacade.get(id);
    }

    @Secured("ROLE_MANAGER")
    @RequestMapping(value = "/addRule", method = RequestMethod.POST)
    public void addRule(@RequestParam("optionId") Long current,
                        @RequestParam("isCompatible") boolean isCompatible,
                        @RequestBody List<String> incompOptions) throws Exception {
        optionFacade.addIncompatible(current, incompOptions, isCompatible);
    }

    @Secured("ROLE_MANAGER")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody OptionDTO optionDTO) throws Exception {
        optionFacade.create(optionDTO);
    }

    @Secured("ROLE_MANAGER")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void edit(@RequestBody OptionDTO optionDTO) throws Exception {
        optionFacade.edit(optionDTO);
    }

    @Secured("ROLE_MANAGER")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestParam("id") Long id) throws Exception {
        optionFacade.deleteOption(id);
    }

    @Secured({"ROLE_CUSTOMER"})
    @RequestMapping(value = "/free/{number}", method = RequestMethod.GET)
    public List<OptionDTO> getAllFreeOptions(@PathVariable("number") String number) {
        return optionFacade.getAllFreeOption(number);
    }
}
