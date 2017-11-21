/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Table;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.util.db.ConnectionManager;
import java.sql.Connection;
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
public class TableDAOImpl implements TableDAO {
    private static TableDAOImpl tableDAO = null;

    public static TableDAOImpl getInstance(){
        if(tableDAO == null){
            tableDAO = new TableDAOImpl();
        }
        return tableDAO;
    }
    
    private TableDAOImpl() { }
    
    
    @Override
    synchronized public Integer insert(Table table) throws PersistenceException {
        if (table == null) {
            throw new PersistenceException(PersistenceException.INSERT_OBJECT_ISNULL, "Table cannot be null");
        }
        
        Integer idTable = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO \"table\"(\n"
                    + "	 idt_status, nro_seat)\n"
                    + "	VALUES ( ?, ?) returning cod_id;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(table.getIdtStatus()) );
            pstmt.setInt(2, table.getNroSeat());
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                idTable = rs.getInt("cod_id");
            }

            rs.close();
            pstmt.close();
            connection.close();

        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            System.out.print(ex.getMessage());
            if(ex.getErrorCode() == 1062)
                throw new PersistenceException(PersistenceException.DUPLICATED_KEY, "Duplicated Key");
        }

        return idTable;
    }

    @Override
    synchronized public boolean update(Table table) throws PersistenceException {
        if (table == null) {
            throw new PersistenceException(PersistenceException.UPDATE_OBJECT_ISNULL, "table cannot be null");
        }
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE \"table\" "
                    + " SET IDT_status = ?,"
                    + "     NRO_seat = ?"
                    + " WHERE COD_ID = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(table.getIdtStatus()));
            pstmt.setInt(2, table.getNroSeat());
            pstmt.setInt(3, table.getCodID());
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
    synchronized public boolean remove(Integer tableID) throws PersistenceException {
        if(tableID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM \"table\" WHERE COD_ID = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, tableID);
            
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
    synchronized public Table getTableByID(Integer tableID) throws PersistenceException {
        if(tableID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM \"table\" WHERE COD_ID = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, tableID);
            ResultSet rs = pstmt.executeQuery();

            Table table = new Table();
            if (rs.next()) {
                table.setCodID(tableID);
                table.setIdtStatus(rs.getString("IDT_status").charAt(0));
                table.setNroSeat(rs.getInt("NRO_seat"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return table;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }  
    }

    @Override
    synchronized public boolean containsThisTableID(Integer tableID) throws PersistenceException {
        if(tableID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT 1 FROM \"table\" WHERE COD_ID = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, tableID);
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
    public List<Table> listAll() throws PersistenceException {
        List list = new ArrayList();
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
                                   
            String sql = "SELECT * FROM \"table\"";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                list.add(rs);
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
