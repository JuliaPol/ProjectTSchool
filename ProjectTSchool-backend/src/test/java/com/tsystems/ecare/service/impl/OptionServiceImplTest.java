package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.config.AppConfig;
import com.tsystems.ecare.dao.OptionDao;
import com.tsystems.ecare.dao.RateDao;
import com.tsystems.ecare.dao.impl.OptionDaoImpl;
import com.tsystems.ecare.dao.impl.RateDaoImpl;
import com.tsystems.ecare.entities.Option;
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

import static org.mockito.Mockito.mock;

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
    }

    private List<Option> optionsInContract;
    private List<Option> availableOptions;
    private Option option;
    private Option option1;
    private Option option2;
    private Option option3;

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private RateDao rateDao;

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

        List<Option> optionList = new ArrayList<>();
        optionList.add(option);
        optionList.add(option2);
        option3.setCompOptions(optionList);
        List<Option> optionList1 = new ArrayList<>();
        optionList1.add(option1);
        option3.setIncOptions(optionList1);

        optionsInContract = new ArrayList<>();
        optionsInContract.add(option);
        optionsInContract.add(option1);

        availableOptions = new ArrayList<>();
        availableOptions.add(option2);
        availableOptions.add(option3);
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
        boolean incompatible = optionService.checkNewOptions(availableOptions);
        Assert.assertTrue(!incompatible);
    }

}
