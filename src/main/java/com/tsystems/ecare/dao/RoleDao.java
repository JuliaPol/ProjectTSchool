package com.tsystems.ecare.dao;

import com.tsystems.ecare.entities.Role;

import java.util.List;

public interface RoleDao extends JpaDao<Role>{
    List getAllUsersByRole(String role);
}
