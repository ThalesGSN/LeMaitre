/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.servlet;

import br.cefetmg.LeMaitre.model.dao.TableDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Table;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.model.service.TableManagement;
import br.cefetmg.LeMaitre.model.service.TableManagementImpl;
import br.cefetmg.LeMaitre.util.Result;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Paula Ribeiro
 */
@Path("table")
public class TableResource {

    private TableManagement tableManagement;
    private Gson gson;
    private Result result;
    
    /**
     * Creates a new instance of TableResource
     */
    public TableResource() {}
   
    /**
     * Retrieves representation of an instance of br.cefetmg.LeMaitre.servlet.TableResource
     * @param id
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get/{id}")
    public String getTable(@PathParam("id") String id) {
        try {
            result = new Result();
            tableManagement = new TableManagementImpl(TableDAOImpl.getInstance());
            gson = new Gson();
            long tableId = new Long(id);
            
            Table table = tableManagement.getTableByID(tableId);
            
            if (table.getCodID() == null) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(table);
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
    public String getTables() {
        try {
            result = new Result();
            tableManagement = new TableManagementImpl(TableDAOImpl.getInstance());
            gson = new Gson();
            
            List<Table> tables = tableManagement.listAll();
            
            if (tables.isEmpty()) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(tables);
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
    public String deleteTable(@PathParam("id") String id) {
        
        try {
            result = new Result();
            tableManagement = new TableManagementImpl(TableDAOImpl.getInstance());
            gson = new Gson();
            long tableId = new Long(id);
            
            boolean bool = tableManagement.tableRemove(tableId);
            
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
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("create")
    public String createTable(JsonObject tableJson) {
        Table table = new Table();
        table.setIdtStatus(tableJson.get("idtStatus").getAsCharacter());
        table.setNroSeat(tableJson.get("nroSeat").getAsInt());
        
        Long id = null;
        
        try {
            id = tableManagement.tableInsert(table);
            if (id == null) {
                result.setStatusBADREQUEST();
            }
            else {
                result.setStatusOK();
            }
        } catch (BusinessException | PersistenceException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }     
        
        return gson.toJson(result);
    }
}
