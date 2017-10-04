package com.tsystems.ecare.service;

import com.tsystems.ecare.dto.RateDTO;

public interface RateService {
    RateDTO findByName(String name);
}
