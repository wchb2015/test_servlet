package com.wchbTest.controller;

import com.wchbTest.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user1 = User.builder().userName("AAA").email("AAA@qq.com").age(21).build();
        User user2 = User.builder().userName("BBB").email("BBB@qq.com").age(22).build();
        User user3 = User.builder().userName("CCC").email("CCC@qq.com").age(23).build();

        request.setAttribute("users", Arrays.asList(user1, user2, user3));

        request.getRequestDispatcher("/WEB-INF/pages/user.jsp").forward(request, response);
    }
}
