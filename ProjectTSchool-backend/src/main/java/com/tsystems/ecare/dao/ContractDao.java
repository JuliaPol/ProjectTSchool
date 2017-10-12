package com.tsystems.ecare.dao;

import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.entities.User;

import java.util.List;

public interface ContractDao extends JpaDao<Contract> {
    List<Option> getAllOption(Long id);
    List<String> findAllContactsByUserId(String login);
    Contract getContractByNumber(String number);
    User findUserByNumber(String number);
}
