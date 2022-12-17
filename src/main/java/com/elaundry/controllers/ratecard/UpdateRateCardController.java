package com.elaundry.controllers.ratecard;


import com.elaundry.entity.RateCard;
import com.elaundry.service.RateCardService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/update-price")
public class UpdateRateCardController extends HttpServlet {
    private final RateCardService rateCardService;

    public UpdateRateCardController() {
        this.rateCardService = new RateCardService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer price = Integer.parseInt(req.getParameter("price"));
        Integer id = Integer.parseInt(req.getParameter("id"));
        RateCard rateCard = rateCardService.updatePriceById(id, price);
        if(rateCard!=null){
            req.getSession().setAttribute("message", "Price updated");
        }else{
            req.getSession().setAttribute("message", "Price could not be updated");
        }
        resp.sendRedirect("allPrices.jsp");
    }
}
