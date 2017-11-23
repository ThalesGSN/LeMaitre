/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.servlet;

import br.cefetmg.LeMaitre.model.dao.ItemDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Item;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.model.service.ItemManagement;
import br.cefetmg.LeMaitre.model.service.ItemManagementImpl;
import br.cefetmg.LeMaitre.util.Result;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Paula Ribeiro
 * url: http://localhost:8080/LeMaitreWebApplication/webresources/table/
 */
@Path("item")
public class ItemResource {

    private ItemManagement itemManagement;
    private Gson gson;
    private Result result;

    /**
     * Creates a new instance of ItemResource
     */
    public ItemResource() {
    }

    /**
     * Retrieves representation of an instance of br.cefetmg.LeMaitre.servlet.ItemResource
     * @param id
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get/{id}")
    public String getItem(@PathParam("id") String id) {
        try {
            result = new Result();
            itemManagement = new ItemManagementImpl(ItemDAOImpl.getInstance());
            gson = new Gson();
            int itemId = new Integer(id);
            
            Item item = itemManagement.getItemByID(itemId);
            
            if (item.getCodItem() == null) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(item);
            }
                        
        } catch (PersistenceException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get")
    public String getItems() {
        try {
            result = new Result();
            itemManagement = new ItemManagementImpl(ItemDAOImpl.getInstance());
            gson = new Gson();
            
            List<Item> items = itemManagement.getAllItems();
            
            if (items.isEmpty()) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(items);
            }
                        
        } catch (PersistenceException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("delete/{id}")
    public String deleteItem(@PathParam("id") String id) {
        
        try {
            result = new Result();
            itemManagement = new ItemManagementImpl(ItemDAOImpl.getInstance());
            gson = new Gson();
            int itemId = new Integer(id);
            
            boolean bool = itemManagement.itemRemove(itemId);
            
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
