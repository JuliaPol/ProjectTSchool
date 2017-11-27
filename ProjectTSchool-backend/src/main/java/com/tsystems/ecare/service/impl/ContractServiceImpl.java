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
    private UserDao userDao;

    @Autowired
    private RateDao rateDao;

    private static Logger log = Logger.getLogger(ContractServiceImpl.class);

    /**
     * The method searches for an contract by number
     *
     * @param number
     * @return contract
     */
    @Override
    @Transactional
    public Contract getContractByNumber(String number) {
        return contractDao.getContractByNumber(number);
    }

    /**
     * The method searches for an user by number
     *
     * @param number
     * @return user
     */
    @Override
    @Transactional
    public User findUserByNumber(String number) {
        return contractDao.findUserByNumber(number);
    }

    /**
     * The method changes contract status.
     * If contract is blocked, he will has status 'AVAILABLE'.
     * If contract is unblocked, he will has status 'BLOCKED_BY_AN_EMPLOYEE'.
     *
     * @param number
     */
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

    /**
     * The method changes contract status.
     * If contract is blocked by customer, he will has status 'AVAILABLE'.
     * If contract is unblocked, he will has status 'BLOCKED_BY_THE_CUSTOMER'.
     *
     * @param number
     */
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


    /**
     * The method searches for an user by like number and gets list.
     *
     * @param likeNumber
     * @param limit
     * @return list users
     */
    @Override
    @Transactional
    public List<User> searchByNumber(String likeNumber, int limit) {
        return contractDao.searchByNumber(likeNumber, limit);
    }

    /**
     * The method searches for an user by like name and gets list.
     *
     * @param likeName
     * @param limit
     * @return list users
     */
    @Override
    @Transactional
    public List<User> searchByName(String likeName, int limit) {
        return contractDao.searchByName(likeName, limit);
    }

    /**
     * The method creates new contract for user.
     *
     * @param id
     * @param contract
     */
    @Override
    @Transactional
    public void create(Long id, Contract contract) {
        User user = userDao.get(id);
        if (user.getContractList() == null) {
            user.setContractList(new ArrayList<>());
        }
        user.getContractList().add(contract);
        contract.setUser(user);
        Rate rate = contract.getRate();
        if (rate.getContractList() == null) {
            rate.setContractList(new ArrayList<>());
        }
        contract.setCreationDate(new Date());
        rate.getContractList().add(contract);
        rateDao.update(rate);
        userDao.update(user);
        contractDao.insert(contract);
        log.info("Added a new contract");
    }

    /**
     * The method deletes contract for user.
     *
     * @param id
     */
    @Transactional
    @Override
    public void delete(Long id) {
        Contract contract = contractDao.get(id);
        contract.setRate(null);
        contract.setOptionList(new ArrayList<>());
        contract.setUser(null);
        contractDao.delete(contract);
    }

    /**
     * The method updates contract for user.
     *
     * @param contract
     */
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
