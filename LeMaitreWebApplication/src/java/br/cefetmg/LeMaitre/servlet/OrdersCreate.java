/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.servlet;

import br.cefetmg.LeMaitre.model.dao.ItemDAO;
import br.cefetmg.LeMaitre.model.dao.ItemDAOImpl;
import br.cefetmg.LeMaitre.model.dao.OrderDAO;
import br.cefetmg.LeMaitre.model.dao.OrderDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Item;
import br.cefetmg.LeMaitre.model.domain.Order;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.model.service.ItemManagement;
import br.cefetmg.LeMaitre.model.service.ItemManagementImpl;
import br.cefetmg.LeMaitre.model.service.OrderManagement;
import br.cefetmg.LeMaitre.model.service.OrderManagementImpl;
import br.cefetmg.LeMaitre.util.Result;
import br.cefetmg.LeMaitre.util.ServletUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Paula Ribeiro
 * url: http://localhost:8080/LeMaitreWebApplication/OrdersCreate
 */
public class OrdersCreate extends HttpServlet {
    
    private OrderManagement orderManagement;
    private Result result;
    private ServletUtil util;
    private Gson gson;
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        result = new Result();
        util = new ServletUtil();
        gson = new Gson();
        
        try {
            
            String payload = util.getJson(request);
            Orders orders = this.ordersFromJson(payload);
            
            OrderDAO orderDAO = OrderDAOImpl.getInstance();
            orderManagement = new OrderManagementImpl(orderDAO);
            
            ItemDAO itemDAO = ItemDAOImpl.getInstance();
            ItemManagement itemManagement = new ItemManagementImpl(itemDAO);
            
            List<Order> ord = new ArrayList();
            
            for (Components item: orders.getItens()) {
                Item i = itemManagement.getItemByID(item.getId());
                
                Order order = new Order();
                order.setCodToken(orders.getCodToken());
                order.setCodItem(i.getCodItem());
                order.setQtdItem(item.getQtd());
                order.setVlrPrice(i.getVlrPrice());
                order.setIdtStatus('N');
                
                if (order.getQtdItem() == 0) {
                    order.setQtdItem(1);
                }
                
                orderManagement.orderInsert(order);
                ord.add(order);
            }
            
            result.setStatusOK();
            result.setContent(ord);
            
        } catch (BusinessException | PersistenceException ex) {
            result.setContent(ex.getMessage());
            result.setStatusBADREQUEST();
        }
        
        finally {
            PrintWriter writer = response.getWriter();
            writer.println(gson.toJson(result));
        }
    }
    
    private Orders ordersFromJson(String str) {
        SqlDateTypeAdapter sqlAdapter = new SqlDateTypeAdapter();
        gson = new GsonBuilder()
           .registerTypeAdapter(java.sql.Date.class, sqlAdapter)
           .setDateFormat("yyyy-MM-dd hh:mm:ss a")
           .create();
        Orders orders = gson.fromJson(str, Orders.class);
        return orders;
    }
}


class Orders {
    private String codToken;
    private List<Components> items;
    
    public Orders() {
    }

    public Orders(String codToken, List<Components> items) {
        this.codToken = codToken;
        this.items = items;
    }

    public String getCodToken() {
        return codToken;
    }

    public void setCodToken(String codToken) {
        this.codToken = codToken;
    }

    public List<Components> getItens() {
        return items;
    }

    public void setItens(List<Components> items) {
        this.items = items;
    }
    
}

class Components {
    public int id;
    public int quantity;

    public Components() {
    }

    public Components(int id, int qtd) {
        this.id = id;
        this.quantity = qtd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtd() {
        return quantity;
    }

    public void setQtd(int qtd) {
        this.quantity = qtd;
    }
    
    
}
