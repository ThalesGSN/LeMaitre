/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.servlet;

import br.cefetmg.LeMaitre.model.dao.ReservationDAO;
import br.cefetmg.LeMaitre.model.dao.ReservationDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Reservation;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.model.service.ReservationManagement;
import br.cefetmg.LeMaitre.model.service.ReservationManagementImpl;
import br.cefetmg.LeMaitre.util.Result;
import br.cefetmg.LeMaitre.util.ServletUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Paula Ribeiro
 * url: http://localhost:8080/LeMaitreWebApplication/ReservationCreate
 */
public class ReservationCreate extends HttpServlet {

    private ReservationManagement reservationManagement;
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
            Reservation reservation = this.reservationFromJson(payload);
            
            ReservationDAO reservationDAO = ReservationDAOImpl.getInstance();
            reservationManagement = new ReservationManagementImpl(reservationDAO);
            
            reservationManagement.reservationInsert(reservation);
            
            result.setStatusOK();
            result.setContent(reservation);
            
        } catch (BusinessException | PersistenceException ex) {
            result.setContent(ex.getMessage());
            result.setStatusBADREQUEST();
        }
        
        finally {
            PrintWriter writer = response.getWriter();
            writer.println(gson.toJson(result));
        }
    }
    
    private Reservation reservationFromJson(String str) {
        SqlDateTypeAdapter sqlAdapter = new SqlDateTypeAdapter();
        gson = new GsonBuilder()
           .registerTypeAdapter(java.sql.Date.class, sqlAdapter)
           .setDateFormat("yyyy-MM-dd")
           .create();
        Reservation reservation = gson.fromJson(str, Reservation.class);
        return reservation;
    }
    
}