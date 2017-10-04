package com.tsystems.ecare.controller;

import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tariffs")
public class RateController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    @ResponseBody
    public UserDTO getByUserLogin(@PathVariable("login") String login) {
        return userService.findByLogin(login);
    }
}
