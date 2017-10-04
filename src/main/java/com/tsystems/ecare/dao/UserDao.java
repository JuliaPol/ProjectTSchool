package com.tsystems.ecare.dao;

import com.tsystems.ecare.entities.Role;
import com.tsystems.ecare.entities.User;

import java.util.List;

public interface UserDao extends JpaDao<User> {
    User findByLogin(String login);
}
