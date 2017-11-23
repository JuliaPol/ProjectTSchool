package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.ContractDao;
import com.tsystems.ecare.dao.JpaDao;
import com.tsystems.ecare.dao.RateDao;
import com.tsystems.ecare.dao.UserDao;
import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.entities.Rate;
import com.tsystems.ecare.entities.User;
import com.tsystems.ecare.entities.enums.ContractStatus;
import com.tsystems.ecare.service.ContractService;
import com.tsystems.ecare.service.OptionService;
import com.tsystems.ecare.service.RateService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of {@link ContractService} interface.
 */

@Service("contractService")
public class ContractServiceImpl extends ServiceImpl<Contract> implements ContractService {

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private OptionService optionService;

    @Autowired
    private RateService rateService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RateDao rateDao;

    private static Logger log = Logger.getLogger(ContractServiceImpl.class);

    @Override
    @Transactional
    public List<String> findContactsByUserLogin(String login) {
        return contractDao.findAllContactsByUserId(login);
    }

    @Override
    @Transactional
    public Contract getContractByNumber(String number) {
        return contractDao.getContractByNumber(number);
    }

    @Override
    @Transactional
    public User findUserByNumber(String number) {
        return contractDao.findUserByNumber(number);
    }

    @Override
    @Transactional
    public void changeContractStatusByEmployee(String number) {
        Contract contract = getContractByNumber(number);
        if (contract.getStatus().equals(ContractStatus.AVAILABLE)) {
            contract.setStatus(ContractStatus.BLOCKED_BY_AN_EMPLOYEE);
        } else {
            contract.setStatus(ContractStatus.AVAILABLE);
        }
        log.info("Contract status changed by employee");
    }

    @Override
    @Transactional
    public void changeContractStatusByCustomer(String number) {
        Contract contract = getContractByNumber(number);
        if (contract.getStatus().equals(ContractStatus.AVAILABLE)) {
            contract.setStatus(ContractStatus.BLOCKED_BY_THE_CUSTOMER);
        } else if (contract.getStatus().equals(ContractStatus.BLOCKED_BY_THE_CUSTOMER)) {
            contract.setStatus(ContractStatus.AVAILABLE);
        }
        log.info("Contract status changed by customer");
    }

    @Override
    @Transactional
    public void deleteRate(String number) {
        Contract contract = getContractByNumber(number);
        contract.setRate(null);
        contract.getOptionList().clear();
    }

    @Override
    @Transactional
    public void deleteOption(String number, Long optionId) {
        Contract contract = getContractByNumber(number);
        Option option = null;
        try {
            option = optionService.get(optionId);
            contract.getOptionList().remove(option);
        } catch (Exception e) {
            log.error("Couldn't remove an option from the contract", e);
        }
    }

    @Override
    @Transactional
    public void addRateInContract(String number, Long rateId) {
        Contract contract = getContractByNumber(number);
        if (contract.getStatus().equals(ContractStatus.AVAILABLE)) {
            Rate rate = null;
            try {
                rate = rateService.get(rateId);
                contract.setRate(rate);
                contract.getOptionList().clear();
            } catch (Exception e) {
                log.error("Couldn't add a tariff", e);
            }
        }
    }

    @Override
    @Transactional
    public void addOptionsInContract(String number, List<Long> optionIds) {
        Contract contract = getContractByNumber(number);
        if (contract.getStatus().equals(ContractStatus.AVAILABLE) && contract.getRate() != null) {
            for (Long id : optionIds) {
                Option option = null;
                try {
                    option = optionService.get(id);
                    contract.getOptionList().add(option);
                } catch (Exception e) {
                    log.error("Couldn't add an option", e);
                }
            }
        }
    }

    @Override
    @Transactional
    public void addOptionInContract(String number, Long optionId) {
        Contract contract = getContractByNumber(number);
        if (contract.getStatus().equals(ContractStatus.AVAILABLE) && contract.getRate() != null) {
            try {
                contract.getOptionList().add(optionService.get(optionId));
            } catch (Exception e) {
                log.error("Couldn't add an option", e);
            }
        }
    }

    @Override
    @Transactional
    public List<User> searchByNumber(String likeNumber, int limit) {
        return contractDao.searchByNumber(likeNumber, limit);
    }

    @Override
    @Transactional
    public List<User> searchByName(String likeName, int limit) {
        return contractDao.searchByName(likeName, limit);
    }

    @Override
    @Transactional
    public void create(Long id, Contract contract) {
        User user = userDao.get(id);
        if (user.getContractList() == null ) {
            user.setContractList(new ArrayList<>());
        }
        user.getContractList().add(contract);
        contract.setUser(user);
        Rate rate = contract.getRate();
        if (rate.getContractList() == null ) {
            rate.setContractList(new ArrayList<>());
        }
        contract.setCreationDate(new Date());
        rate.getContractList().add(contract);
        rateDao.update(rate);
        userDao.update(user);
        contractDao.insert(contract);
        log.info("Added a new contract");
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Contract contract = contractDao.get(id);
        contract.setRate(null);
        contract.setOptionList(new ArrayList<>());
        contract.setUser(null);
        contractDao.delete(contract);
    }

    @Override
    @Transactional
    public void updateContract(Contract contract) {
        contractDao.update(contract);
    }

    @Override
    protected JpaDao<Contract> getDefaultDao() {
        return contractDao;
    }
}
