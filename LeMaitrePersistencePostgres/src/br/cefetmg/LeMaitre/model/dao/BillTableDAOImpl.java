/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Bill;
import br.cefetmg.LeMaitre.model.domain.BillTable;
import br.cefetmg.LeMaitre.model.domain.Table;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.util.db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public class BillTableDAOImpl implements BillTableDAO {
    private static BillTableDAOImpl billDAO = null;

    public static BillTableDAOImpl getInstance(){
        if(billDAO == null){
            billDAO = new BillTableDAOImpl();
        }
        return billDAO;
    }
    
    private BillTableDAOImpl() { }
    
    
    @Override
    synchronized public boolean insert(BillTable billTable) throws PersistenceException {
        if (billTable == null) {
            throw new PersistenceException(PersistenceException.INSERT_OBJECT_ISNULL, "BillTable cannot be null");
        }
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO BillTable "
                    + "(COD_ID_Bill, COD_ID_Table) "
                    + "    VALUES (?, ?);";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, billTable.getCodIDBill());
            pstmt.setInt(2, billTable.getCodIDTable());
            
            pstmt.executeQuery();

            pstmt.close();
            connection.close();
            
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            if(ex.getErrorCode() == 1062)
                throw new PersistenceException(PersistenceException.DUPLICATED_KEY, "Duplicated Key");
        }

        return true;
    }


    @Override
    synchronized public boolean remove(String codToken, Integer tableID) throws PersistenceException {
        if(codToken == null || tableID == null )
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM BillTable "
                    + "WHERE COD_ID_Bill = ? AND COD_ID_Table = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, codToken);
            pstmt.setInt(2, tableID);
            
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
    synchronized public List<Table> listTablesByToken(String codToken) throws PersistenceException {
        if(codToken == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        ArrayList<Table> tables = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT A.COD_ID_Table AS ID, B.IDT_status AS STATUS, B.NRO_seat AS SEATS"
                    + "FROM BillTable A JOIN Bill B ON A.COD_ID_Bill = B.COD_ID "
                    + "WHERE COD_ID_Bill = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, codToken);
            
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Table table = new Table();
                
                table.setCodID(rs.getInt("ID"));
                table.setIdtStatus(rs.getString("STATUS").charAt(0));
                table.setNroSeat(rs.getInt("SEATS"));
                
                tables.add(table);
            }
            
            pstmt.close();
            connection.close();

             
            throw new PersistenceException(PersistenceException.NOT_A_DELETE, "Something went wrong when delete.");

        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }
    }

    @Override
    synchronized public List<Bill> listBillsByTableID(Integer tableID) throws PersistenceException {
        if(tableID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        ArrayList<Bill> bills = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT A.COD_ID_Bill AS ID, B.DAT_use AS DAT, B.IDT_status AS STATUS "
                    + "FROM BillTable A JOIN Bill B ON A.COD_ID_Bill = B.COD_ID "
                    + "WHERE COD_ID_Table = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, tableID);
            
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                Bill bill = new Bill();
                
                bill.setCodToken(rs.getString("ID"));
                bill.setDatUse(rs.getDate("DAT"));
                bill.setIdtStatus(rs.getString("STATUS").charAt(0));
                
                bills.add(bill);
            }
            
            pstmt.close();
            connection.close();

             
            throw new PersistenceException(PersistenceException.NOT_A_DELETE, "Something went wrong when delete.");

        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }
    }



}
