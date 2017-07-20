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
 * @author Temp
 */
public class BillTableDAOImpl implements BillTableDAO{
    private static BillTableDAOImpl billDAO = null;

    public static BillTableDAOImpl getInstance(){
        if(billDAO == null){
            billDAO = new BillTableDAOImpl();
        }
        return billDAO;
    }
    
    private BillTableDAOImpl() { }
    
    
    @Override
    public boolean insert(BillTable billTable) throws PersistenceException {
        if (billTable == null) {
            throw new PersistenceException(PersistenceException.INSERTED_OBJECT_ISNULL, "BillTable cannot be null");
        }
        Long idBill = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO BillTable "
                    + "(COD_ID_Bill, COD_ID_Table) "
                    + "    VALUES (?, ?);";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, billTable.getCodIDBill());
            pstmt.setInt(2, billTable.getcodIDBillTable());
            
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
    public boolean remove(Long billID, Integer tableID) throws PersistenceException {
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM BillTable "
                    + "WHERE COD_ID_Bill = ? AND COD_ID_Table = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, billID);
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
    public List<Table> listTablesByBillID(Long billID) throws PersistenceException {
        ArrayList<Table> tables = null;
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT A.COD_ID_Table AS ID, B.IDT_status AS STATUS, B.NRO_seat AS SEATS"
                    + "FROM BillTable A JOIN Bill B ON A.COD_ID_Bill = B.COD_ID "
                    + "WHERE COD_ID_Bill = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, billID);
            
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
    public List<Bill> listBillsByTableID(Integer tableID) throws PersistenceException {
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
                
                bill.setCodID(rs.getLong("ID"));
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
