/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.servlet;

import br.cefetmg.LeMaitre.model.dao.BillDAOImpl;
import br.cefetmg.LeMaitre.model.dao.OrderDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Bill;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.model.service.BillManagement;
import br.cefetmg.LeMaitre.model.service.BillManagementImpl;
import br.cefetmg.LeMaitre.model.service.OrderManagement;
import br.cefetmg.LeMaitre.model.service.OrderManagementImpl;
import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Paula Ribeiro
 */
@Path("bill")
public class BillResource {
    
    private BillManagement billManagement;
    private OrderManagement orderManagement;
    private Bill bill;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of BillResource
     */
    public BillResource() {
    }

    /**
     * Retrieves representation of an instance of br.cefetmg.LeMaitre.servlet.BillResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get")
    public String getBill() throws PersistenceException {
        
        billManagement = new BillManagementImpl(BillDAOImpl.getInstance());
        Long billId = 1L;//new Long(id);
        
        orderManagement = new OrderManagementImpl(OrderDAOImpl.getInstance());
        
        
        try {
            bill = billManagement.getBillByID(billId);
            //bill.setListOrder(orderManagement.getOrdersByBillID(billId));
        } catch (PersistenceException ex) {
            Logger.getLogger(BillResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Gson gson = new Gson();
                      
        return gson.toJson(bill) + " - " + gson.toJson(orderManagement.getOrdersByBillID(bill.getCodID()));
    }

    /**
     * PUT method for updating or creating an instance of BillResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
