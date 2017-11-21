package com.tsystems.ecare.aop;

import com.tsystems.ecare.dao.ContractDao;
import com.tsystems.ecare.dao.UserDao;
import com.tsystems.ecare.entities.User;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Aspect
@Component("checkPermissionOfCustomer")
public class CheckPermissionOfCustomer {
    private static Logger log = Logger.getLogger(CheckPermissionOfCustomer.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private ContractDao contractDao;

    @Around("@annotation(com.tsystems.ecare.aop.annotation.CheckCustomerPermission) && args(number, principal)")
    public Object checkPermission(ProceedingJoinPoint joinPoint, String number, Principal principal) throws Throwable {
        User user = userDao.findByLogin(principal.getName());
        if (user.getContractList() != null && user.getContractList().contains(contractDao.getContractByNumber(number))) {
            return joinPoint.proceed(new Object[]{number, principal});
        } else {
            log.error("Error");
            return joinPoint.proceed(new Object[]{number, principal});
        }
    }
}
