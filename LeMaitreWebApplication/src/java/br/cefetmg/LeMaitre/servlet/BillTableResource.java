/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.servlet;

import br.cefetmg.LeMaitre.model.dao.BillTableDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Image;
import br.cefetmg.LeMaitre.model.domain.BillTable;
import br.cefetmg.LeMaitre.model.domain.Table;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.model.service.BillTableManagement;
import br.cefetmg.LeMaitre.model.service.BillTableManagementImpl;
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
 * url: http://localhost:8080/LeMaitreWebApplication/webresources/billTable/
 */
@Path("billTable")
public class BillTableResource {

    private BillTableManagement billTableManagement;
    private Gson gson;
    private Result result;

    /**
     * Creates a new instance of BillTableResource
     */
    public BillTableResource() {
    }

    /**
     * Retrieves representation of an instance of br.cefetmg.LeMaitre.servlet.BillTableResource
     * @param billID
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{billID}")
    public String getTablesByToken(@PathParam("billID") String billID) {
        try {
            result = new Result();
            billTableManagement = new BillTableManagementImpl(BillTableDAOImpl.getInstance());
            gson = new Gson();            
            
            List<Table> tables = billTableManagement.getTablesByToken(billID);
            
            if (tables == null) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(tables);
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
    public String getBillTables() {
        try {
            result = new Result();
            billTableManagement = new BillTableManagementImpl(BillTableDAOImpl.getInstance());
            gson = new Gson();
            
            List<BillTable> billTables = billTableManagement.();
            
            if (billTables.isEmpty()) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(billTables);
            }
                        
        } catch (PersistenceException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }*/
    
    
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{billID}/{tableID}")
    public String deleteBillTable(@PathParam("billID") String billID, @PathParam("tableID") String tableID) {
        try {
            result = new Result();
            billTableManagement = new BillTableManagementImpl(BillTableDAOImpl.getInstance());
            gson = new Gson();
                         
            int codTable = Integer.parseInt(tableID);
            
            
            
            if (billTableManagement.billTableRemove(billID, codTable)) {
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
