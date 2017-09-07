package com.wchbTest.listener; /**
 * Created by wchb on 9/7/17.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class TestListener implements ServletContextListener {

    private static final Logger LOG = LoggerFactory.getLogger(TestListener.class);

    // Public constructor is required by servlet spec
    public TestListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info(" contextInitialized!!!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info(" contextDestroyed!!!");
    }
}
