package com.tsystems.ecare.session;

import com.tsystems.ecare.dto.OptionDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Component
@Scope("session")
public class BasketBean {
    private Map<String, List<OptionDTO>> basket = new HashMap<>();

    public Map<String, List<OptionDTO>> getBasket() {
        return basket;
    }
}
