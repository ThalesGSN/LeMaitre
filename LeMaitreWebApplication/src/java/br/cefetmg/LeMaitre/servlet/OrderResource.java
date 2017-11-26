/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.servlet;

import br.cefetmg.LeMaitre.model.dao.ItemDAOImpl;
import br.cefetmg.LeMaitre.model.dao.ItemImageDAOImpl;
import br.cefetmg.LeMaitre.model.dao.OrderDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Image;
import br.cefetmg.LeMaitre.model.domain.Item;
import br.cefetmg.LeMaitre.model.domain.Order;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.model.service.ItemImageManagement;
import br.cefetmg.LeMaitre.model.service.ItemImageManagementImpl;
import br.cefetmg.LeMaitre.model.service.ItemManagement;
import br.cefetmg.LeMaitre.model.service.ItemManagementImpl;
import br.cefetmg.LeMaitre.model.service.OrderManagement;
import br.cefetmg.LeMaitre.model.service.OrderManagementImpl;
import br.cefetmg.LeMaitre.util.Result;
import com.google.gson.Gson;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
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
                
                List<Content> content = this.setOrders(orders);
                
                result.setContent(content);
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
    
    public List<Content> setOrders(List<Order> orders) throws PersistenceException {
        List<Content> content = new ArrayList();
        ItemManagement itemManagement = new ItemManagementImpl(ItemDAOImpl.getInstance());
        ItemImageManagement itemImageManagement = new ItemImageManagementImpl(ItemImageDAOImpl.getInstance());
        Content o;
        ItemOrdered i;
        
        for (Order order: orders) {
            o = new Content();
            o.setCodToken(order.getCodToken());
            o.setDatOrder(order.getDatOrder());
            o.setIdtStatus(order.getIdtStatus());
            o.setQtdItem(order.getQtdItem());
            o.setVlrPrice(order.getVlrPrice());
            
            Item item = itemManagement.getItemByID(order.getCodItem());
            i = new ItemOrdered();
            i.setCodItem(item.getCodItem());
            i.setDesItem(item.getDesItem());
            i.setIsAvaliable(item.isIsAvaliable());
            i.setNomItem(item.getNomItem());
            i.setSeqCategory(item.getSeqCategory());
            i.setSeqSubCategory(item.getSeqSubcategory());
               
            List<Image> images = itemImageManagement.getImagesByItemID(item.getCodItem());
            i.setImages(images);
            o.setItem(i);
            
            content.add(o);
            
        }
        
        return content;
    }
}

class Content {
    private String codToken;
    private Timestamp datOrder;
    private char idtStatus;
    private double vlrPrice;
    private int qtdItem;
    private ItemOrdered item;

    public Content() {
    }

    public Content(String codToken, Timestamp datOrder, char idtStatus, double vlrPrice, int qtdItem, ItemOrdered item) {
        this.codToken = codToken;
        this.datOrder = datOrder;
        this.idtStatus = idtStatus;
        this.vlrPrice = vlrPrice;
        this.qtdItem = qtdItem;
        this.item = item;
    }
    
    

    public String getCodToken() {
        return codToken;
    }

    public void setCodToken(String codToken) {
        this.codToken = codToken;
    }

    public Timestamp getDatOrder() {
        return datOrder;
    }

    public void setDatOrder(Timestamp datOrder) {
        this.datOrder = datOrder;
    }

    public char getIdtStatus() {
        return idtStatus;
    }

    public void setIdtStatus(char idtStatus) {
        this.idtStatus = idtStatus;
    }

    public double getVlrPrice() {
        return vlrPrice;
    }

    public void setVlrPrice(double vlrPrice) {
        this.vlrPrice = vlrPrice;
    }

    public int getQtdItem() {
        return qtdItem;
    }

    public void setQtdItem(int qtdItem) {
        this.qtdItem = qtdItem;
    }

    public ItemOrdered getItem() {
        return item;
    }

    public void setItem(ItemOrdered item) {
        this.item = item;
    }
    
    
}

class ItemOrdered {
    private int codItem;
    private String nomItem;
    private String desItem;
    private boolean isAvaliable;
    private int seqCategory;
    private int seqSubCategory;
    private List<Image> images;

    public ItemOrdered() {
    }

    public ItemOrdered(int codItem, String nomItem, String desItem, boolean isAvaliable, int seqCategory, int seqSubCategory) {
        this.codItem = codItem;
        this.nomItem = nomItem;
        this.desItem = desItem;
        this.isAvaliable = isAvaliable;
        this.seqCategory = seqCategory;
        this.seqSubCategory = seqSubCategory;
    }

    public int getCodItem() {
        return codItem;
    }

    public void setCodItem(int codItem) {
        this.codItem = codItem;
    }

    public String getNomItem() {
        return nomItem;
    }

    public void setNomItem(String nomItem) {
        this.nomItem = nomItem;
    }

    public String getDesItem() {
        return desItem;
    }

    public void setDesItem(String desItem) {
        this.desItem = desItem;
    }

    public boolean isIsAvaliable() {
        return isAvaliable;
    }

    public void setIsAvaliable(boolean isAvaliable) {
        this.isAvaliable = isAvaliable;
    }

    public int getSeqCategory() {
        return seqCategory;
    }

    public void setSeqCategory(int seqCategory) {
        this.seqCategory = seqCategory;
    }

    public int getSeqSubCategory() {
        return seqSubCategory;
    }

    public void setSeqSubCategory(int seqSubCategory) {
        this.seqSubCategory = seqSubCategory;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
    
}
