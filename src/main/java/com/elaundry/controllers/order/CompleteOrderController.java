package com.elaundry.controllers.order;


import com.elaundry.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/complete-order")
public class CompleteOrderController extends HttpServlet {

    private final OrderService orderService;

    public CompleteOrderController() {
        this.orderService = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderId = Integer.parseInt(req.getParameter("orderId"));
        boolean b = orderService.updateStatus(orderId);
        HttpSession session = req.getSession();
        if(b){
            session.setAttribute("message", "updated status to completed");
        }
        else {
            session.setAttribute("message", "could not complete order");
        }
        resp.sendRedirect("allOrders.jsp");
    }
}
