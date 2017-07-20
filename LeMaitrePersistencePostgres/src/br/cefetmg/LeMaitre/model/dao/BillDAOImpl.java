/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Bill;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.util.db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Thalesgsn
 */
public class BillDAOImpl implements BillDAO {
    private static BillDAOImpl billDAO = null;

    public static BillDAOImpl getInstance(){
        if(billDAO == null){
            billDAO = new BillDAOImpl();
        }
        return billDAO;
    }
    
    private BillDAOImpl() { }
    
    
    @Override
    synchronized public Long insert(Bill bill) throws PersistenceException {
        if (bill == null) {
            throw new PersistenceException(PersistenceException.INSERT_OBJECT_ISNULL, "bill cannot be null");
        }
        
        Long idBill = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO Bill "
                    + "(DAT_use, IDT_status) "
                    + "    VALUES (?, ?) returning COD_ID;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setDate(1,(java.sql.Date) bill.getDatUse());
            pstmt.setString(2, String.valueOf(bill.getIdtStatus()));
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                idBill = rs.getLong("COD_ID");
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            if(ex.getErrorCode() == 1062)
                throw new PersistenceException(PersistenceException.DUPLICATED_KEY, "Duplicated Key");
        }

        return idBill;
    }

    @Override
    synchronized public boolean update(Bill bill) throws PersistenceException {
        try {
            if (bill == null) {
                throw new PersistenceException(PersistenceException.UPDATE_OBJECT_ISNULL, "bill cannot be null");
            }
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE Bill "
                    + " SET DAT_use = ?,"
                    + "     IDT_status = ?"
                    + " WHERE COD_ID = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setDate(1, (java.sql.Date) bill.getDatUse());
            pstmt.setString(2, String.valueOf(bill.getIdtStatus()));
            pstmt.setLong(3, bill.getCodID());
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
    synchronized public boolean remove(Long billID) throws PersistenceException {
        if(billID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM Bill WHERE COD_ID = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, billID);
            
            int removedRows = pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            if(removedRows == 1){
                return true;
            } 
            throw new PersistenceException(PersistenceException.NOT_A_DELETE, "Something went wrong when delete.");

        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }   
    }

    @Override
    synchronized public Bill getBillByID(Long billID) throws PersistenceException {
        if(billID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM bill WHERE COD_ID = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, billID);
            ResultSet rs = pstmt.executeQuery();

            Bill bill = new Bill();
            if (rs.next()) {
                bill.setCodID(billID);
                bill.setDatUse(rs.getDate("DAT_use"));
                bill.setIdtStatus(rs.getString("IDT_status").charAt(0));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return bill;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }  
    }

    @Override
    synchronized public boolean thisBillIDExists(Long billID) throws PersistenceException {
        if(billID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT 1 FROM Bill WHERE COD_ID = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, billID);
            ResultSet rs = pstmt.executeQuery();
            boolean status = rs.next();

            rs.close();
            pstmt.close();
            connection.close();

            return status;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }  
    }
}
