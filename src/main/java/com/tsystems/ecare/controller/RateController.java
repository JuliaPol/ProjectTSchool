package com.tsystems.ecare.controller;

import com.tsystems.ecare.dto.RateDTO;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.facade.RateFacade;
import com.tsystems.ecare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tariffs")
public class RateController {
    @Autowired
    private RateFacade rateFacade;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RateDTO findByName(@PathVariable("id") Long id) throws Exception{
        return rateFacade.get(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<RateDTO> getAllTariffs() throws Exception {
        return rateFacade.getAll();
    }
}
