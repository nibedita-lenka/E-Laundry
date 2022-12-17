package com.elaundry.controllers.user;


import com.elaundry.entity.User;
import com.elaundry.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class UserLoginController extends HttpServlet {
    private final UserService userService;

    public UserLoginController() {
        this.userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        boolean valid = userService.matchCredentials(email, password);
        HttpSession session = req.getSession();
        if (valid) {
            User byEmail = userService.getByEmail(email);
            session.setAttribute("activeUser", byEmail);
            session.setAttribute("message", "Login successful");
            System.out.println("login success with user mail - " + byEmail);
            resp.sendRedirect("allOrders.jsp");
        } else {
            session.setAttribute("message", "try again !!!");
            resp.sendRedirect("index.jsp");
            System.out.println("Login unsuccessful with user mail - " + email);
        }
    }
}
