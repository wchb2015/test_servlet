package com.wchbTest.controller;

import com.wchbTest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(HelloServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        synchronized (this) {
            LOG.info("haha {}", req.getHeaderNames());

            try {
                LOG.info("sleep 4秒");
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resp.setStatus(200);
            resp.setHeader("haha", "hehe");

            new UserService().save();
            LOG.info("success!!!");
        }
    }
}
