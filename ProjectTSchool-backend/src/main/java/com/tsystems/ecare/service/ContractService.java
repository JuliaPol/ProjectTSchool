package com.tsystems.ecare.service;

import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.entities.User;

import java.util.List;

/**
 * Service for {@link Contract}
 */

public interface ContractService extends Service<Contract> {
    List<String> findContactsByUserLogin(String login);

    Contract getContractByNumber(String number);

    User findUserByNumber(String number);

    void changeContractStatusByEmployee(String number);

    void changeContractStatusByCustomer(String number);

    void deleteRate(String number);

    void deleteOption(String number, Long optionId);

    void addRateInContract(String number, Long rateId);

    void addOptionsInContract(String number, List<Long> optionIds);

    void addOptionInContract(String number, Long optionId);

    List<User> searchByNumber(String likeNumber, int limit);

    List<User> searchByName(String likeName, int limit);

    void create(Long id, Contract contract);
}
