package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.JpaDao;
import com.tsystems.ecare.dao.RateDao;
import com.tsystems.ecare.entities.Rate;
import com.tsystems.ecare.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    public List<Rate> findAllForCustomer(String number) {
        return rateDao.findAllForCustomer(number);
    }

    @Override
    public Rate findForCustomerByNumber(String number) {
        return rateDao.findForCustomerByNumber(number);
    }

    @Override
    protected JpaDao<Rate> getDefaultDao() {
        return rateDao;
    }
}