package com.tsystems.ecare.facade;

import com.tsystems.ecare.dto.RateDTO;

public interface RateFacade extends Facade<RateDTO> {
    RateDTO findByName(String name);
}
