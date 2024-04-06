/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import entity.SurplusFood;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.SurplusFoodService;

import service.impl.SurplusFoodServiceImpl;

/**
 *
 * @author liuma
 */
@WebServlet(name = "ClientServlet", urlPatterns = {"/ClientServlet"})
public class ClientServlet extends HttpServlet {
    private SurplusFoodService surplusFoodService = null;
    
    @Override
    public void init() throws ServletException {
        super.init();
        this.surplusFoodService = new SurplusFoodServiceImpl();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<SurplusFood> surplusfoods = null;     
        surplusfoods = surplusFoodService.getAllSurplusFood();
        request.setAttribute("surplusfoods", surplusfoods);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/consumer_dashboard.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     doGet(request,response);
     
     purchaseFood(request, response);
    
    }
    
    private void purchaseFood(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int foodID = Integer.parseInt(request.getParameter("foodID"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        surplusFoodService.updateSurplusQuantity(foodID, quantity);
        
        request.setAttribute("successMessage", "Checked out successful!");
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/consumer_dashboard.jsp");
        dispatcher.forward(request, response);
        
    }
        /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}