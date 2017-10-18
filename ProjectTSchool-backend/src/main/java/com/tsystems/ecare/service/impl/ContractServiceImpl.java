package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.ContractDao;
import com.tsystems.ecare.dao.JpaDao;
import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.entities.Rate;
import com.tsystems.ecare.entities.User;
import com.tsystems.ecare.entities.enums.ContractStatus;
import com.tsystems.ecare.service.ContractService;
import com.tsystems.ecare.service.OptionService;
import com.tsystems.ecare.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("contractService")
public class ContractServiceImpl extends ServiceImpl<Contract> implements ContractService {

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private OptionService optionService;

    @Autowired
    private RateService rateService;

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
    }

    @Override
    @Transactional
    public void changeContractStatusByCustomer(String number) {
        Contract contract = getContractByNumber(number);
        if (contract.getStatus().equals(ContractStatus.AVAILABLE)) {
            contract.setStatus(ContractStatus.BLOCKED_BY_THE_CUSTOMER);
        } else if (contract.getStatus().equals(ContractStatus.BLOCKED_BY_THE_CUSTOMER)){
            contract.setStatus(ContractStatus.AVAILABLE);
        }
    }

    @Override
    @Transactional
    public void deleteRate(String number) {
        Contract contract = getContractByNumber(number);
        contract.setRate(null);
    }

    @Override
    @Transactional
    public void deleteOption(String number, Long optionId) throws Exception {
        Contract contract = getContractByNumber(number);
        Option option = optionService.get(optionId);
        contract.getOptionList().remove(option);
    }

    @Override
    @Transactional
    public void addRateInContract(String number, Long rateId) throws Exception {
        Contract contract = getContractByNumber(number);
        if (contract.getStatus().equals(ContractStatus.AVAILABLE)) {
            Rate rate = rateService.get(rateId);
            contract.setRate(rate);
        }
    }

    @Override
    @Transactional
    public void addOptionsInContract(String number, List<Long> optionIds) throws Exception {
        Contract contract = getContractByNumber(number);
        if (contract.getStatus().equals(ContractStatus.AVAILABLE)) {
            for (Long id : optionIds) {
                Option option = optionService.get(id);
                contract.getOptionList().add(option);
            }
        }
    }

    @Override
    @Transactional
    public void addOptionInContract(String number, Long optionId) throws Exception {
        Contract contract = getContractByNumber(number);
        contract.getOptionList().add(optionService.get(optionId));
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
    protected JpaDao<Contract> getDefaultDao() {
        return contractDao;
    }
}
