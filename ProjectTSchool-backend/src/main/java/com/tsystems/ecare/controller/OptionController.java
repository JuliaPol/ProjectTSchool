package com.tsystems.ecare.controller;

import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.facade.OptionFacade;
import com.tsystems.ecare.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/options")
public class OptionController {

    @Autowired
    private OptionFacade optionFacade;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<OptionDTO> getAll() throws Exception {
        return optionFacade.getAll();
    }

    @RequestMapping(value = "/addRule", method = RequestMethod.POST)
    public void addRule(@RequestParam("optionId") Long current,
                                @RequestParam("isCompatible") boolean isCompatible,
                                @RequestBody List<String> incompOptions) throws Exception {
        optionFacade.addIncompatible(current, incompOptions, isCompatible);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody OptionDTO optionDTO) throws Exception {
        optionFacade.create(optionDTO);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void edit(@RequestParam("optionId") String optionId, @RequestBody OptionDTO optionDTO) throws Exception {
        optionFacade.edit(optionId, optionDTO);
    }

    @RequestMapping(value = "/available", method = RequestMethod.GET)
    public List<OptionDTO> getAllAvailableOptions(@RequestParam("number") String number) {
        return optionFacade.getAllAvailableOptionsForCustomer(number);
    }

    @RequestMapping(value = "/unavailable", method = RequestMethod.GET)
    public List<OptionDTO> getAllIncompatibleOptions(@RequestParam("number") String number) {
        return optionFacade.getAllIncompatibleOptionsForCustomer(number);
    }

    @RequestMapping(value = "/compatible", method = RequestMethod.GET)
    public List<OptionDTO> getAllAvailableOptions() {
        return optionFacade.getBy(3L);
    }

    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public List<OptionDTO> getAllOptions(@RequestParam("number") String number) {
        return optionFacade.getAllOptionsForCustomer(number);
    }
}
