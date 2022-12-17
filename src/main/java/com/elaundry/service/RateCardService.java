package com.elaundry.service;

import com.elaundry.dao.RateCardDao;
import com.elaundry.entity.RateCard;

import java.util.HashMap;
import java.util.Map;

public class RateCardService {
    private final RateCardDao rateCardDao;

    public RateCardService() {
        this.rateCardDao = new RateCardDao();
    }

    public Map<String, Integer> getAll(){
        Map<String, Integer> price = new HashMap<>();
        rateCardDao.findAll().forEach(rateCard -> {
            price.put(rateCard.getItemName(), rateCard.getPrice());
        });
        return price;
    }

    public RateCard getByName(String name){
        return rateCardDao.findByName(name);
    }

    public RateCard updatePriceById(Integer id, Integer price){
        return rateCardDao.updatePriceById(id, price);
    }
}
