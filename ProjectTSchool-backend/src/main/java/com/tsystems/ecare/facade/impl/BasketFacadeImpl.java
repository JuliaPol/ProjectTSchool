package com.tsystems.ecare.facade.impl;

import com.tsystems.ecare.dto.BasketDTO;
import com.tsystems.ecare.dto.OptionDTO;
import com.tsystems.ecare.dto.RateDTO;
import com.tsystems.ecare.entities.User;
import com.tsystems.ecare.facade.BasketFacade;
import com.tsystems.ecare.facade.OptionFacade;
import com.tsystems.ecare.facade.RateFacade;
import com.tsystems.ecare.facade.UserFacade;
import com.tsystems.ecare.service.Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component("basketFacade")
public class BasketFacadeImpl extends FacadeImpl<User, BasketDTO> implements BasketFacade {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private RateFacade rateFacade;

    @Autowired
    private OptionFacade optionFacade;

    @Override
    public BasketDTO getBasket(String user, String rate, Set<String> optionList) throws Exception {
        BasketDTO basketDTO = new BasketDTO();
        basketDTO.setUser(userFacade.findByLogin(user));
        RateDTO rateDto;
        if (rate != null) {
            rateDto = rateFacade.get(Long.parseLong(rate));
        } else {
            rateDto = new RateDTO();
        }
        basketDTO.setRateId(rateDto.getId());
        basketDTO.setRateName(rateDto.getName());
        basketDTO.setRateCost(rateDto.getCost());
        basketDTO.setRateSms(rateDto.getSms());
        basketDTO.setRateCalls(rateDto.getCalls());
        basketDTO.setRateInternet(rateDto.getInternet());
        basketDTO.setRateDescription(rateDto.getDescription());
        List<OptionDTO> optionDTOList = new ArrayList<>();
        if (optionList != null) {
            for (String id : optionList) {
                optionDTOList.add(optionFacade.get(Long.parseLong(id)));
            }
        }
        if(optionFacade.checkNewOptions(optionDTOList)) {
            basketDTO.setWarning("Options are incompatible!");
        } else {
            basketDTO.setWarning("");
        }
        basketDTO.setOptionList(optionDTOList);
        return basketDTO;
    }

    @Override
    public BasketDTO convertToDto(User entity) {
        return null;
    }

    @Override
    public User convertToEntity(BasketDTO dto) {
        return null;
    }

    @Override
    protected Service<User> getDefaultService() {
        return null;
    }
}
