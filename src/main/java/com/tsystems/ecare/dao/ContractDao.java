package com.tsystems.ecare.dao;

import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.entities.Option;

import java.util.List;

public interface ContractDao extends JpaDao<Contract> {
    List<Option> getAllOption(Long id);
}
