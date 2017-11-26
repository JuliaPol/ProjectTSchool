package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.ContractDao;
import com.tsystems.ecare.dao.OptionDao;
import com.tsystems.ecare.dao.RateDao;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.entities.Rate;
import com.tsystems.ecare.service.OptionService;
import com.tsystems.ecare.service.RateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class RateServiceImplTest {

    @Configuration
    static class ContextConfiguration {

        @Bean
        public RateService rateService() {
            return new RateServiceImpl();
        }


        @Bean
        public RateDao rateDao() {
            RateDao rateDao = mock(RateDao.class);
            return rateDao;
        }

        @Bean
        public ContractDao contractDao() {
            ContractDao contractDao = mock(ContractDao.class);
            return contractDao;
        }
    }
    @Autowired
    private RateDao rateDao;

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private RateService rateService;

    @Test
    public void findByName() {
        String name = "rate";
        Long id = 11L;
        Rate rate = new Rate();
        rate.setId(id);
        rate.setName(name);
        when(rateDao.findByName(name)).thenReturn(rate);
        Rate rate1 = rateService.findByName(name);
        assertNotNull(rate1);
        assertEquals(id, rate1.getId());
    }

    @Test
    public void findAllRates() {
        String number = "1111";
        Long id = 11L;
        Long id1 = 12L;
        Long id2 = 13L;
        Rate rate = new Rate();
        Rate rate1 = new Rate();
        Rate rate2 = new Rate();
        rate.setId(id);
        rate1.setId(id1);
        rate2.setId(id2);
        List<Rate> rates = new ArrayList<>();
        rates.add(rate);
        rates.add(rate1);
        rates.add(rate2);
        when(rateDao.findAllForCustomer(number)).thenReturn(rates);
        List<Rate> rates1 = rateService.findAllForCustomer(number);
        assertNotNull(rates1);
        assertEquals(rates.get(0).getId(), rates1.get(0).getId());
        assertEquals(rates.get(1).getId(), rates1.get(1).getId());
        assertEquals(rates.get(2).getId(), rates1.get(2).getId());
    }
}

