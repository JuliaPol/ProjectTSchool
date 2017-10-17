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
import java.util.stream.Collectors;

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
        List<Option> freeOptions = getAllFreeOptions(number);
        List<Option> availableOptions = checkCompatibleOptions(listOptionsInRateAndContract, freeOptions);
        return checkIncompatibleOptions(listOptionsInRateAndContract, availableOptions);
    }

    @Override
    public List<Option> getAllOptionsInRateAndContract(String number) {
        List<Option> listOptionsInRateAndContract = new ArrayList<>();
        listOptionsInRateAndContract.addAll(optionDao.findAllOptionsInRateForCustomer(number));
        listOptionsInRateAndContract.addAll(optionDao.getAllOptionsForCustomer(number));
        return listOptionsInRateAndContract;
    }

    @Override
    public List<Option> getAllIncompatibleOptions(String number, List<Option> availableOptions) {
        List<Option> allOptions = getAllFreeOptions(number);
        return allOptions.stream()
                .filter(op -> !availableOptions.contains(op)).collect(Collectors.toList());
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
    public List<Option> checkCompatibleOptions(List<Option> optionsInContract, List<Option> availableOptions) {
       return availableOptions.stream()
                .filter(option -> optionsInContract.contains(option.getCompatibleOption()) || option.getCompatibleOption() == null)
                .collect(Collectors.toList());
    }

    @Override
    public List<Option> checkIncompatibleOptions(List<Option> optionsInContract, List<Option> availableOption) {
        return availableOption.stream()
                .filter(option -> !optionsInContract.contains(option.getIncompatibleOption()) || option.getIncompatibleOption() == null)
                .collect(Collectors.toList());
    }

    @Override
    public List<Option> getAllFreeOptions(String number) {
        List<Option> listOptionsInRateAndContract = getAllOptionsInRateAndContract(number);
        List<Option> allOptions = optionDao.getAll();
        return allOptions.stream()
                .filter(op -> !listOptionsInRateAndContract.contains(op)).collect(Collectors.toList());
    }

    @Override
    protected JpaDao<Option> getDefaultDao() {
        return optionDao;
    }
}
