package com.tsystems.ecare.service.impl;

import com.tsystems.ecare.dao.ContractDao;
import com.tsystems.ecare.dao.OptionDao;
import com.tsystems.ecare.dao.RateDao;
import com.tsystems.ecare.dao.UserDao;
import com.tsystems.ecare.entities.Contract;
import com.tsystems.ecare.entities.User;
import com.tsystems.ecare.entities.enums.ContractStatus;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
    private ContractDao contractDao;

    @Autowired
    private ContractService contractService;

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

    @Test
    public void searchByNameTest() {
        String name= "Ivan";
        String name2 = "Ivan2";
        Long id = 11L;
        Long id2 = 12L;
        int limit = 2;
        User user = new User();
        user.setId(id);
        user.setLastName(name);
        User user2 = new User();
        user2.setId(id2);
        user2.setLastName(name2);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);
        when(contractDao.searchByName(name, limit)).thenReturn(userList);
        List<User> c = contractService.searchByName(name, limit);
        assertNotNull(c);
        assertEquals(userList.get(0).getId(),c.get(0).getId());
        assertEquals(userList.get(1).getId(),c.get(1).getId());
    }

    @Test
    public void searchByNumberTest() {
        String number= "123";
        String number2 = "543";
        String number3 = "1234";
        Long id = 11L;
        Long id2 = 12L;
        int limit = 2;
        Contract contract = new Contract();
        Contract contract2 = new Contract();
        Contract contract3 = new Contract();
        contract.setNumber(number);
        contract2.setNumber(number2);
        contract3.setNumber(number3);
        List<Contract> contracts = new ArrayList<>();
        contracts.add(contract);
        contracts.add(contract2);
        List<Contract> contracts2 = new ArrayList<>();
        contracts2.add(contract3);
        User user = new User();
        user.setId(id);
        user.setContractList(contracts);
        User user2 = new User();
        user2.setId(id2);
        user2.setContractList(contracts2);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);
        when(contractDao.searchByNumber(number, limit)).thenReturn(userList);
        List<User> c = contractService.searchByNumber(number, limit);
        assertNotNull(c);
        assertEquals(userList.get(0).getId(),c.get(0).getId());
        assertEquals(userList.get(1).getId(),c.get(1).getId());
    }

    @Test
    public void changeStatusToUnblock() {
        String number = "11111";
        Contract contract = new Contract();
        contract.setId(35L);
        contract.setNumber(number);
        contract.setStatus(ContractStatus.AVAILABLE);
        when(contractService.getContractByNumber(number)).thenReturn(contract);
        contractService.changeContractStatusByCustomer(number);
        assertEquals(contract.getStatus(), ContractStatus.BLOCKED_BY_THE_CUSTOMER);
    }

    @Test
    public void changeStatusToBlock() {
        String number = "11111";
        Contract contract = new Contract();
        contract.setId(35L);
        contract.setNumber(number);
        contract.setStatus(ContractStatus.BLOCKED_BY_THE_CUSTOMER);
        when(contractService.getContractByNumber(number)).thenReturn(contract);
        contractService.changeContractStatusByCustomer(number);
        assertEquals(contract.getStatus(), ContractStatus.AVAILABLE);
    }

    @Test
    public void tryToChangeStatusToUnblock() {
        String number = "11111";
        Contract contract = new Contract();
        contract.setId(35L);
        contract.setNumber(number);
        contract.setStatus(ContractStatus.BLOCKED_BY_AN_EMPLOYEE);
        when(contractService.getContractByNumber(number)).thenReturn(contract);
        contractService.changeContractStatusByCustomer(number);
        assertNotEquals(contract.getStatus(), ContractStatus.AVAILABLE);
    }

    @Test
    public void changeStatusByEmployeeToUnblock() {
        String number = "11111";
        Contract contract = new Contract();
        contract.setId(35L);
        contract.setNumber(number);
        contract.setStatus(ContractStatus.BLOCKED_BY_AN_EMPLOYEE);
        when(contractService.getContractByNumber(number)).thenReturn(contract);
        contractService.changeContractStatusByEmployee(number);
        assertEquals(contract.getStatus(), ContractStatus.AVAILABLE);
    }


    @Test
    public void changeCustomerStatusByEmployeeToUnblock() {
        String number = "11111";
        Contract contract = new Contract();
        contract.setId(35L);
        contract.setNumber(number);
        contract.setStatus(ContractStatus.BLOCKED_BY_THE_CUSTOMER);
        when(contractService.getContractByNumber(number)).thenReturn(contract);
        contractService.changeContractStatusByEmployee(number);
        assertEquals(contract.getStatus(), ContractStatus.AVAILABLE);
    }

    @Test
    public void changeStatusByEmployeeToBlock() {
        String number = "11111";
        Contract contract = new Contract();
        contract.setId(35L);
        contract.setNumber(number);
        contract.setStatus(ContractStatus.AVAILABLE);
        when(contractService.getContractByNumber(number)).thenReturn(contract);
        contractService.changeContractStatusByEmployee(number);
        assertEquals(contract.getStatus(), ContractStatus.BLOCKED_BY_AN_EMPLOYEE);
    }

    @Test
    public void findUserByNumber() {
        String number = "111111";
        Long id = 11L;
        User user = new User();
        user.setId(id);
        when(contractDao.findUserByNumber(number)).thenReturn(user);
        User user1 = contractService.findUserByNumber(number);
        assertNotNull(user1);
        assertEquals(id, user1.getId());
    }
}
