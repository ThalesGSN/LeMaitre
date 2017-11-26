/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.servlet;

import br.cefetmg.LeMaitre.model.dao.BillDAO;
import br.cefetmg.LeMaitre.model.dao.BillDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Bill;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.model.service.BillManagement;
import br.cefetmg.LeMaitre.model.service.BillManagementImpl;
import br.cefetmg.LeMaitre.util.Result;
import br.cefetmg.LeMaitre.util.ServletUtil;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.MailDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Thalesgsn
 * url: http://localhost:8080/LeMaitreWebApplication/BillCreate
 */
@WebServlet(name = "BillCreate", urlPatterns = {"/BillCreate"})
public class BillCreate extends HttpServlet {

    private BillManagement billManagement;
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
            
            billManagement = new BillManagementImpl(BillDAOImpl.getInstance());
            
            result.setStatusOK();
            result.setContent(billManagement.billCreate());
            
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            result.setContent(ex.getMessage());
            result.setStatusBADREQUEST();
        } finally {
            PrintWriter writer = response.getWriter();
            writer.println(gson.toJson(result));
        }
    }
    
    private Bill billFromJson(String str) throws ParseException {
        gson = new Gson();
        BillCreateAdapter billAdapter = gson.fromJson(str, BillCreateAdapter.class);
        Bill bill = billAdapter.getBill();
        return bill;
    }

}

 class BillCreateAdapter {
    private String codToken;
    
    private String datUse;

    private char idtStatus;

    public BillCreateAdapter() {
    }

    
    public BillCreateAdapter(String codToken, String datUse, char idtStatus) {
        this.codToken = codToken;
        this.datUse = datUse;
        this.idtStatus = idtStatus;
    }

    public String getCodToken() {
        return codToken;
    }

    public void setCodToken(String codToken) {
        this.codToken = codToken;
    }

    public Bill getBill() throws ParseException{
        return new Bill(codToken, new Date(Date.parse(datUse)), idtStatus);
    }
    public String getDatUse() {
        return datUse;
    }

    public void setDatUse(String datUse) {
        this.datUse = datUse;
    }

    public char getIdtStatus() {
        return idtStatus;
    }

    public void setIdtStatus(char idtStatus) {
        this.idtStatus = idtStatus;
    }
    
    
}
