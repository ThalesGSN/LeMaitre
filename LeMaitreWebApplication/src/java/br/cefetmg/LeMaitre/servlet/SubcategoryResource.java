/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.servlet;

import br.cefetmg.LeMaitre.model.dao.SubcategoryDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Subcategory;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.model.service.SubcategoryManagement;
import br.cefetmg.LeMaitre.model.service.SubcategoryManagementImpl;
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
 * url: http://localhost:8080/LeMaitreWebApplication/webresources/subcategory/
 */
@Path("subcategory")
public class SubcategoryResource {

    private SubcategoryManagement subcategoryManagement;
    private Gson gson;
    private Result result;

    /**
     * Creates a new instance of SubcategoryResource
     */
    public SubcategoryResource() {
    }

    /**
     * Retrieves representation of an instance of br.cefetmg.LeMaitre.servlet.SubcategoryResource
     * @param categoryID
     * @param subcategoryID
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{categoryID}/{subcategoryID}")
    public String getSubcategory(@PathParam("categoryID") String categoryID, @PathParam("subcategoryID") String subcategoryID) {
        try {
            result = new Result();
            subcategoryManagement = new SubcategoryManagementImpl(SubcategoryDAOImpl.getInstance());
            gson = new Gson();
                        
            int catID = Integer.parseInt(categoryID);
            int subcatID = Integer.parseInt(subcategoryID);
            
            
            Subcategory subcategory = subcategoryManagement.getSubcategoryByID(catID, subcatID);
            
            if (subcategory.getSeqCategory() == null) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(subcategory);
            }
                        
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }
    
    /* TO check
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSubcategorys() {
        try {
            result = new Result();
            subcategoryManagement = new SubcategoryManagementImpl(SubcategoryDAOImpl.getInstance());
            gson = new Gson();
            
            List<Subcategory> subcategorys = subcategoryManagement.();
            
            if (subcategorys.isEmpty()) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(subcategorys);
            }
                        
        } catch (PersistenceException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }*/
    
    
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{categoryID}/{subcategoryID}")
    public String deleteSubcategory(@PathParam("categoryID") String categoryID, @PathParam("subcategoryID") String subcategoryID) {
        
        try {
            result = new Result();
            subcategoryManagement = new SubcategoryManagementImpl(SubcategoryDAOImpl.getInstance());
            gson = new Gson();
                         
            int catID = Integer.parseInt(categoryID);
            int subcatID = Integer.parseInt(subcategoryID);
            
            
            
            if (subcategoryManagement.subcategoryRemove(catID, subcatID)) {
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
