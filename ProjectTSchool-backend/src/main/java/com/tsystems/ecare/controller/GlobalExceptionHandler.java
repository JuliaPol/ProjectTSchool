package com.tsystems.ecare.controller;

import com.tsystems.ecare.exception.LoginFailedException;
import com.tsystems.ecare.exception.ResourcePermissionException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    private void logException(Exception e) {
        LOG.error(e);
    }
}
