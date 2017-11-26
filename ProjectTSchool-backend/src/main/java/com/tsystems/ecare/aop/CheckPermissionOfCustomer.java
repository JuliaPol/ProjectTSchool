package com.tsystems.ecare.aop;

import com.tsystems.ecare.dao.ContractDao;
import com.tsystems.ecare.dao.UserDao;
import com.tsystems.ecare.entities.User;
import com.tsystems.ecare.exception.ResourcePermissionException;
import com.tsystems.ecare.util.Util;
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

    @Autowired
    private Util util;

    @Around("@annotation(com.tsystems.ecare.aop.annotation.CheckCustomerPermission) && args(id, principal)")
    public Object checkPermission(ProceedingJoinPoint joinPoint, Long id, Principal principal) throws Throwable {
        User user = userDao.findByLogin(principal.getName());
        if (util.isManager() || (util.isCustomer()
                && user.getContractList() != null
                && user.getContractList().contains(contractDao.get(id)))) {
            return joinPoint.proceed(new Object[]{id, principal});
        } else {
            throw new ResourcePermissionException();
        }
    }
}
