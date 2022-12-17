package com.elaundry.dao;

import com.elaundry.entity.RateCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.elaundry.enums.SqlQuery.*;

public class RateCardDao {
    private final Connection db;
    private PreparedStatement ps;

    public RateCardDao() {
        this.db = GetConnection.initialize();
    }

    public List<RateCard> findAll(){
        List<RateCard> rateCards = new ArrayList<>();
        try {
            ps = db.prepareStatement(GET_ALL_RATE_CARDS.getValue());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rateCards.add(getRateCardFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error - " + e.getMessage());
        }
        return rateCards;
    }

    public RateCard findByName(String name){
        try{
            ps = db.prepareStatement(FIND_RATE_CARD_BY_NAME.getValue());
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next())
                return getRateCardFromResultSet(resultSet);
        } catch (SQLException e) {
            System.out.println("Error - " + e.getMessage());
        }
        return null;
    }

    public RateCard updatePriceById(Integer id, Integer price){
        try{
            ps = db.prepareStatement(UPDATE_RATE_CARD_PRICE.getValue());
            ps.setInt(1, price);
            ps.setInt(2, id);
            int i = ps.executeUpdate();
            if(i>0)
                return findById(id);
        } catch (SQLException e) {
            System.out.println("Error - " + e.getMessage());
        }
        return null;
    }

    public RateCard findById(Integer id){
        try{
            ps = db.prepareStatement(FIND_RATE_CARD_BY_ID.getValue());
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return getRateCardFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Error - " + e.getMessage());
        }
        return null;
    }
    private RateCard getRateCardFromResultSet(ResultSet resultSet) throws SQLException {
        RateCard rateCard = new RateCard();
        rateCard.setId(resultSet.getInt("id"));
        rateCard.setItemName(resultSet.getString("item_name"));
        rateCard.setPrice(resultSet.getInt("price"));
        return rateCard;
    }


}
