package com.elaundry.service;

import com.elaundry.dao.OrderDao;
import com.elaundry.entity.Order;

import java.util.List;

public class OrderService {
    private final OrderDao orderDao;

    public OrderService() {
        this.orderDao = new OrderDao();
    }

    public List<Order> getAll() {
        return orderDao.findAll();
    }

    public List<Order> getAll(Integer userId) {
        return orderDao.findAll(userId);
    }

    public Order createOrder(Order order) {
        return orderDao.save(order);
    }

    public boolean updateStatus(Integer orderId){
        return orderDao.updateStatusById(orderId);
    }

}
