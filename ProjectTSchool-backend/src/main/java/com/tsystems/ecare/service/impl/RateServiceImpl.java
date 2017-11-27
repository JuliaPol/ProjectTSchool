package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.ContractDao;
import com.tsystems.ecare.dao.JpaDao;
import com.tsystems.ecare.dao.RateDao;
import com.tsystems.ecare.entities.Rate;
import com.tsystems.ecare.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    /**
     * The method searches for a tariff by name
     *
     * @param name
     * @return rate
     */
    @Override
    public Rate findByName(String name) {
        return rateDao.findByName(name);
    }

    /**
     * The method gets all available tariff.
     *
     * @param number
     * @return list tariffs
     */
    @Override
    public List<Rate> findAllForCustomer(String number) {
        return rateDao.findAllForCustomer(number);
    }

    /**
     * The method edits tariff.
     *
     * @param rate
     */
    @Override
    @Transactional
    public void editRate(Rate rate) {
        rateDao.update(rate);
    }

    /**
     * The method deletes tariff.
     *
     * @param id
     */
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
