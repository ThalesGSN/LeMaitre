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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    synchronized public Bill create() throws PersistenceException {
        Bill bill = null;
            
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO bill(\n"
                    + "	 dat_use, idt_status)\n"
                    + "	VALUES ( current_date, 'O') "
                    + "returning cod_token, dat_use, idt_status;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                bill = new Bill();
                bill.setCodToken(rs.getString("cod_token"));
                bill.setDatUse(rs.getDate("dat_use"));
                bill.setIdtStatus('O');
            }

            rs.close();
            pstmt.close();
            connection.close();
            
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            if(ex.getErrorCode() == 1062)
                throw new PersistenceException(PersistenceException.DUPLICATED_KEY, "Duplicated Key");
            throw new PersistenceException(PersistenceException.DUPLICATED_KEY, ex.getMessage());
        }
        
        return bill;
    }

    @Override
    synchronized public boolean update(Bill bill) throws PersistenceException {
        try {
            if (bill == null) {
                throw new PersistenceException(PersistenceException.UPDATE_OBJECT_ISNULL, "bill cannot be null");
            }
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE Bill "
                    + " SET dat_use = ?,"
                    + "     idt_status = ?"
                    + " WHERE cod_token = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setDate(1, new java.sql.Date(bill.getDatUse().getTime()));
            pstmt.setString(2, String.valueOf(bill.getIdtStatus()));
            pstmt.setString(3, bill.getCodToken());
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
    synchronized public boolean remove(String codToken) throws PersistenceException {
        if(codToken == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM Bill WHERE cod_token = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, codToken);
            
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
    synchronized public Bill getBillByID(String codToken) throws PersistenceException {
        if(codToken == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM bill WHERE cod_token = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, codToken);
            ResultSet rs = pstmt.executeQuery();

            Bill bill = new Bill();
            if (rs.next()) {
                bill.setCodToken(codToken);
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
    synchronized public boolean containsThisBillID(String codToken) throws PersistenceException {
        if(codToken == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT 1 FROM Bill WHERE cod_token = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, codToken);
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

    @Override
    public List<Bill> listAll() throws PersistenceException {
        List<Bill> list = null;
        Bill bill = null;
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
                                   
            String sql = "SELECT * FROM Bill;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            bill = new Bill();
            list = new ArrayList();
            while(rs.next()) {
                bill.setCodToken(rs.getString("cod_token"));
                bill.setDatUse(rs.getDate("DAT_use"));
                bill.setIdtStatus(rs.getString("IDT_status").charAt(0));
                
                list.add(bill);
            }
            
            rs.close();
            pstmt.close();
            connection.close();
                        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BillDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BillDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
