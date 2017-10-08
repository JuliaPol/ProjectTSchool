package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.JpaDao;
import com.tsystems.ecare.dao.OptionDao;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("optionService")
public class OptionServiceImpl extends ServiceImpl<Option> implements OptionService {

    @Autowired
    private OptionDao optionDao;

    @Override
    public List<Option> getAllOptionsForCustomer(String number) {
        return optionDao.getAllOptionsForCustomer(number);
    }
    @Override
    protected JpaDao<Option> getDefaultDao() {
        return optionDao;
    }
}
