package com.tsystems.ecare.service;

import com.tsystems.ecare.entities.Rate;

public interface RateService extends Service<Rate> {
    Rate findByName(String name);
}
