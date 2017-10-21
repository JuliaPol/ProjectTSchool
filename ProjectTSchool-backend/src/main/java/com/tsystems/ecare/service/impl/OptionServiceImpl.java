package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.ContractDao;
import com.tsystems.ecare.dao.JpaDao;
import com.tsystems.ecare.dao.OptionDao;
import com.tsystems.ecare.dao.RateDao;
import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.entities.Rate;
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

    @Autowired
    private RateDao rateDao;

    @Autowired
    private ContractDao contractDao;

    private static Logger log = Logger.getLogger(OptionServiceImpl.class);

    @Override
    public List<Option> getAllOptionsForCustomer(String number) {
        return optionDao.getAllOptionsForCustomer(number);
    }

    /**
     * The method gets options that aren't contained in the tariff and the contract
     * and checks for compatibility.
     * @param number
     * @return list available options
     */
    @Override
    public List<Option> getAllAvailableOptionsForCustomer(String number) {
        List<Option> listOptionsInRateAndContract = getAllOptionsInRateAndContract(number);
        List<Option> freeOptions = getAllFreeOptions(number);
        List<Option> availableOptions = checkCompatibleOptions(listOptionsInRateAndContract, freeOptions);
        log.info("Check compatible options:");
        for (Option o : availableOptions) {
            log.info(o.getId() + "  " + o.getName());
        }
        List<Option> allAvailableOptions = checkIncompatibleOptions(listOptionsInRateAndContract, availableOptions);
        log.info("Check incompatible options:");
        for (Option o : allAvailableOptions) {
            log.info(o.getId() + "  " + o.getName());
        }
        return allAvailableOptions;
    }

    /**
     * The method gets options that are contained in the tariff and the contract.
     * @param number
     * @return list options
     */
    @Override
    public List<Option> getAllOptionsInRateAndContract(String number) {
        List<Option> listOptionsInRateAndContract = new ArrayList<>();
        listOptionsInRateAndContract.addAll(optionDao.findAllOptionsInRateForCustomer(number));
        listOptionsInRateAndContract.addAll(optionDao.getAllOptionsForCustomer(number));
        return listOptionsInRateAndContract;
    }

    /**
     * The method gets list options that aren't contained in tariff or contract
     * and returns all options that wouldn't plugged in contract.
     * @param number
     * @param availableOptions
     * @return list available options
     */
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

    /**
     * The method checks two option lists. If option from availableOptions has the compatible option
     * and optionsInContract contains this option, it will add to return list.
     * @param optionsInContract list
     * @param availableOptions list
     * @return list of available options
     */
    @Override
    public List<Option> checkCompatibleOptions(List<Option> optionsInContract, List<Option> availableOptions) {
        return availableOptions.stream()
                .filter(option -> option.getCompOptions() == null || optionsInContract.containsAll(option.getCompOptions()))
                .collect(Collectors.toList());
    }

    /**
     * The method checks two option lists. If option from availableOptions has the incompatible option
     * and optionsInContract don't contain this option, the incompatible option will add to return list.
     * @param optionsInContract
     * @param availableOptions
     * @return list of available options
     */
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


    /**
     * The method returns list options that aren't contained in tariff or contract.
     * @param number
     * @return list of free options
     */
    @Override
    public List<Option> getAllFreeOptions(String number) {
        List<Option> listOptionsInRateAndContract = getAllOptionsInRateAndContract(number);
        List<Option> allOptions = optionDao.getAll();
        return allOptions.stream()
                .filter(op -> !listOptionsInRateAndContract.contains(op)).collect(Collectors.toList());
    }

    /**
     * The method checks if options in list are incompatible
     * @param optionList
     * @return true if incompatible or false if not
     */
    @Override
    public boolean checkNewOptions(List<Option> optionList) {
        for (Option option : optionList) {
            if (option.getCompOptions() != null && option.getIncOptions().stream().anyMatch(optionList::contains)) {
                return true;
            }
        }
        return false;
    }

    /**
     * The method adds rules for options. They can be compatible or incompatible.
     * @param current option id
     * @param incomp list options
     * @param isCompatible options are compatible or not
     */
    @Transactional
    @Override
    public void addIncompatible(Long current, List<String> incomp, boolean isCompatible) {
        Option currentOption = optionDao.get(current);
        if (!isCompatible) {

            cleanIncompatibleReferences(currentOption);
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
            cleanCompatibleReferences(currentOption);
            currentOption.setCompOptions(new ArrayList<>());
            for (String name : incomp) {
                Option inc = optionDao.findOptionByName(name);
                currentOption.getCompOptions().add(inc);
                optionDao.update(currentOption);
            }
        }
    }

    /**
     * The method deletes compatible options for current option.
     * @param currentOption
     */
    private void cleanCompatibleReferences(Option currentOption) {
        for (Option option : currentOption.getCompOptions()) {
            option.getCompOptionsOf().remove(currentOption);
            optionDao.update(option);
        }
    }

    /**
     * The method deletes incompatible options for current option.
     * @param currentOption
     */
    private void cleanIncompatibleReferences(Option currentOption) {
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

    /**
     * The method deletes an option.
     * @param id option
     */
    @Override
    @Transactional
    public void deleteOption(Long id) {
        Option option = optionDao.get(id);
        cleanCompatibleReferences(option);
        cleanIncompatibleReferences(option);
        for (Rate rate : option.getRateList()) {
            rate.getOptionList().remove(option);
            rateDao.update(rate);
        }

        for (Contract contract : option.getContractList()) {
            contract.getOptionList().remove(option);
            contractDao.update(contract);
        }
        optionDao.delete(option);
        log.info("Option deleted");
    }

    @Override
    public void editRateOptions(Long id, Option option) {
       Rate rate = rateDao.get(id);
    }

    @Override
    protected JpaDao<Option> getDefaultDao() {
        return optionDao;
    }
}
