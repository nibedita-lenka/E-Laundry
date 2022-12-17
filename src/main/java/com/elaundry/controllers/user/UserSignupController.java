package com.elaundry.controllers.user;


import com.elaundry.enums.Role;
import com.elaundry.service.UserService;
import com.elaundry.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/signup")
public class UserSignupController extends HttpServlet {
    private final UserService userService;

    public UserSignupController() {
        this.userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        user.setAddress(req.getParameter("address"));
        user.setRole(Role.USER);
        HttpSession session = req.getSession();
        if(userService.getByEmail(user.getEmail()) != null){
            session.setAttribute("message", "Email already exists !");
            resp.sendRedirect("signup.jsp");
        }
        else{
            User user1 = userService.createUser(user);
            session.setAttribute("activeUser", user1);
            if(user1.getRole().equals(Role.USER)){
                resp.sendRedirect("allOrders.jsp");
            }
            System.out.println("Signup success");
            session.setAttribute("message", "Sign Up Successful");
        }
    }
}
