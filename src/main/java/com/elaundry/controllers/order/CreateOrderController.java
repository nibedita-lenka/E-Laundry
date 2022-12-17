package com.elaundry.controllers.order;

import com.elaundry.entity.Order;
import com.elaundry.entity.User;
import com.elaundry.enums.OrderStatus;
import com.elaundry.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/create-order")
public class CreateOrderController extends HttpServlet {

    private final OrderService orderService;

    public CreateOrderController() {
        this.orderService = new OrderService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = new Order();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("activeUser");
        order.setUserId(user.getId());
        order.setOrderDate(LocalDate.now());
        order.setPrice(Integer.parseInt(req.getParameter("totalPrice")));
        order.setStatus(OrderStatus.WORK_IN_PROGRESS);
        order.setUserName(user.getName());
        order.setCompletionDate(LocalDate.now().plusDays(7));
        int shirt = 0;
        int pant = 0;
        int tshirt = 0;
        int blanket = 0;
        int winter = 0;
        int other = 0;

        if(!req.getParameter("shirt").isEmpty())
            shirt = Integer.parseInt(req.getParameter("shirt"));
        if(!req.getParameter("pant").isEmpty())
            pant = Integer.parseInt(req.getParameter("pant"));
        if(!req.getParameter("tshirt").isEmpty())
            tshirt = Integer.parseInt(req.getParameter("tshirt"));
        if(!req.getParameter("blanket").isEmpty())
            blanket = Integer.parseInt(req.getParameter("blanket"));
        if(!req.getParameter("winter").isEmpty())
            winter = Integer.parseInt(req.getParameter("winter"));
        if(!req.getParameter("other").isEmpty())
            other = Integer.parseInt(req.getParameter("other"));
        List<String> items = new ArrayList<>();
        if (shirt > 0)
            items.add("Shirt");
        if (pant > 0)
            items.add("Pant");
        if (tshirt > 0)
            items.add("T Shirt");
        if(blanket>0)
            items.add("Blanket");
        if(winter>0)
            items.add("Winter Cloth");
        if(other>0)
            items.add("other");
        order.setItems(items);
        Order order1 = orderService.createOrder(order);
        if(order1!=null){
            session.setAttribute("message", "Order placed successfully");
            resp.sendRedirect("allOrders.jsp");
        }else{
            session.setAttribute("message", "Order could not be placed try again");
            resp.sendRedirect("placerOrder.jsp");
        }
    }
}
