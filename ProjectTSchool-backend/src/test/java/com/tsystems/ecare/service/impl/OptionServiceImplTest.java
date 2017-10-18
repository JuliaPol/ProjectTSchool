package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.OptionDao;
import com.tsystems.ecare.entities.Option;
import com.tsystems.ecare.service.OptionService;
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

    private List<Option> optionsInContract;
    private List<Option> availableOptions;
    private Option option;
    private Option option1;
    private Option option2;
    private Option option3;

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private OptionService optionService = new OptionServiceImpl();

    @Before
    public void init() {
        option = new Option();
        option.setId(1L);
        option.setName("opt1");

        option1 = new Option();
        option.setId(2L);
        option.setName("opt2");

        option2 = new Option();
        option.setId(3L);
        option.setName("opt3");

        option3 = new Option();
        option.setId(4L);
        option.setName("opt4");
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
    }
}
