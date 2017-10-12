package com.tsystems.ecare.controller;

import com.tsystems.ecare.dto.ContractDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/index")
public class ContractViewController {
    @RequestMapping(method = RequestMethod.GET)
    public String getContractByNumber() {
        return "index";
    }
}
