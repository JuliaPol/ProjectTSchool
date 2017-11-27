package com.tsystems.ecare.controller.customer;

import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.session.BasketBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/basket")
@Secured("ROLE_CUSTOMER")
public class BasketController {

    @Autowired
    private BasketBean basketBean;

    @RequestMapping(value = "/add/options/{id}", method = RequestMethod.POST)
    public void updateOptions(@PathVariable("id") Long id, @RequestBody List<OptionDTO> optionDTOS) {
        basketBean.getBasket().put(id.toString(), optionDTOS);
    }

    @RequestMapping(value = "/get/options/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<OptionDTO> getOptionsInBasket(@PathVariable("id") String number) {
        return basketBean.getBasket().get(number) == null ? new ArrayList<>()
                : basketBean.getBasket().get(number);
    }

    @RequestMapping(value = "/clear/{id}", method = RequestMethod.POST)
    public void clearSession(@PathVariable("id") Long id) {
        basketBean.getBasket().put(id.toString(), new ArrayList<>());
    }
}
