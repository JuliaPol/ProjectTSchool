package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.ContractDao;
import com.tsystems.ecare.dao.JpaDao;
import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.entities.User;
import com.tsystems.ecare.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("contractService")
public class ContractServiceImpl extends ServiceImpl<Contract> implements ContractService {

    @Autowired
    private ContractDao contractDao;

    @Override
    @Transactional
    public List<String> findContactsByUserLogin(String login) {
        return contractDao.findAllContactsByUserId(login);
    }

    @Override
    public Contract getContractByNumber(String number) {
        return contractDao.getContractByNumber(number);
    }

    @Override
    public User findUserByNumber(String number) {
        return contractDao.findUserByNumber(number);
    }

    @Override
    protected JpaDao<Contract> getDefaultDao() {
        return contractDao;
    }
}
