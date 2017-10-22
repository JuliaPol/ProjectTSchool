package com.tsystems.ecare.facade.impl;

import com.tsystems.ecare.dto.ContractDTO;
import com.tsystems.ecare.dto.CustomerDTO;
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
import com.tsystems.ecare.form.BasketForm;
import com.tsystems.ecare.service.ContractService;
import com.tsystems.ecare.service.OptionService;
import com.tsystems.ecare.service.Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public ContractDTO getContractByNumber(String number) {
        return convertToDto(contractService.getContractByNumber(number));
    }

    @Override
    public UserDTO findUserByNumber(String number) {
        return userFacade.convertToDto(contractService.findUserByNumber(number));
    }

    /**
     * The method changes contract status to "BLOCKED_BY_AN_EMPLOYEE" or "AVAILABLE"
     * @param number
     */
    @Override
    public void changeContractStatusByEmployee(String number) {
        contractService.changeContractStatusByEmployee(number);
    }

    /**
     * The method changes contract status to "BLOCKED_BY_THE_CUSTOMER" or "AVAILABLE"
     * @param number
     */
    @Override
    public void changeContractStatusByCustomer(String number) {
        contractService.changeContractStatusByCustomer(number);
    }

    /**
     * The method deletes Tariff in Contract.
     * @param number
     */
    @Override
    public void deleteRate(String number) {
        contractService.deleteRate(number);
    }

    /**
     * The method deletes Option in Contract.
     * @param number
     * @param optionId
     */
    @Override
    public void deleteOption(String number, Long optionId) throws Exception {
        contractService.deleteOption(number, optionId);
    }

    /**
     * The method takes Tariff or Options from the basket and adds to Contract.
     * @param basket
     */
    @Override
    public void addRateOrOptionsInContract(BasketForm basket) throws Exception {
        if (basket.getRate()!= null) {
            contractService.addRateInContract(basket.getNumber(),
                    Long.parseLong(basket.getRate()));
        } else {
            if (!(basket.getOptions().isEmpty() || basket.getOptions() == null)) {
                List<Long> options = basket.getOptions().stream()
                        .map(Long::parseLong)
                        .collect(Collectors.toList());
                contractService.addOptionsInContract(basket.getNumber(), options);
            }
        }
    }

    @Override
    public List<CustomerDTO> searchByNumber(String likeName, int limit) {
        return contractService.searchByNumber(likeName, limit).stream()
                .map(userFacade::convertToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> searchByName(String likeName, int limit) {
        return contractService.searchByName(likeName , limit).stream()
                .map(userFacade::convertToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContractDTO convertToDto(Contract entity) {
        ContractDTO contractDTO = modelMapper.map(entity, ContractDTO.class);
        contractDTO.setStatus(entity.getStatus().toString());
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
        return modelMapper.map(dto, Contract.class);
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
        contract.setOptionList(options);
        contractService.updateContract(contract);
    }

    @Override
    protected Service<Contract> getDefaultService() {
        return contractService;
    }
}
