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

@WebServlet("/edit-user")
public class EditUserController extends HttpServlet {

    private final UserService userService;

    public EditUserController() {
        this.userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setId(Integer.parseInt(req.getParameter("id")));
        user.setName(req.getParameter("name"));
        user.setEmail(req.getParameter("email"));
        if(!req.getParameter("password").isBlank() ){
            user.setPassword(req.getParameter("password"));
        }else{
            user.setPassword(userService.getByEmail(user.getEmail()).getPassword());
        }

        user.setAddress(req.getParameter("address"));
        User user1 = userService.updateUserById(user.getId(), user);
        HttpSession session = req.getSession();
        if(user1!= null){
            session.setAttribute("message", "User updated");
        }
        else {
            session.setAttribute("message", "User could not get updated");
        }
        User activeUser = (User) session.getAttribute("activeUser");
        assert user1 != null;
        if(user1.getId().equals(activeUser.getId())){
            resp.sendRedirect("allOrders.jsp");
        }
        else {
            resp.sendRedirect("allUsers.jsp");
        }
    }
}
