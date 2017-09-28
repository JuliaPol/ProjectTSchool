package com.tsystems.ecare.service;

public interface SecurityService {

    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
