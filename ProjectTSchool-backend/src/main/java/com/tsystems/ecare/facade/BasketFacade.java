package com.tsystems.ecare.facade;

import com.tsystems.ecare.dto.BasketDTO;
import com.tsystems.ecare.entities.User;

import java.util.List;
import java.util.Set;

public interface BasketFacade extends Facade<BasketDTO, User>{
    //BasketDTO getBasket(String user, String rate, Set<String> optionList);
}
