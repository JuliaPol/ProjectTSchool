package com.tsystems.ecare.service;

import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.entities.User;

import java.util.List;

public interface ContractService  extends Service<Contract> {
    List<String> findContactsByUserLogin(String login);
    Contract getContractByNumber(String number);
    User findUserByNumber(String number);
    void changeContractStatusByEmployee(String number);
    void changeContractStatusByCustomer(String number);
    void deleteRate(String number);
    void deleteOption(String number, Long optionId) throws Exception;
    void addRateInContract(String number, Long rateId) throws Exception;
    void addOptionsInContract(String number, List<Long> optionIds) throws Exception;
}
