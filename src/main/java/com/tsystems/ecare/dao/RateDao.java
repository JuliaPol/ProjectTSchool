package com.tsystems.ecare.dao;

import com.tsystems.ecare.entities.Rate;

public interface RateDao extends JpaDao<Rate> {
    Rate findByName(String name);
}
