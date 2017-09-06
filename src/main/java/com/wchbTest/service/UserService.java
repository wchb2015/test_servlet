package com.wchbTest.service;


import com.wchbTest.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);


    public void save() {
        LOG.info("UserService  save...");

        new UserDao().save();
    }
}
