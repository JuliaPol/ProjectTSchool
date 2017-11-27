package com.tsystems.ecare.service;

import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.entities.User;

import java.util.List;

/**
 * Service for {@link Contract}
 */

public interface ContractService extends Service<Contract> {

    Contract getContractByNumber(String number);

    User findUserByNumber(String number);

    void changeContractStatusByEmployee(String number);

    void changeContractStatusByCustomer(String number);

    List<User> searchByNumber(String likeNumber, int limit);

    List<User> searchByName(String likeName, int limit);

    void create(Long id, Contract contract);

    void updateContract(Contract contract);

    void delete(Long id);
}
