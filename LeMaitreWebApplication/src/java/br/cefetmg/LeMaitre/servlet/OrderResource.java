/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.servlet;

import br.cefetmg.LeMaitre.model.dao.OrderDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Order;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.model.service.OrderManagement;
import br.cefetmg.LeMaitre.model.service.OrderManagementImpl;
import br.cefetmg.LeMaitre.util.Result;
import com.google.gson.Gson;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Paula Ribeiro
 * url: http://localhost:8080/LeMaitreWebApplication/webresources/order/
 */
@Path("order")
public class OrderResource {
private OrderManagement orderManagement;
    private Gson gson;
    private Result result;

    /**
     * Creates a new instance of OrderResource
     */
    public OrderResource() {
    }

    /**
     * Retrieves representation of an instance of br.cefetmg.LeMaitre.servlet.OrderResource
     * @param token
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{token}/{dat}/{hour}")
    public String getOrder(@PathParam("token") String token, @PathParam("dat") String date, @PathParam("hour") String hour) {
        try {
            result = new Result();
            orderManagement = new OrderManagementImpl(OrderDAOImpl.getInstance());
            gson = new Gson();
            
            Timestamp datOrder = this.getDate(date, hour);
            
            Order order = orderManagement.getOrderByID(token, datOrder);
            
            if (order == null) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(order);
            }
                        
        } catch (PersistenceException | ParseException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{token}")
    public String getOrders(@PathParam("token") String token) {
        try {
            result = new Result();
            orderManagement = new OrderManagementImpl(OrderDAOImpl.getInstance());
            gson = new Gson();
            
            List<Order> orders = orderManagement.getOrdersByToken(token);
            
            if (orders.isEmpty()) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(orders);
            }
                        
        } catch (PersistenceException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getOrders() {
        try {
            result = new Result();
            orderManagement = new OrderManagementImpl(OrderDAOImpl.getInstance());
            gson = new Gson();
            
            List<Order> orders = orderManagement.listToDoOrders();
            
            if (orders.isEmpty()) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(orders);
            }
                        
        } catch (PersistenceException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{token}/{dat}/{hour}")
    public String deleteOrder(@PathParam("token") String token, @PathParam("dat") String date, @PathParam("hour") String hour) {
        
        try {
            result = new Result();
            orderManagement = new OrderManagementImpl(OrderDAOImpl.getInstance());
            gson = new Gson();

            Timestamp datOrder = this.getDate(date, hour);
            
            boolean bool = orderManagement.orderRemove(token, datOrder);
            
            if (bool) {
                result.setStatusOK();
            }
            else {
                result.setStatusDOESNOTEXIST();
            }
                        
        } catch (PersistenceException | ParseException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }
    
    public Timestamp getDate(String dat, String hour) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date d = format.parse(dat);
        
        Time h = Time.valueOf(hour);
        Time fix = Time.valueOf("00:00:00");
        
        Timestamp date = Timestamp.from(Instant.MIN);
        date.setTime(d.getTime() + h.getTime() - fix.getTime());
                    
        return date;
    }
}
