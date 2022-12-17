package com.elaundry.dao;

import com.elaundry.entity.Order;
import com.elaundry.enums.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.elaundry.enums.SqlQuery.*;

public class OrderDao {
    private final Connection db;
    private PreparedStatement ps;

    public OrderDao() {
        this.db = GetConnection.initialize();
    }

    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try {
            ps = db.prepareStatement(GET_ALL_ORDERS.getValue());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orders.add(getOrderFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error - " + e.getMessage());
        }
        return orders;
    }


    public List<Order> findAll(Integer userId) {
        List<Order> orders = new ArrayList<>();
        try {
            ps = db.prepareStatement(GET_ALL_ORDERS_BY_USER_ID.getValue());
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orders.add(getOrderFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error - " + e.getMessage());
        }
        return orders;
    }

    public Order save(Order order) {
        try {
            ps = db.prepareStatement(CREATE_NEW_ORDER.getValue());
            ps.setInt(1, order.getUserId());
            ps.setString(2, order.getUserName());
            ps.setString(3, order.getItems().toString().replace("[", "").replace("]", ""));
            ps.setInt(4, order.getPrice());
            ps.setString(5, order.getOrderDate().toString());
            ps.setString(6, order.getCompletionDate().toString());
            ps.setString(7, order.getStatus().toString());
            if (ps.executeUpdate() > 0)
                return order;
        } catch (SQLException e) {
            System.out.println("Error - " + e.getMessage());
        }
        return null;
    }

    public boolean updateStatusById(Integer orderId) {
        try {
            ps = db.prepareStatement(UPDATE_STATUS_BY_ID.getValue());
            ps.setString(1, OrderStatus.DONE.toString());
            ps.setString(2, LocalDate.now().toString());
            ps.setInt(3, orderId);
            int i = ps.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error - " + e.getMessage());
        }
        return false;
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setUserId(resultSet.getInt("user_id"));
        order.setUserName(resultSet.getString("user_name"));
        order.setItems(Arrays.asList(resultSet.getString("items").split(",")));
        order.setPrice(resultSet.getInt("price"));
        order.setOrderDate(LocalDate.parse(resultSet.getString("order_date")));
        order.setCompletionDate(LocalDate.parse(resultSet.getString("complete_date")));
        order.setStatus(Enum.valueOf(OrderStatus.class, resultSet.getString("status")));
        return order;
    }
}
