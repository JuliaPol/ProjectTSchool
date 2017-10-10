package com.tsystems.ecare.facade.impl;

import com.tsystems.ecare.dto.ContractDTO;
import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.facade.ContractFacade;
import com.tsystems.ecare.facade.OptionFacade;
import com.tsystems.ecare.facade.RateFacade;
import com.tsystems.ecare.service.ContractService;
import com.tsystems.ecare.service.Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Override
    public ContractDTO getContractByNumber(String number) {
        return convertToDto(contractService.getContractByNumber(number));
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
    protected Contract convertToEntity(ContractDTO dto) {
        return null;
    }

    @Override
    protected Service<Contract> getDefaultService() {
        return contractService;
    }
}
