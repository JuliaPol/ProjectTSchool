package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.config.AppConfig;
import com.tsystems.ecare.dao.ContractDao;
import com.tsystems.ecare.dao.OptionDao;
import com.tsystems.ecare.dao.RateDao;
import com.tsystems.ecare.dao.impl.OptionDaoImpl;
import com.tsystems.ecare.dao.impl.RateDaoImpl;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.entities.User;
import com.tsystems.ecare.service.OptionService;
import com.tsystems.ecare.service.impl.OptionServiceImpl;
import org.junit.Assert;
import org.junit.Before;
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
public class OptionServiceImplTest {

    @Configuration
    static class ContextConfiguration {

        @Bean
        public OptionService optionService() {
            return new OptionServiceImpl();
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

        @Bean
        public ContractDao contractDao() {
            ContractDao contractDao = mock(ContractDao.class);
            return contractDao;
        }
    }

    private List<Option> optionsInContract;
    private List<Option> availableOptions;
    private Option option;
    private Option option1;
    private Option option2;
    private Option option3;
    private Option option5;
    private Option option6;
    private Option option7;

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private RateDao rateDao;

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private OptionService optionService;

    @Before
    public void init() {
        option = new Option();
        option.setId(1L);
        option.setName("opt1");

        option1 = new Option();
        option1.setId(2L);
        option1.setName("opt2");

        option2 = new Option();
        option2.setId(3L);
        option2.setName("opt3");

        option3 = new Option();
        option3.setId(4L);
        option3.setName("opt4");

        option5 = new Option();
        option5.setId(5L);
        option5.setName("opt5");

        option6 = new Option();
        option6.setId(6L);
        option6.setName("opt6");

        option7 = new Option();
        option7.setId(7L);
        option7.setName("opt7");

        List<Option> optionList = new ArrayList<>();
        optionList.add(option);
        optionList.add(option2);
        option3.setCompOptions(optionList);
        List<Option> optionList1 = new ArrayList<>();
        optionList1.add(option1);
        option3.setIncOptions(optionList1);
        List<Option> optionList2 = new ArrayList<>();
        optionList2.add(option6);
        optionList2.add(option7);
        option5.setCompOptions(optionList2);
        option5.setIncOptions(new ArrayList<>());

        optionsInContract = new ArrayList<>();
        optionsInContract.add(option);
        optionsInContract.add(option1);

        availableOptions = new ArrayList<>();
        availableOptions.add(option2);
        availableOptions.add(option3);
        availableOptions.add(option5);
    }

    @Test
    public void checkIncompatibleOptionsTest() throws Exception {
        List<Option> checkIncompOptions = optionService.checkIncompatibleOptions(optionsInContract, availableOptions);
        Assert.assertTrue(checkIncompOptions.contains(option2));
    }

    @Test
    public void checkCompatibleOptionsTest() throws Exception {
        List<Option> checkCompOptions = optionService.checkCompatibleOptions(optionsInContract, availableOptions);
        Assert.assertTrue(!checkCompOptions.contains(option3));
    }

    @Test
    public void checkNewOptionsTest() throws Exception {
        boolean incompatible = optionService.checkNewOptions(availableOptions, optionsInContract);
        Assert.assertFalse(incompatible);
    }

    @Test
    public void checkNewOptionsForCompatibleOptionsTest() throws Exception {
        boolean compatible = optionService.checkOptionListForCompatible(availableOptions, optionsInContract);
        Assert.assertFalse(compatible);
    }

    @Test
    public void getAllOptionsForCustomer() {
        String number = "11111";
        when(optionDao.getAllOptionsForCustomer(number)).thenReturn(availableOptions);
        List<Option> optionList = optionService.getAllOptionsForCustomer(number);
        assertNotNull(optionList);
        assertEquals(availableOptions.get(0).getId(),optionList.get(0).getId());
        assertEquals(availableOptions.get(1).getId(),optionList.get(1).getId());
    }
}
