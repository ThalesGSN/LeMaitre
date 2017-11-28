/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.servlet;

import br.cefetmg.LeMaitre.model.dao.BillDAOImpl;
import br.cefetmg.LeMaitre.model.dao.ItemDAOImpl;
import br.cefetmg.LeMaitre.model.dao.ItemImageDAOImpl;
import br.cefetmg.LeMaitre.model.dao.OrderDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Bill;
import br.cefetmg.LeMaitre.model.domain.Image;
import br.cefetmg.LeMaitre.model.domain.Item;
import br.cefetmg.LeMaitre.model.domain.Order;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.model.service.BillManagement;
import br.cefetmg.LeMaitre.model.service.BillManagementImpl;
import br.cefetmg.LeMaitre.model.service.ItemImageManagement;
import br.cefetmg.LeMaitre.model.service.ItemImageManagementImpl;
import br.cefetmg.LeMaitre.model.service.ItemManagement;
import br.cefetmg.LeMaitre.model.service.ItemManagementImpl;
import br.cefetmg.LeMaitre.model.service.OrderManagement;
import br.cefetmg.LeMaitre.model.service.OrderManagementImpl;
import br.cefetmg.LeMaitre.util.Result;
import com.google.gson.Gson;
import java.sql.Timestamp;
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
 * @author Thalesgsn
 * url: http://localhost:8080/LeMaitreWebApplication/webresources/bill/
 */
@Path("bill")
public class BillResource {

    private BillManagement billManagement;
    private OrderManagement orderManagement;
    private ItemManagement itemManagement;
    private ItemImageManagement itemImageManagement;
    
    private Gson gson;
    private Result result;
    
    /**
     * Creates a new instance of BillResource
     */
    public BillResource() {}
   
