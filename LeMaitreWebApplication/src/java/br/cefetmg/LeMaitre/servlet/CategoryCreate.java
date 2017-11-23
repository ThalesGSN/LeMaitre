/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.servlet;

import br.cefetmg.LeMaitre.model.dao.CategoryDAO;
import br.cefetmg.LeMaitre.model.dao.CategoryDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Category;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.model.service.CategoryManagement;
import br.cefetmg.LeMaitre.model.service.CategoryManagementImpl;
import br.cefetmg.LeMaitre.util.Result;
import br.cefetmg.LeMaitre.util.ServletUtil;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Paula Ribeiro
 * url: http://localhost:8080/LeMaitreWebApplication/CategoryCreate
 */
public class CategoryCreate extends HttpServlet {
    private CategoryManagement categoryManagement;
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
            Category category = this.categoryFromJson(payload);
            
            CategoryDAO categoryDAO = CategoryDAOImpl.getInstance();
            categoryManagement = new CategoryManagementImpl(categoryDAO);
            
            int id = categoryManagement.categoryInsert(category);
            category.setSeqCategory(id);
            
            result.setStatusOK();
            result.setContent(category);
            
        } catch (BusinessException | PersistenceException ex) {
            result.setContent(ex.getMessage());
            result.setStatusBADREQUEST();
        }
        
        finally {
            PrintWriter writer = response.getWriter();
            writer.println(gson.toJson(result));
        }
    }
    
    private Category categoryFromJson(String str) {
        gson = new Gson();
        Category category = gson.fromJson(str, Category.class);
        return category;
    }
}
