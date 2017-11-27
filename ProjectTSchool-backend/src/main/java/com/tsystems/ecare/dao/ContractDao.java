package com.tsystems.ecare.dao;

import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.entities.User;

import java.util.List;

public interface ContractDao extends JpaDao<Contract> {
    List<String> findAllContactsByUserId(String login);

    Contract getContractByNumber(String number);

    User findUserByNumber(String number);

    List<User> searchByNumber(String likeNumber, int limit);

    List<User> searchByName(String name, int limit);

    void updateContractWithDeletedRate(Long id);
}
