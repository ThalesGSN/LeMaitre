/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.servlet;

import br.cefetmg.LeMaitre.model.dao.CategoryDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Category;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.model.service.CategoryManagement;
import br.cefetmg.LeMaitre.model.service.CategoryManagementImpl;
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
 * url: http://localhost:8080/LeMaitreWebApplication/webresources/category/
 */
@Path("category")
public class CategoryResource {

    private CategoryManagement categoryManagement;
    private Gson gson;
    private Result result;

    /**
     * Creates a new instance of CategoryResource
     */
    public CategoryResource() {
    }

    /**
     * Retrieves representation of an instance of br.cefetmg.LeMaitre.servlet.CategoryResource
     * @param id
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String getCategory(@PathParam("id") String id) {
        try {
            result = new Result();
            categoryManagement = new CategoryManagementImpl(CategoryDAOImpl.getInstance());
            gson = new Gson();
            int categoryId = new Integer(id);
            
            Category category = categoryManagement.getCategoryByID(categoryId);
            
            if (category.getSeqCategory() == null) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(category);
            }
                        
        } catch (PersistenceException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCategorys() {
        try {
            result = new Result();
            categoryManagement = new CategoryManagementImpl(CategoryDAOImpl.getInstance());
            gson = new Gson();
            
            List<Category> categories = categoryManagement.listAll();
            
            if (categories.isEmpty()) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(categories);
            }
                        
        } catch (PersistenceException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String deleteCategory(@PathParam("id") String id) {
        
        try {
            result = new Result();
            categoryManagement = new CategoryManagementImpl(CategoryDAOImpl.getInstance());
            gson = new Gson();
            int categoryId = new Integer(id);
            
            boolean bool = categoryManagement.categoryRemove(categoryId);
            
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
