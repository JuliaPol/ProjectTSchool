package com.tsystems.ecare.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("util")
public class Util {

    private static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";
    private static final String ROLE_MANAGER = "ROLE_MANAGER";

    public boolean isCustomer() {
        return userInRole(ROLE_CUSTOMER);
    }

    public boolean isManager() {
        return userInRole(ROLE_MANAGER);
    }

    private boolean userInRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream().filter(ga ->
                Objects.equals(ga.getAuthority(), role)).findAny().isPresent();
    }
}
