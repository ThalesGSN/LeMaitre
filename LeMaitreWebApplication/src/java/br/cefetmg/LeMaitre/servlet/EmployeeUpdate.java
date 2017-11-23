/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.servlet;

import br.cefetmg.LeMaitre.model.dao.EmployeeDAO;
import br.cefetmg.LeMaitre.model.dao.EmployeeDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Employee;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.model.service.EmployeeManagement;
import br.cefetmg.LeMaitre.model.service.EmployeeManagementImpl;
import br.cefetmg.LeMaitre.util.Result;
import br.cefetmg.LeMaitre.util.ServletUtil;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thalesgsn
 */
@WebServlet(name = "EmployeeUpdate", urlPatterns = {"/EmployeeUpdate"})
public class EmployeeUpdate extends HttpServlet {
    private EmployeeManagement employeeManagement;
    private Result result;
    private ServletUtil util;
    private Gson gson;
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        result = new Result();
        util = new ServletUtil();
        gson = new Gson();
        
        try {
            
            String payload = util.getJson(request);
            Employee employee = this.employeeFromJson(payload);
            
            EmployeeDAO employeeDAO = EmployeeDAOImpl.getInstance();
            employeeManagement = new EmployeeManagementImpl(employeeDAO);
            
            boolean flag = employeeManagement.employeeUpdate(employee);
            
            if (flag) {
                result.setStatusOK();
                result.setContent(employee);
            }
            
        } catch (BusinessException | PersistenceException ex) {
            result.setContent(ex.getMessage());
            result.setStatusBADREQUEST();
        }
        
        finally {
            PrintWriter writer = response.getWriter();
            writer.println(gson.toJson(result));
        }
    }
    
    private Employee employeeFromJson(String str) {
        gson = new Gson();
        Employee employee = gson.fromJson(str, Employee.class);
        return employee;
    }
}