package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.JpaDao;
import com.tsystems.ecare.dao.OptionDao;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.facade.impl.OptionFacadeImpl;
import com.tsystems.ecare.service.OptionService;
import org.apache.log4j.Logger;
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

    private static Logger log = Logger.getLogger(OptionServiceImpl.class);

    @Override
    public List<Option> getAllOptionsForCustomer(String number) {
        return optionDao.getAllOptionsForCustomer(number);
    }

    @Override
    public List<Option> getAllAvailableOptionsForCustomer(String number) {
        List<Option> listOptionsInRateAndContract = getAllOptionsInRateAndContract(number);
        List<Option> freeOptions = getAllFreeOptions(number);
        List<Option> availableOptions = checkCompatibleOptions(listOptionsInRateAndContract, freeOptions);
        log.info("Check Comp Option:");
        for (Option o : availableOptions) {
            log.info(o.getId() + "  " + o.getName());
        }
        List<Option> allAvailableOptions = checkIncompatibleOptions(listOptionsInRateAndContract, availableOptions);
        log.info("Check Inc Option:");
        for (Option o : allAvailableOptions) {
            log.info(o.getId() + "  " + o.getName());
        }
        return allAvailableOptions;
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
    public List<Option> getBy(Long id) {
        Option option = optionDao.get(id);
        return option.getCompOptions();
    }

    @Override
    public List<Option> checkCompatibleOptions(List<Option> optionsInContract, List<Option> availableOptions) {
        return availableOptions.stream()
                .filter(option -> optionsInContract.containsAll(option.getCompOptions()) || option.getCompOptions() == null)
                .collect(Collectors.toList());
    }

    @Override
    public List<Option> checkIncompatibleOptions(List<Option> optionsInContract, List<Option> availableOptions) {
        List<Option> checkedOptions = new ArrayList<>();
        for (Option option : availableOptions) {
            if (option.getIncOptions() == null || option.getIncOptions().stream().noneMatch(optionsInContract::contains)) {
                checkedOptions.add(option);
            }
        }
        return checkedOptions;
    }


    @Override
    public List<Option> getAllFreeOptions(String number) {
        List<Option> listOptionsInRateAndContract = getAllOptionsInRateAndContract(number);
        List<Option> allOptions = optionDao.getAll();
        return allOptions.stream()
                .filter(op -> !listOptionsInRateAndContract.contains(op)).collect(Collectors.toList());
    }

    @Override
    public boolean checkNewOptions(List<Option> optionList) {
        for (Option option : optionList) {
            if (option.getIncOptions().stream().anyMatch(optionList::contains)) {
                return true;
            }
        }
        return false;
    }

    @Transactional
    @Override
    public void addIncompatible(Long current, List<String> incomp, boolean isCompatible) {
        Option currentOption = optionDao.get(current);
        if (!isCompatible) {

            for (Option option : currentOption.getIncOptions()) {
                option.getIncOptions().remove(currentOption);
                option.getIncOptionsOf().remove(currentOption);
                optionDao.update(option);
            }
            for (Option option : currentOption.getIncOptionsOf()) {
                option.getIncOptions().remove(currentOption);
                option.getIncOptionsOf().remove(currentOption);
                optionDao.update(option);
            }
            currentOption.setIncOptions(new ArrayList<>());
            currentOption.setIncOptionsOf(new ArrayList<>());
            for (String name : incomp) {
                Option inc = optionDao.findOptionByName(name);
                currentOption.getIncOptions().add(inc);
                optionDao.update(currentOption);
                inc.getIncOptions().add(currentOption);
                optionDao.update(inc);
            }
        } else {

            for (Option option : currentOption.getCompOptions()) {
                option.getCompOptions().remove(currentOption);
                option.getCompOptionsOf().remove(currentOption);
                optionDao.update(option);
            }
            for (Option option : currentOption.getCompOptionsOf()) {
                option.getCompOptions().remove(currentOption);
                option.getCompOptionsOf().remove(currentOption);
                optionDao.update(option);
            }
            currentOption.setCompOptions(new ArrayList<>());
            currentOption.setCompOptionsOf(new ArrayList<>());
            for (String name : incomp) {
                Option inc = optionDao.findOptionByName(name);
                currentOption.getCompOptions().add(inc);
                optionDao.update(currentOption);
                inc.getCompOptions().add(currentOption);
                optionDao.update(inc);
            }
        }
    }

    @Override
    public List<Option> getOptionsForRules(Long optionId) {
        Option option = optionDao.get(optionId);
        List<Option> optionList= optionDao.getAll();
        List<Option> checkedOption = option.getCompOptionsOf().stream()
                    .filter(o->!optionList.contains(o)).collect(Collectors.toList());
        checkedOption.remove(option);
        return checkedOption;
    }

    @Override
    protected JpaDao<Option> getDefaultDao() {
        return optionDao;
    }
}
