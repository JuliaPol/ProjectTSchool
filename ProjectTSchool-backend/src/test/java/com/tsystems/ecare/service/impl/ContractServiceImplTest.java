package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.ContractDao;
import com.tsystems.ecare.dao.OptionDao;
import com.tsystems.ecare.dao.RateDao;
import com.tsystems.ecare.dao.UserDao;
import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.service.ContractService;
import com.tsystems.ecare.service.OptionService;
import com.tsystems.ecare.service.RateService;
import com.tsystems.ecare.service.impl.ContractServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class ContractServiceImplTest {

    @Configuration
    static class ContextConfiguration {

        @Bean
        public ContractService contractService() {
            return new ContractServiceImpl();
        }

        @Bean
        public OptionService optionService() {
            return new OptionServiceImpl();
        }

        @Bean
        public RateService rateService() {
            return new RateServiceImpl();
        }

        @Bean
        public ContractDao contractDao() {
            ContractDao contractDao = mock(ContractDao.class);
            return contractDao;
        }

        @Bean
        public UserDao userDao() {
            UserDao userDao = mock(UserDao.class);
            return userDao;
        }

        @Bean
        public OptionDao optionDao() {
            OptionDao optionDao = mock(OptionDao.class);
            return optionDao;
        }

        @Bean
        public RateDao rateDao() {
            RateDao rateDao = mock(RateDao.class);
            return rateDao;
        }
    }

    @Autowired
    private UserDao userDao;

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private ContractService contractService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private RateService rateService;

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private RateDao rateDao;

    @Test
    public void getContractByNumber() {
        String number = "111111";
        Long id = 11L;
        Contract contract = new Contract();
        contract.setId(id);
        when(contractDao.getContractByNumber(number)).thenReturn(contract);
        Contract contract1 = contractService.getContractByNumber(number);
        assertNotNull(contract1);
        assertEquals(id, contract1.getId());
    }

}
