package com.tsystems.ecare.facade;

import com.tsystems.ecare.dto.ContractDTO;
import com.tsystems.ecare.dto.CustomerDTO;
import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.entities.Contract;

import java.util.List;

public interface ContractFacade extends Facade<ContractDTO, Contract> {

    UserDTO findUserByNumber(String number);

    void changeContractStatusByEmployee(String number);

    void changeContractStatusByCustomer(String number);

    void create(Long id, ContractDTO contractDTO);

    void updateContract(ContractDTO contractDTO) throws Exception;

    void updateContractOptions(Long id, List<OptionDTO> optionDTOS) throws Exception;

    void delete(Long id);
}
