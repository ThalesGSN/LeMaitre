/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.servlet;

import br.cefetmg.LeMaitre.model.dao.ImageDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Image;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.model.service.ImageManagement;
import br.cefetmg.LeMaitre.model.service.ImageManagementImpl;
import br.cefetmg.LeMaitre.util.Result;
import com.google.gson.Gson;
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
 * url: http://localhost:8080/LeMaitreWebApplication/webresources/image/
 */
@Path("image")
public class ImageResource {

    private ImageManagement imageManagement;
    private Gson gson;
    private Result result;

    /**
     * Creates a new instance of ImageResource
     */
    public ImageResource() {
    }

    /**
     * Retrieves representation of an instance of br.cefetmg.LeMaitre.servlet.ImageResource
     * @param id
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String getImage(@PathParam("id") String id) {
        try {
            result = new Result();
            imageManagement = new ImageManagementImpl(ImageDAOImpl.getInstance());
            gson = new Gson();
            long imageId = new Integer(id);
            
            Image image = imageManagement.getImageByID(imageId);
            
            if (image.getCodImage()== null) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(image);
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
    public String deleteImage(@PathParam("id") String id) {
        
        try {
            result = new Result();
            imageManagement = new ImageManagementImpl(ImageDAOImpl.getInstance());
            gson = new Gson();
            
            long imageId = Integer.parseInt(id);
            
            boolean bool = imageManagement.imageRemove(imageId);
            
            if (bool) {
                result.setStatusOK();
            }
            else {
                result.setStatusDOESNOTEXIST();
            }
                        
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }
}
