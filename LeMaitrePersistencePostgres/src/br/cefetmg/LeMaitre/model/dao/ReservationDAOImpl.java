/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Reservation;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.util.db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public class ReservationDAOImpl implements ReservationDAO {
    private static ReservationDAOImpl reservationDAO = null;

    public static ReservationDAOImpl getInstance(){
        if(reservationDAO == null){
            reservationDAO = new ReservationDAOImpl();
        }
        return reservationDAO;
    }
    
    private ReservationDAOImpl() { }
    
    
    @Override
    synchronized public void insert(Reservation reservation) throws PersistenceException {
        if (reservation == null) {
            throw new PersistenceException(PersistenceException.INSERT_OBJECT_ISNULL, "Reservation cannot be null");
        }
                
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
                       
            String sql = "INSERT INTO Reservation "
                    + "(COD_id_table, DAT_reservation, DAT_hour_reservation, "
                    + "NRO_persons, TXT_contact_name, TXT_telephone, TXT_cellphone) "
                    + "    VALUES (?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, reservation.getCodIDTable());
            pstmt.setDate(2, (java.sql.Date) this.getDateUtilToSql(reservation.getDatReservation()));
            pstmt.setTime(3, reservation.getDatHourReservation());
            pstmt.setInt(4, reservation.getNroPersons());
            pstmt.setString(5, reservation.getTxtContactName());
            pstmt.setString(6, reservation.getTxtTelephone());
            pstmt.setString(7, reservation.getTxtCellphone());
            
            ResultSet rs = pstmt.executeQuery();

            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            if(ex.getErrorCode() == 1062)
                throw new PersistenceException(PersistenceException.DUPLICATED_KEY, "Duplicated Key");
        }
        
    }

    @Override
    synchronized public boolean update(Reservation reservation) throws PersistenceException {
        if (reservation == null) {
            throw new PersistenceException(PersistenceException.UPDATE_OBJECT_ISNULL, "reservation cannot be null");
        }
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE Reservation "
                    + "     SET NRO_persons  = ?,"
                    + "     TXT_contact_name = ?,"
                    + "     TXT_telephone    = ?,"
                    + "     TXT_cellphone    = ? "
                    + "WHERE COD_id_table = ? AND DAT_reservation = ? AND DAT_hour_reservation = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, reservation.getNroPersons());
            pstmt.setString(2, reservation.getTxtContactName());
            pstmt.setString(3, reservation.getTxtTelephone());
            pstmt.setString(4, reservation.getTxtCellphone());
            pstmt.setInt(5, reservation.getCodIDTable());
            pstmt.setDate(6, (java.sql.Date) this.getDateUtilToSql(reservation.getDatReservation()));
            pstmt.setTime(7, reservation.getDatHourReservation());
            int changedRows = pstmt.executeUpdate();
            
            pstmt.close();
            connection.close();
            
            if(changedRows == 1){
                return true;
            } 
            throw new PersistenceException(PersistenceException.NOT_A_UPDATE, "Something went wrong when update.");
        
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }     
    }

    @Override
    synchronized public boolean remove(Integer tableID, Date datReservation, Time hourReservation) throws PersistenceException {
        if(tableID == null || datReservation == null || hourReservation == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM Reservation WHERE COD_id_table = ? AND DAT_reservation = ? AND DAT_HOUR_reservation = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, tableID);
            pstmt.setDate(2,(java.sql.Date) this.getDateUtilToSql(datReservation));
            pstmt.setTime(3, hourReservation);
            
            int removedRows = pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            if(removedRows == 1){
                return true;
            } 
            throw new PersistenceException(PersistenceException.NOT_A_DELETE, "Something went wrong when deleting.");

        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }   
    }

    @Override
    synchronized public Reservation getReservationByID(Integer tableID, Date datReservation, Time hourReservation) throws PersistenceException {
        if(tableID == null || datReservation == null || hourReservation == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM reservation "
                    + "WHERE COD_id_table = ? AND DAT_reservation = ? AND DAT_hour_reservation = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, tableID);
            pstmt.setDate(2, (java.sql.Date) this.getDateUtilToSql(datReservation));
            pstmt.setTime(3, hourReservation);
            ResultSet rs = pstmt.executeQuery();

            Reservation reservation = new Reservation();
            
            if (rs.next()) {
                reservation.setCodIDTable(tableID);
                reservation.setDatReservation(datReservation);
                reservation.setDatHourReservation(hourReservation);
                reservation.setNroPersons(rs.getInt("NRO_persons"));
                reservation.setTxtContactName(rs.getString("TXT_contact_name"));
                reservation.setTxtTelephone(rs.getString("TXT_telephone"));
                reservation.setTxtCellphone(rs.getString("TXT_cellphone"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return reservation;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }  
    }
    
    @Override
    synchronized public List<Reservation> listReservationByTableID(Integer tableID) throws PersistenceException {
        if(tableID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        ArrayList<Reservation> reservations = new ArrayList();
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM Reservation WHERE COD_id_table = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setInt(1, tableID);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                Reservation reservation = new Reservation();
                
                reservation.setCodIDTable(rs.getInt("COD_id_table"));
                reservation.setDatReservation( this.getDateUtilToSql(rs.getDate("DAT_reservation")));
                reservation.setDatHourReservation(rs.getTime("DAT_hour_reservation"));
                reservation.setNroPersons(rs.getInt("NRO_persons"));
                reservation.setTxtContactName(rs.getString("TXT_contact_name"));
                reservation.setTxtTelephone(rs.getString("TXT_telephone"));
                reservation.setTxtCellphone(rs.getString("TXT_cellphone"));
                
                reservations.add(reservation);
            }

            rs.close();
            pstmt.close();
            connection.close();

            return reservations;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }
    }
    
    @Override
    synchronized public List<Reservation> listReservationByTableIDWithinSevenDays(Integer tableID) throws PersistenceException {
        if(tableID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        ArrayList<Reservation> reservations = new ArrayList();
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            
            String sql = "SELECT * FROM Reservation WHERE COD_id_table = ? AND DAT_reservation - CURRENT_DATE < 8 AND DAT_reservation - CURRENT_DATE > -1;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, tableID);
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                Reservation reservation = new Reservation();
                
                reservation.setCodIDTable(rs.getInt("COD_id_table"));
                reservation.setDatReservation(this.getDateUtilToSql(rs.getDate("DAT_reservation")));
                reservation.setDatHourReservation(rs.getTime("DAT_hour_reservation"));
                reservation.setNroPersons(rs.getInt("NRO_persons"));
                reservation.setTxtContactName(rs.getString("TXT_contact_name"));
                reservation.setTxtTelephone(rs.getString("TXT_telephone"));
                reservation.setTxtCellphone(rs.getString("TXT_cellphone"));
                               
                reservations.add(reservation);
            }

            rs.close();
            pstmt.close();
            connection.close();

            return reservations;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }
    }

    @Override
    synchronized public List<Reservation> listAll() throws PersistenceException {
        ArrayList<Reservation> reservations = new ArrayList();
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM Reservation;";
            
            ResultSet rs = stmt.executeQuery(sql);
            Reservation reservation;
            
            while(rs.next()){
                reservation = new Reservation();
                
                reservation.setCodIDTable(rs.getInt("COD_id_table"));
                reservation.setDatReservation(this.getDateUtilToSql(rs.getDate("DAT_reservation")));
                reservation.setDatHourReservation(rs.getTime("DAT_hour_reservation"));
                reservation.setNroPersons(rs.getInt("NRO_persons"));
                reservation.setTxtContactName(rs.getString("TXT_contact_name"));
                reservation.setTxtTelephone(rs.getString("TXT_telephone"));
                reservation.setTxtCellphone(rs.getString("TXT_cellphone"));
                
                reservations.add(reservation);
            }

            rs.close();
            stmt.close();
            connection.close();

            return reservations;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }
    }
    
    @Override
    synchronized public List<Reservation> listAllWithinSevenDays() throws PersistenceException {
        ArrayList<Reservation> reservations = new ArrayList();
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM Reservation WHERE DAT_reservation - CURRENT_DATE < 8 AND DAT_reservation - CURRENT_DATE > -1;";
            
            ResultSet rs = stmt.executeQuery(sql);
            Reservation reservation;
            
            while(rs.next()){
                reservation = new Reservation();
                
                reservation.setCodIDTable(rs.getInt("COD_id_table"));
                reservation.setDatReservation(this.getDateUtilToSql(rs.getDate("DAT_reservation")));
                reservation.setDatHourReservation(rs.getTime("DAT_hour_reservation"));
                reservation.setNroPersons(rs.getInt("NRO_persons"));
                reservation.setTxtContactName(rs.getString("TXT_contact_name"));
                reservation.setTxtTelephone(rs.getString("TXT_telephone"));
                reservation.setTxtCellphone(rs.getString("TXT_cellphone"));
                
                reservations.add(reservation);
            }

            rs.close();
            stmt.close();
            connection.close();

            return reservations;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }
    }
    
    @Override
    synchronized public List<Reservation> getReservationsByDate(Date datReservation) throws PersistenceException {
        if(datReservation == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        ArrayList<Reservation> reservations = new ArrayList();
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM reservation "
                    + "WHERE DAT_reservation = ? ;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setDate(1, (java.sql.Date) this.getDateUtilToSql(datReservation));
            ResultSet rs = pstmt.executeQuery();

            Reservation reservation = new Reservation();
            
            while(rs.next()){
                reservation = new Reservation();
                
                reservation.setCodIDTable(rs.getInt("COD_id_table"));
                reservation.setDatReservation(datReservation);
                reservation.setDatHourReservation(rs.getTime("DAT_hour_reservation"));
                reservation.setNroPersons(rs.getInt("NRO_persons"));
                reservation.setTxtContactName(rs.getString("TXT_contact_name"));
                reservation.setTxtTelephone(rs.getString("TXT_telephone"));
                reservation.setTxtCellphone(rs.getString("TXT_cellphone"));
                
                reservations.add(reservation);
            }

            rs.close();
            pstmt.close();
            connection.close();

            return reservations;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }  
    }
    
    private java.sql.Date getDateUtilToSql (Date date) {
        java.sql.Date dat = new java.sql.Date(date.getYear(), date.getMonth(), date.getDate());
        return dat;
    }
    
}
