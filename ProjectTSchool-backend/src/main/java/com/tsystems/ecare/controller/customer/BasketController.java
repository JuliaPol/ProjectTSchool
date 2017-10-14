package com.tsystems.ecare.controller.customer;

import com.tsystems.ecare.form.BasketForm;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/basket")
@Secured("ROLE_CUSTOMER")
public class BasketController {

    @RequestMapping
    public ModelAndView showBasketPage(HttpSession request, @RequestParam("number") String number) {
        return new ModelAndView("/basket.jsp",
                "basket", request.getAttribute(number));
    }

    @RequestMapping(value = "/addTariff")
    public String addTariff(HttpSession httpSession, @RequestParam("number") String number, @RequestParam("tariff") String tariff) {
        BasketForm basket = (BasketForm) httpSession.getAttribute(number);
        if (basket == null) {
            basket = new BasketForm();
            basket.setNumber(number);
        }
        basket.setRate(tariff);
        httpSession.setAttribute(number, basket);
        return basket.getCountOfProduct().toString();
    }

    @RequestMapping(value = "/addOption")
    public String addOption(HttpSession httpSession, @RequestParam("number") String number, @RequestParam("option") String option) {
        BasketForm basket = (BasketForm) httpSession.getAttribute(number);
        if (basket == null) {
            basket = new BasketForm();
            basket.setNumber(number);
        }
        basket.getOptions().add(option);
        httpSession.setAttribute(number, basket);
        return basket.getCountOfProduct().toString();
    }

    @RequestMapping(value = "/getCount")
    public String addOption(HttpSession httpSession, @RequestParam("number") String number) {
        BasketForm basket = (BasketForm) httpSession.getAttribute(number);
        return basket.getCountOfProduct().toString();
    }
}
