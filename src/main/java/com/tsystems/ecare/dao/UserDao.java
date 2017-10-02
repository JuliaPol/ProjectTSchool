package com.tsystems.ecare.dao;

import com.tsystems.ecare.entities.User;

public interface UserDao extends JpaDao<User> {
    User findByLogin(String login);
}
