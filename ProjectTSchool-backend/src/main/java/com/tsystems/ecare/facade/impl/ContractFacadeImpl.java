package com.tsystems.ecare.facade.impl;

import com.tsystems.ecare.dto.ContractDTO;
import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.dto.UserDTO;
import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.entities.Rate;
import com.tsystems.ecare.entities.enums.ContractStatus;
import com.tsystems.ecare.facade.ContractFacade;
import com.tsystems.ecare.facade.OptionFacade;
import com.tsystems.ecare.facade.RateFacade;
import com.tsystems.ecare.facade.UserFacade;
import com.tsystems.ecare.service.ContractService;
import com.tsystems.ecare.service.Service;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component("contractFacade")
public class ContractFacadeImpl extends FacadeImpl<Contract, ContractDTO> implements ContractFacade {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ContractService contractService;

    @Autowired
    private OptionFacade optionFacade;

    @Autowired
    private RateFacade rateFacade;

    @Autowired
    private UserFacade userFacade;

    private static Logger log = Logger.getLogger(ContractFacadeImpl.class);

    @Override
    public UserDTO findUserByNumber(String number) {
        return userFacade.convertToDto(contractService.findUserByNumber(number));
    }

    /**
     * The method changes contract status to "BLOCKED_BY_AN_EMPLOYEE" or "AVAILABLE"
     *
     * @param number
     */
    @Override
    public void changeContractStatusByEmployee(String number) {
        contractService.changeContractStatusByEmployee(number);
    }

    /**
     * The method changes contract status to "BLOCKED_BY_THE_CUSTOMER" or "AVAILABLE"
     *
     * @param number
     */
    @Override
    public void changeContractStatusByCustomer(String number) {
        contractService.changeContractStatusByCustomer(number);
    }

    @Override
    public ContractDTO convertToDto(Contract entity) {
        ContractDTO contractDTO = modelMapper.map(entity, ContractDTO.class);
        contractDTO.setStatus(entity.getStatus().toString());
        contractDTO.dateConverter(entity.getCreationDate());
        if (entity.getOptionList() != null) {
            contractDTO.setOptionList(optionFacade.convertList(entity.getOptionList()));
        }
        if (entity.getRate() != null) {
            contractDTO.setRate(rateFacade.convertToDto(entity.getRate()));
        }
        return contractDTO;
    }

    @Override
    public Contract convertToEntity(ContractDTO dto) {
        Contract contract = modelMapper.map(dto, Contract.class);
        return contract;
    }

    @Override
    public void create(Long id, ContractDTO contractDTO) {
        contractService.create(id, convertToEntity(contractDTO));
    }

    @Override
    public void updateContract(ContractDTO contractDTO) throws Exception {
        Rate rate = modelMapper.map(contractDTO.getRate(), Rate.class);
        Contract contract = contractService.get(contractDTO.getId());
        contract.setOptionList(new ArrayList<>());
        contract.setRate(rate);
        contract.setStatus(ContractStatus.valueOf(contractDTO.getStatus()));
        contractService.updateContract(contract);
    }

    @Override
    public void updateContractOptions(Long id, List<OptionDTO> optionDTOS) throws Exception {
        Contract contract = contractService.get(id);
        List<Option> options = optionFacade.convertToEntitiesList(optionDTOS);
        if (optionFacade.checkNewOptions(optionDTOS, contract.getNumber())
                && optionFacade.checkNewOptionsForCompatible(optionDTOS, contract.getNumber())) {
            contract.setOptionList(options);
            contractService.updateContract(contract);
        } else {
            log.warn("Bad option list");
        }
    }

    @Override
    public void delete(Long id) {
        contractService.delete(id);
    }

    @Override
    protected Service<Contract> getDefaultService() {
        return contractService;
    }
}
