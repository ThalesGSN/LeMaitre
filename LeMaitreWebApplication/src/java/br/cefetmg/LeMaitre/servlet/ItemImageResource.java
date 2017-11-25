/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.servlet;

import br.cefetmg.LeMaitre.model.dao.ItemImageDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Image;
import br.cefetmg.LeMaitre.model.domain.ItemImage;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.model.service.ItemImageManagement;
import br.cefetmg.LeMaitre.model.service.ItemImageManagementImpl;
import br.cefetmg.LeMaitre.util.Result;
import com.google.gson.Gson;
import java.util.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Thalesgsn
 * url: http://localhost:8080/LeMaitreWebApplication/webresources/itemImage/
 */
@Path("itemImage")
public class ItemImageResource {

    private ItemImageManagement itemImageManagement;
    private Gson gson;
    private Result result;

    /**
     * Creates a new instance of ItemImageResource
     */
    public ItemImageResource() {
    }

    /**
     * Retrieves representation of an instance of br.cefetmg.LeMaitre.servlet.ItemImageResource
     * @param itemID
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{itemID}")
    public String getImagesbyItem(@PathParam("itemID") String itemID) {
        try {
            result = new Result();
            itemImageManagement = new ItemImageManagementImpl(ItemImageDAOImpl.getInstance());
            gson = new Gson();
                        
            int codItem = Integer.parseInt(itemID);
            
            
            List<Image> images = itemImageManagement.getImagesByItemID(codItem);
            
            if (images == null) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(images);
            }
                        
        } catch (PersistenceException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
            ex.printStackTrace();
        }
        
        return gson.toJson(result);
    }
    
    /* TO check
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getItemImages() {
        try {
            result = new Result();
            itemImageManagement = new ItemImageManagementImpl(ItemImageDAOImpl.getInstance());
            gson = new Gson();
            
            List<ItemImage> itemImages = itemImageManagement.();
            
            if (itemImages.isEmpty()) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(itemImages);
            }
                        
        } catch (PersistenceException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }*/
    
    
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{itemID}/{imageID}")
    public String deleteItemImage(@PathParam("itemID") String itemID, @PathParam("imageID") String imageID) {
        try {
            result = new Result();
            itemImageManagement = new ItemImageManagementImpl(ItemImageDAOImpl.getInstance());
            gson = new Gson();
                         
            int codItem = Integer.parseInt(itemID);
            long codImage = Integer.parseInt(imageID);
            
            
            
            if (itemImageManagement.itemImageRemove(codItem, codImage)) {
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
