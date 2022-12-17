package com.elaundry.controllers.user;

import com.elaundry.entity.User;
import com.elaundry.enums.Role;
import com.elaundry.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/create-admin")
public class CreateAdminController extends HttpServlet {

    private final UserService userService;

    public CreateAdminController() {
        this.userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        user.setAddress(req.getParameter("address"));
        user.setRole(Role.ADMIN);
        User user1 = userService.createUser(user);
        HttpSession session = req.getSession();
        if(userService.getByEmail(user.getEmail()) != null){
            session.setAttribute("message", "Email already exists !");
            resp.sendRedirect("createAdmin.jsp");
        }
        else{
            session.setAttribute("message", "Admin created successfully");
            if(user1.getRole().equals(Role.ADMIN)){
                resp.sendRedirect("allUsers.jsp");
            }
        }
    }
}
