/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.servlet;

import br.cefetmg.LeMaitre.model.dao.BillDAOImpl;
import br.cefetmg.LeMaitre.model.dao.OrderDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Bill;
import br.cefetmg.LeMaitre.model.domain.Item;
import br.cefetmg.LeMaitre.model.domain.Order;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.model.service.BillManagement;
import br.cefetmg.LeMaitre.model.service.BillManagementImpl;
import br.cefetmg.LeMaitre.model.service.OrderManagement;
import br.cefetmg.LeMaitre.model.service.OrderManagementImpl;
import br.cefetmg.LeMaitre.util.Result;
import com.google.gson.Gson;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonWriter;
import java.io.OutputStreamWriter;
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
    private Gson gson;
    private Result result;
    private JsonWriter jWriter;
    
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
            billManagement = new BillManagementImpl(BillDAOImpl.getInstance());
            orderManagement = new OrderManagementImpl(OrderDAOImpl.getInstance());
            
            gson = new Gson();
            
            Bill bill = billManagement.getBillByID(id);
            
            if (bill.getCodToken() == null) {
                result.setStatusDOESNOTEXIST();

            }
            else {
                BillOrdersAdapter billItemsAdapter;
                billItemsAdapter = new BillOrdersAdapter(bill, orderManagement.getOrdersByToken(id));
                result.setContent(billItemsAdapter);
                
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


class BillOrdersAdapter{
    private String codToken;
    
    private Date datUse;

    private char idtStatus;
    
    private List<Order> orders;

    public BillOrdersAdapter() {}

    public BillOrdersAdapter(String codToken, Date datUse, char idtStatus, List<Order> orders) {
        this.codToken = codToken;
        this.datUse = datUse;
        this.idtStatus = idtStatus;
        this.orders = orders;
    }
    
    public BillOrdersAdapter(Bill bill, List<Order> orders){
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

    public List<Order> getItems() {
        return orders;
    }

    public void setItems(List<Order> orders) {
        this.orders = orders;
    }
    
    
}