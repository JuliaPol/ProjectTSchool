package com.tsystems.ecare.facade;

import com.tsystems.ecare.dto.ContractDTO;
import com.tsystems.ecare.dto.CustomerDTO;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.form.BasketForm;

import java.util.List;

public interface ContractFacade extends Facade<ContractDTO, Contract> {
    ContractDTO getContractByNumber(String number);

    UserDTO findUserByNumber(String number);

    void changeContractStatusByEmployee(String number);

    void changeContractStatusByCustomer(String number);

    void deleteRate(String number);

    void deleteOption(String number, Long optionId);

    void addRateOrOptionsInContract(BasketForm basket);

    List<CustomerDTO> searchByNumber(String likeName, int limit);

    List<CustomerDTO> searchByName(String likeName, int limit);

    void create(Long id, ContractDTO contractDTO);
}
