package com.wchbTest.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDao {

    private static final Logger LOG = LoggerFactory.getLogger(UserDao.class);

    public void save() {
        LOG.info("UserDao save...");
    }
}
