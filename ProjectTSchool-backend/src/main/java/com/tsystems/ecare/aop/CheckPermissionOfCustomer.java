package com.tsystems.ecare.aop;

import com.tsystems.ecare.dao.ContractDao;
import com.tsystems.ecare.dao.UserDao;
import com.tsystems.ecare.dto.ContractDTO;
import com.tsystems.ecare.entities.Contract;
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

    @Around("@annotation(com.tsystems.ecare.aop.annotation.CheckCustomerPermission) && args(contractDTO, principal)")
    public Object checkPermission(ProceedingJoinPoint joinPoint, ContractDTO contractDTO, Principal principal) throws Throwable {
        return getObject(joinPoint, principal, contractDao.get(contractDTO.getId()));
    }

    @Around("@annotation(com.tsystems.ecare.aop.annotation.CheckCustomerPermission) && args(id, principal,..)")
    public Object checkPermission(ProceedingJoinPoint joinPoint, Long id, Principal principal) throws Throwable {
        return getObject(joinPoint, principal, contractDao.get(id));
    }

    private Object getObject(ProceedingJoinPoint joinPoint, Principal principal, Contract contract) throws Throwable {
        User user = userDao.findByLogin(principal.getName());
        if (util.isManager() || (util.isCustomer())
                && user.getContractList() != null
                && user.getContractList().contains(contract)) {
            return joinPoint.proceed();
        } else {
            throw new ResourcePermissionException();
        }
    }
}
