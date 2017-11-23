/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.servlet;

import br.cefetmg.LeMaitre.model.dao.ReservationDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Reservation;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.model.service.ReservationManagement;
import br.cefetmg.LeMaitre.model.service.ReservationManagementImpl;
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
 * @author Paula Ribeiro
 * url: http://localhost:8080/LeMaitreWebApplication/webresources/reservation/
 */
@Path("reservation")
public class ReservationResource {

    private ReservationManagement reservationManagement;
    private Gson gson;
    private Result result;

    /**
     * Creates a new instance of ReservationResource
     */
    public ReservationResource() {
    }

    /**
     * Retrieves representation of an instance of br.cefetmg.LeMaitre.servlet.ReservationResource
     * @param id
     * @param dat
     * @param hour
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/{dat}/{hour}")
    public String getReservation(@PathParam("id") String id, @PathParam("dat") String dat, @PathParam("hour") String hour) {
        try {
            result = new Result();
            reservationManagement = new ReservationManagementImpl(ReservationDAOImpl.getInstance());
            gson = new Gson();
                        
            int tableId = new Integer(id);
            
            Time hourReservation = Time.valueOf(hour);
            Date datReservation = this.getDate(dat);
            
            Reservation reservation = reservationManagement.getReservationByID(tableId, datReservation, hourReservation);
            
            if (reservation.getCodIDTable() == null) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(reservation);
            }
                        
        } catch (PersistenceException | ParseException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getReservations() {
        try {
            result = new Result();
            reservationManagement = new ReservationManagementImpl(ReservationDAOImpl.getInstance());
            gson = new Gson();
            
            List<Reservation> reservations = reservationManagement.listAllWithinSevenDays();
            
            if (reservations.isEmpty()) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(reservations);
            }
                        
        } catch (PersistenceException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("date/{dat}")
    public String getReservationsByDate(@PathParam("dat") String dat) {
        try {
            result = new Result();
            reservationManagement = new ReservationManagementImpl(ReservationDAOImpl.getInstance());
            gson = new Gson();
            
            Date datReservation = this.getDate(dat);
            List<Reservation> reservations = reservationManagement.getReservationsByDate(datReservation);
            
            if (reservations.isEmpty()) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(reservations);
            }
                        
        } catch (PersistenceException | ParseException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String getReservationsByTableIdWithinSevenDays(@PathParam("id") String id) {
        try {
            result = new Result();
            reservationManagement = new ReservationManagementImpl(ReservationDAOImpl.getInstance());
            gson = new Gson();
            
            int tableId = new Integer(id);
            
            List<Reservation> reservations = reservationManagement.listReservationByTableIDWithinSevenDays(tableId);
            
            if (reservations.isEmpty()) {
                result.setStatusDOESNOTEXIST();
            }
            else {
                result.setStatusOK();
                result.setContent(reservations);
            }
                        
        } catch (PersistenceException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/{dat}/{hour}")
    public String deleteReservation(@PathParam("id") String id, @PathParam("dat") String dat, @PathParam("hour") String hour) {
        
        try {
            result = new Result();
            reservationManagement = new ReservationManagementImpl(ReservationDAOImpl.getInstance());
            gson = new Gson();
            
            int tableId = new Integer(id);
            Date datReservation = this.getDate(dat);
            Time hourReservation = Time.valueOf(hour);
            
            boolean bool = reservationManagement.reservationRemove(tableId, datReservation, hourReservation);
            
            if (bool) {
                result.setStatusOK();
            }
            else {
                result.setStatusDOESNOTEXIST();
            }
                        
        } catch (PersistenceException | ParseException ex) {
            result.setStatusBADREQUEST();
            result.setContent(ex);
        }
        
        return gson.toJson(result);
    }
    
    public Date getDate(String dat) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date = format.parse(dat);
                    
        return date;
    }
}
