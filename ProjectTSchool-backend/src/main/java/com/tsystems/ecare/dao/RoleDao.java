package com.tsystems.ecare.dao;

import com.tsystems.ecare.entities.Role;
import com.tsystems.ecare.entities.User;

import java.util.List;

public interface RoleDao extends JpaDao<Role>{
    List<User> getAllCustomers();
    Role getByName(String name);
}
