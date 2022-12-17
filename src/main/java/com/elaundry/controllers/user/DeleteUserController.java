package com.elaundry.controllers.user;

import com.elaundry.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/delete-user")
public class DeleteUserController extends HttpServlet {

    private final UserService userService;

    public DeleteUserController() {
        this.userService = new UserService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("userId"));
        boolean b = userService.deleteById(id);
        if(b){
            req.getSession().setAttribute("message", "User deleted successfully");
        }else{
            req.getSession().setAttribute("message", "User could not be deleted");
        }
        resp.sendRedirect("allUsers.jsp");
    }
}
