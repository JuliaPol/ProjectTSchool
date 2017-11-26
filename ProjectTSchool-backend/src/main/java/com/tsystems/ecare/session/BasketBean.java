package com.tsystems.ecare.session;

import com.tsystems.ecare.dto.OptionDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Component("basketBean")
@Scope(value= "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BasketBean {
    private HashMap<String, List<OptionDTO>> basket = new HashMap<>();

    public HashMap<String, List<OptionDTO>> getBasket() {
        return basket;
    }

    public void setBasket(HashMap<String, List<OptionDTO>> basket) {
        this.basket = basket;
    }
}
