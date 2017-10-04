package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.JpaDao;
import com.tsystems.ecare.dao.RateDao;
import com.tsystems.ecare.entities.Rate;
import com.tsystems.ecare.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("rateService")
public class RateServiceImpl extends ServiceImpl<Rate> implements RateService {

    @Autowired
    private RateDao rateDao;

    @Override
    @Transactional
    public Rate findByName(String name) {
        return rateDao.findByName(name);
    }

    @Override
    protected JpaDao<Rate> getDefaultDao() {
        return rateDao;
    }
}
