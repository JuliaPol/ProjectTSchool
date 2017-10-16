package com.tsystems.ecare.facade;

import com.tsystems.ecare.dto.ContractDTO;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.form.BasketForm;

public interface ContractFacade extends Facade<ContractDTO, Contract> {
    ContractDTO getContractByNumber(String number);

    UserDTO findUserByNumber(String number);

    void changeContractStatusByEmployee(String number);

    void changeContractStatusByCustomer(String number);

    void deleteRate(String number);

    void deleteOption(String number, Long optionId) throws Exception;

    void addRateOrOptionsInContract(BasketForm basket) throws Exception;
}
