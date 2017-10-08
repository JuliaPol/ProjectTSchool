package com.tsystems.ecare.service;

import com.tsystems.ecare.entities.Contract;

import java.util.List;

public interface ContractService  extends Service<Contract> {
    List<String> findContactsByUserLogin(String login);
    Contract getContractByNumber(String number);
}
