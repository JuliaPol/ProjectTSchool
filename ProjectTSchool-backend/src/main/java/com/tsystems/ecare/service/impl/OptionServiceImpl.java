package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.JpaDao;
import com.tsystems.ecare.dao.OptionDao;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.service.OptionService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("optionService")
public class OptionServiceImpl extends ServiceImpl<Option> implements OptionService {

    @Autowired
    private OptionDao optionDao;

    @Override
    public List<Option> getAllOptionsForCustomer(String number) {
        return optionDao.getAllOptionsForCustomer(number);
    }

    @Override
    public List<Option> getAllAvailableOptionsForCustomer(String number) {
        List<Option> listOptionsInRateAndContract = getAllOptionsInRateAndContract(number);
        List<Option> allOptions = optionDao.getAll();
        List<Option> newOptions = new ArrayList<>();
        for (Option option : allOptions) {
            if (!listOptionsInRateAndContract.contains(option)) {
                newOptions.add(option);
            }
        }
        return newOptions;
    }

    @Override
    public List<Option> getAllOptionsInRateAndContract(String number) {
        List<Option> listOptionsInRateAndContract = new ArrayList<>();
        listOptionsInRateAndContract.addAll(optionDao.findAllOptionsInRateForCustomer(number));
        listOptionsInRateAndContract.addAll(optionDao.getAllOptionsForCustomer(number));
        return listOptionsInRateAndContract;
    }

    @Override
    public List<Option> getAllIncompatibleOptions(String number) {
        List<Option> allOptions = getAllOptionsInRateAndContract(number);
        List<Option> allAvailableOptions = getAllAvailableOptionsForCustomer(number);

        return null;
    }

    @Override
    public Option findOptionByName(String name) {
        return optionDao.findOptionByName(name);
    }

    @Override
    @Transactional
    public Set<Option> getBy(Long id) {
        Option option = optionDao.get(id);
        return option.getCompatibleOptionList();
    }

    @Override
    protected JpaDao<Option> getDefaultDao() {
        return optionDao;
    }
}
