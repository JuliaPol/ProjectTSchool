package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.JpaDao;
import com.tsystems.ecare.dao.RateDao;
import com.tsystems.ecare.dto.RateDTO;
import com.tsystems.ecare.entities.Rate;
import com.tsystems.ecare.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rateService")
public class RateServiceImpl extends ServiceImpl<Rate> implements RateService {

    @Autowired
    private RateDao rateDao;

    @Override
    public RateDTO findByName(String name) {
        return null;
    }

    @Override
    protected JpaDao<Rate> getDefaultDao() {
        return rateDao;
    }
}
