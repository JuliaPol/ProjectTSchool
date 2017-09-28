package com.tsystems.ecare.controller;

import com.tsystems.ecare.dao.impl.UserDaoImpl;
import com.tsystems.ecare.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CustomerController {

//    @Autowired
//    private UserDaoImpl customerDao;
//
//    @RequestMapping(value = "/all", method = RequestMethod.GET)
//    public List<UserEntity> getAllUsers() {
//        return customerDao.getAll();
//    }
//
//    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
//    public UserEntity getCustomerById(@PathVariable long id) {
//        return customerDao.get(id);
//    }
}
