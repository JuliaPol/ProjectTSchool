package com.tsystems.ecare.service;

import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.entities.User;

import java.util.List;

public interface ContractService  extends Service<Contract> {
    List<String> findContactsByUserLogin(String login);
    Contract getContractByNumber(String number);
    User findUserByNumber(String number);
    void changeContractStatusByEmployee(String number);
}
