package com.tsystems.ecare.controller.main;

import com.tsystems.ecare.dto.RoleDTO;
import com.tsystems.ecare.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {
    @Autowired
    private Util util;

    @RequestMapping(value = "/checkRole")
    public RoleDTO checkRole() {

        if (util.isManager())
            return new RoleDTO("ROLE_MANAGER");
        else if (util.isCustomer())
            return new RoleDTO("ROLE_CUSTOMER");
        else
            return new RoleDTO("ERROR");
    }
}