    /**
     * Retrieves representation of an instance of br.cefetmg.LeMaitre.servlet.BillResource
     * @param id
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String getBill(@PathParam("id") String id) {
        try {
            result = new Result();
            gson = new Gson();
            
            billManagement = new BillManagementImpl(BillDAOImpl.getInstance());
            orderManagement = new OrderManagementImpl(OrderDAOImpl.getInstance());
            itemManagement = new ItemManagementImpl(ItemDAOImpl.getInstance());
            itemImageManagement = new ItemImageManagementImpl(ItemImageDAOImpl.getInstance());
            
            Bill bill = billManagement.getBillByID(id);
            
            if (bill.getCodToken() == null) {
                result.setStatusDOESNOTEXIST();
            } else {
                
                //Build the Complete Bill
                List<OrderItemAdapter> ordersItemAdapter = new ArrayList();
                for(Order order: orderManagement.getOrdersByToken(id)){
                    
                    Item item = itemManagement.getItemByID(order.getCodItem());
                    List<Image> images = itemImageManagement.getImagesByItemID(order.getCodItem());
                    ItemImageAdapter itemImageAdapter = new ItemImageAdapter(item, images);
                    
                    
                    OrderItemAdapter orderItemAdapter = new OrderItemAdapter(order, itemImageAdapter);
                    ordersItemAdapter.add(orderItemAdapter);
                }
                
                BillOrdersAdapter billOrdersAdapter = 
                        new BillOrdersAdapter(bill, ordersItemAdapter);
               
                result.setContent(billOrdersAdapter);
                
                result.setStatusOK();
            }
                        
        } catch (PersistenceException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBills() {
        try {
            result = new Result();
            billManagement = new BillManagementImpl(BillDAOImpl.getInstance());
            gson = new Gson();
            
            List<Bill> bills = billManagement.listAll();

            if (bills.isEmpty()) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(bills);
            }
                        
        } catch (PersistenceException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String deleteBill(@PathParam("id") String id) {
        
        try {
            result = new Result();
            billManagement = new BillManagementImpl(BillDAOImpl.getInstance());
            gson = new Gson();
            
            boolean bool = billManagement.billRemove(id);
            
            if (bool) {
                result.setStatusOK();
            }
            else {
                result.setStatusDOESNOTEXIST();
            }
                        
        } catch (PersistenceException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }
    
}

// <editor-fold defaultstate="collapsed" desc="Bill Adapters Classes. Click on the +">

class BillOrdersAdapter{
    private String codToken;
    
    private Date datUse;

    private char idtStatus;
    
    private List<OrderItemAdapter> orders;

    public BillOrdersAdapter() {}

    public BillOrdersAdapter(String codToken, Date datUse, char idtStatus, List<OrderItemAdapter> orders) {
        this.codToken = codToken;
        this.datUse = datUse;
        this.idtStatus = idtStatus;
        this.orders = orders;
    }
    
    public BillOrdersAdapter(Bill bill, List<OrderItemAdapter> orders){
        this.codToken = bill.getCodToken();
        this.datUse = bill.getDatUse();
        this.idtStatus = bill.getIdtStatus();
        this.orders = orders;
    }


    public String getCodToken() {
        return codToken;
    }

    public void setCodToken(String codToken) {
        this.codToken = codToken;
    }

    public Date getDatUse() {
        return datUse;
    }

    public void setDatUse(Date datUse) {
        this.datUse = datUse;
    }

    public char getIdtStatus() {
        return idtStatus;
    }

    public void setIdtStatus(char idtStatus) {
        this.idtStatus = idtStatus;
    }

    public List<OrderItemAdapter> getItems() {
        return orders;
    }

    public void setItems(List<OrderItemAdapter> orders) {
        this.orders = orders;
    }
    
}

class OrderItemAdapter{
    private String codToken;
    
    private Timestamp datOrder;
    
    private Integer codItem;

    private char idtStatus;

    private double vlrPrice;

    private int qtdItem;

    private ItemImageAdapter item;

    public OrderItemAdapter() {
    }

    public OrderItemAdapter(String codToken, Timestamp datOrder, Integer codItem,
            char idtStatus, double vlrPrice, Integer qtdItem, ItemImageAdapter item) {
        this.codToken = codToken;
        this.datOrder = datOrder;
        this.codItem = codItem;
        this.idtStatus = idtStatus;
        this.vlrPrice = vlrPrice;
        this.qtdItem = qtdItem;
        this.item = item;
    }
    
    public OrderItemAdapter(Order order, ItemImageAdapter item) {
        this.codToken = order.getCodToken();
        this.datOrder = order.getDatOrder();
        this.codItem = order.getCodItem();
        this.idtStatus = order.getIdtStatus();
        this.vlrPrice = order.getVlrPrice();
        this.qtdItem = order.getQtdItem();
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

    public Integer getCodItem() {
        return codItem;
    }

    public void setCodItem(Integer codItem) {
        this.codItem = codItem;
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

    public Integer getQtdItem() {
        return qtdItem;
    }

    public void setQtdItem(Integer qtdItem) {
        this.qtdItem = qtdItem;
    }

    public ItemImageAdapter getImages() {
        return item;
    }

    public void setImages(ItemImageAdapter item) {
        this.item = item;
    }
    
    
}

class ItemImageAdapter{
    private Integer codItem;

    private Double vlrPrice;

    private String nomItem;

    private String desItem;

    private boolean isAvaliable;
    
    private List<Image> images;

    
    public ItemImageAdapter() { }
    
    public ItemImageAdapter(Integer codItem, Double vlrPrice, String nomItem, 
            String desItem, boolean isAvaliable, String seqCategory, String seqSubcategory, List<Image> images) {
        this.codItem = codItem;
        this.vlrPrice = vlrPrice;
        this.nomItem = nomItem;
        this.desItem = desItem;
        this.isAvaliable = isAvaliable;
        this.images = images;
    }
    
    public ItemImageAdapter(Item item, List<Image> images){
        this.codItem = item.getCodItem();
        this.vlrPrice = item.getVlrPrice();
        this.nomItem = item.getNomItem();
        this.desItem = item.getDesItem();
        this.isAvaliable = item.isAvaliable();
        this.images = images;
    }

    public Integer getCodItem() {
        return codItem;
    }

    public void setCodItem(Integer codItem) {
        this.codItem = codItem;
    }

    public Double getVlrPrice() {
        return vlrPrice;
    }

    public void setVlrPrice(Double vlrPrice) {
        this.vlrPrice = vlrPrice;
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}

// </editor-fold>