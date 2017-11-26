package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.ContractDao;
import com.tsystems.ecare.dao.JpaDao;
import com.tsystems.ecare.dao.OptionDao;
import com.tsystems.ecare.dao.RateDao;
import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.dto.RateDTO;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.entities.Rate;
import com.tsystems.ecare.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link RateService} interface.
 */
@Service("rateService")
public class RateServiceImpl extends ServiceImpl<Rate> implements RateService {

    @Autowired
    private RateDao rateDao;

    @Autowired
    private ContractDao contractDao;

    @Override
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
    @Transactional
    public void editRate(Rate rate) {
        rateDao.update(rate);
    }

    @Override
    @Transactional
    public void deleteRate(Long id) {
        Rate rate = rateDao.get(id);
        contractDao.updateContractWithDeletedRate(id);
        rateDao.delete(rate);
    }

    @Override
    protected JpaDao<Rate> getDefaultDao() {
        return rateDao;
    }
}
