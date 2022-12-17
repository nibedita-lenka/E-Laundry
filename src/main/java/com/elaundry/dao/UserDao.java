package com.elaundry.dao;

import com.elaundry.entity.User;
import com.elaundry.enums.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.elaundry.enums.SqlQuery.*;

public class UserDao {
    private final Connection db;
    private PreparedStatement ps;

    public UserDao() {
        this.db = GetConnection.initialize();
    }

    public User save(User user) {
        try {
            ps = db.prepareStatement(INSERT_NEW_USER.getValue());
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getRole().toString());
            if (ps.executeUpdate() > 0) {
                return findByEmail(user.getEmail());
            }
        } catch (SQLException e) {
            System.out.println("Error - " + e.getMessage());
        }
        return null;
    }

    public User findById(Integer id) {
        try {
            ps = db.prepareStatement(GET_USER_BY_ID.getValue());
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error - " + e.getMessage());
        }
        return null;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            ps = db.prepareStatement(GET_ALL_USERS.getValue());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(getUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error - " + e.getMessage());
        }

        return users;
    }

    public User findByEmail(String email) {
        try {
            ps = db.prepareStatement(GET_USER_BY_EMAIL.getValue());
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error - " + e.getMessage());
        }
        return null;
    }

    public User updateUser(User user){
        try{
            ps = db.prepareStatement(UPDATE_USER.getValue());
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getAddress());
            ps.setInt(5, user.getId());
            int i = ps.executeUpdate();
            if(i>0){
                return findByEmail(user.getEmail());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public boolean deleteById(Integer id) {
        try {
            ps = db.prepareStatement(DELETE_USER_BY_ID.getValue());
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error - " + e.getMessage());
        }
        return false;
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setAddress(resultSet.getString("address"));
        user.setRole(Enum.valueOf(Role.class, resultSet.getString("role")));
        return user;
    }
}
