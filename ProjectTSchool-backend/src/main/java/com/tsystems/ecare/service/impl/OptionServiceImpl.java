package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.ContractDao;
import com.tsystems.ecare.dao.JpaDao;
import com.tsystems.ecare.dao.OptionDao;
import com.tsystems.ecare.dao.RateDao;
import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.entities.Rate;
import com.tsystems.ecare.service.OptionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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

    /**
     * The method gets all options in contract
     *
     * @param number
     * @return list options
     */
    @Override
    public List<Option> getAllOptionsForCustomer(String number) {
        return optionDao.getAllOptionsForCustomer(number);
    }

    /**
     * The method gets options that are contained in the tariff and the contract.
     *
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
     * The method searches for an option by name
     *
     * @param name
     * @return option
     */
    @Override
    public Option findOptionByName(String name) {
        return optionDao.findOptionByName(name);
    }


    /**
     * The method checks two option lists. If option from availableOptions has the compatible option
     * and optionsInContract contains this option, it will add to return list.
     *
     * @param optionsInContract list
     * @param availableOptions  list
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
     *
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
     *
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
     *
     * @param optionList
     * @return false if incompatible or true if not
     */
    @Override
    public boolean checkNewOptions(List<Option> optionList,
                                   String number) {
        List<Option> optionInRate = optionDao.findAllOptionsInRateForCustomer(number);
        List<Option> oldOptionInContract = optionDao.getAllOptionsForCustomer(number);
        log.info("Options new" + optionList);
        log.info("Options old" + oldOptionInContract);
        log.info("Options in rate" + optionInRate);
        for (Option option : optionList) {
            log.info("option " + option + " incompOpt " + option.getIncOptions());
            if (option.getIncOptions() != null && option.getIncOptions().size() > 0 && (option.getIncOptions().stream()
                    .anyMatch(optionList::contains) || (!oldOptionInContract.contains(option) && option.getIncOptions().stream()
                    .anyMatch(optionInRate::contains)))) {
                log.info("error");
                return false;
            }
        }
        return true;
    }

    /**
     * The method checks if options in list are compatible with options in rate and contract
     *
     * @param optionList
     * @return false if incompatible or true if not
     */
    @Override
    public boolean checkOptionListForCompatible(List<Option> optionList,
                                                String number) {
        List<Option> optionInRate = optionDao.findAllOptionsInRateForCustomer(number);
        List<Option> oldOptionInContract = optionDao.getAllOptionsForCustomer(number);
        log.info("Options" + optionList);
        log.info("Options old" + oldOptionInContract);
        log.info("Options in rate" + optionInRate);
        for (Option option : optionList) {
            log.info("option " + option + " compOpt " + option.getCompOptions());
            if (!oldOptionInContract.contains(option) && option.getCompOptions() != null && option.getCompOptions().size() > 0 && option.getCompOptions().stream()
                    .noneMatch(optionList::contains) && option.getCompOptions().stream()
                    .noneMatch(optionInRate::contains)) {
                log.info("error");
                return false;
            }
        }
        return true;
    }

    /**
     * The method adds rules for options. They can be compatible or incompatible.
     *
     * @param current      option id
     * @param incomp       list options
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
     *
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
     *
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


    /**
     * The method deletes an option.
     *
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
    protected JpaDao<Option> getDefaultDao() {
        return optionDao;
    }
}
