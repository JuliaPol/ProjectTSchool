package com.tsystems.ecare.controller;

import com.tsystems.ecare.exception.ContractUpdateException;
import com.tsystems.ecare.exception.LoginFailedException;
import com.tsystems.ecare.exception.ResourcePermissionException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice(basePackages = "com.tsystems.ecare.controller")
@Component("globalExceptionHandler")
public class GlobalExceptionHandler {

    private static final Logger LOG = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(LoginFailedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void onLoginFailedException(Exception exception) {
        logException(exception);
    }


    @ExceptionHandler(ResourcePermissionException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String onResourcePermissionException(Exception exception) {
        logException(exception);
        return "You have not permission";
    }

    @ExceptionHandler(ContractUpdateException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void onContractUpdateException(Exception exception) {
        logException(exception);
    }

    private void logException(Exception e) {
        LOG.error(e);
    }
}
