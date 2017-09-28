package com.tsystems.ecare.controller;

import com.tsystems.ecare.dao.impl.CustomerDaoImpl;
import com.tsystems.ecare.entities.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerDaoImpl customerDao;

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerEntity>> getAll() {
        return new ResponseEntity<>(customerDao.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<CustomerEntity> getCustomerById(@PathVariable long id) {
        return new ResponseEntity<>(customerDao.get(id), HttpStatus.OK);
    }
}
