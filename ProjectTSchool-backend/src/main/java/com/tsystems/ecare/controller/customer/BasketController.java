package com.tsystems.ecare.controller.customer;

import com.tsystems.ecare.dto.BasketDTO;
import com.tsystems.ecare.facade.BasketFacade;
import com.tsystems.ecare.facade.UserFacade;
import com.tsystems.ecare.form.BasketForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@RestController
@RequestMapping("/basket")
@Secured("ROLE_CUSTOMER")
public class BasketController {

    @Autowired
    private BasketFacade basketFacade;

    @RequestMapping
    public ModelAndView showBasketPage(Principal principal, HttpSession request, @RequestParam("number") String number) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/basket.jsp");
        BasketForm basketForm = (BasketForm) request.getAttribute(number);
        BasketDTO basketDTO;
        if (basketForm != null) {//!!!!!!!!!!!!!!!!!!!!
            basketDTO = basketFacade.getBasket(principal.getName(), basketForm.getRate(), basketForm.getOptions());
        } else {
            basketDTO = basketFacade.getBasket(principal.getName(), null, null);
        }
        modelAndView.addObject("customerInfo", basketDTO.getUser());
        modelAndView.addObject("basket", basketDTO);
        return modelAndView;
    }

    @RequestMapping(value = "/add")
    public String addTariffOrOption(HttpSession httpSession, @RequestParam("number") String number,
                                    @RequestParam("tariff") String tariff, @RequestParam("option") String option) {
        BasketForm basket = (BasketForm) httpSession.getAttribute(number);
        if (basket == null) {
            basket = new BasketForm();
            basket.setNumber(number);
        }
        if (tariff.equals("")) {
            basket.getOptions().add(option);
        } else {
            basket.setRate(tariff);
        }
        httpSession.setAttribute(number, basket);
        return basket.getCountOfProduct().toString();
    }

    @RequestMapping(value = "/delete")
    public String deleteTariffOrOption(HttpSession httpSession, @RequestParam("number") String number,
                                       @RequestParam("option") String option) {
        BasketForm basket = (BasketForm) httpSession.getAttribute(number);
        if (option.equals("")) {
            basket.setRate(null);
        } else {
            basket.getOptions().remove(option);
        }
        httpSession.setAttribute(number, basket);
        return basket.getCountOfProduct().toString();
    }

    @RequestMapping(value = "/getCount")
    public String addOption(HttpSession httpSession, @RequestParam("number") String number) {
        BasketForm basket = (BasketForm) httpSession.getAttribute(number);
        return basket.getCountOfProduct() == null ? "" :basket.getCountOfProduct().toString();
    }
}
