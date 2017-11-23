/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.servlet;

import br.cefetmg.LeMaitre.model.dao.EmployeeDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Employee;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.model.service.EmployeeManagement;
import br.cefetmg.LeMaitre.model.service.EmployeeManagementImpl;
import br.cefetmg.LeMaitre.util.Result;
import com.google.gson.Gson;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author thales
 * 
 * url: http://localhost:8080/LeMaitreWebApplication/webresources/employee/
 */
@Path("employee")
public class EmployeeResource {
    
    private EmployeeManagement employeeManagement;
    private Gson gson;
    private Result result;

    public EmployeeResource() {
    }
    
     /**
     * Retrieves representation of an instance of br.cefetmg.LeMaitre.servlet.EmployeeResource
     * @param id
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String getEmployee(@PathParam("id") String id) {
        try {
            result = new Result();
            employeeManagement = new EmployeeManagementImpl(EmployeeDAOImpl.getInstance());
            gson = new Gson();
            int employeeId = Integer.parseInt(id);
            
            Employee employee = employeeManagement.getEmployeeByID(employeeId);
            
            if (employee.getCodID() == null) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(employee);
            }
                        
        } catch (PersistenceException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }
    
    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployees() {
        try {
            result = new Result();
            employeeManagement = new EmployeeManagementImpl(EmployeeDAOImpl.getInstance());
            gson = new Gson();
            
            //List<Employee> categories = employeeManagement.getAll();
            
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
    }*/
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String deleteEmployee(@PathParam("id") String id) {
        
        try {
            result = new Result();
            employeeManagement = new EmployeeManagementImpl(EmployeeDAOImpl.getInstance());
            gson = new Gson();
            int employeeId = new Integer(id);
            
            if(employeeManagement.employeeRemove(employeeId)) {
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
