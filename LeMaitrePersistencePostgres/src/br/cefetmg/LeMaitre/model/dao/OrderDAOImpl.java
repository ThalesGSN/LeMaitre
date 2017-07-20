/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Item;
import br.cefetmg.LeMaitre.model.domain.Order;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.util.db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public class OrderDAOImpl implements OrderDAO {
    private static OrderDAOImpl billDAO = null;

    public static OrderDAOImpl getInstance(){
        if(billDAO == null){
            billDAO = new OrderDAOImpl();
        }
        return billDAO;
    }
    
    private OrderDAOImpl() { }
    
    
    @Override
    synchronized public boolean insert(Order order) throws PersistenceException {
        if (order == null) {
            throw new PersistenceException(PersistenceException.INSERT_OBJECT_ISNULL, "Order cannot be null");
        }
        Long idBill = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO Order "
                    + "(COD_ID_Bill, COD_Item, IDT_status, VLR_price, COD_token) "
                    + "    VALUES (?, ?, ?, ?, ?);";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, order.getCodIDBill());
            pstmt.setInt(2, order.getCodItem());
            pstmt.setString(3, String.valueOf(order.getIdtStatus()));
            pstmt.setDouble(4, order.getVlrPrice());
            pstmt.setString(5, order.getCodToken());
            
            
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
    synchronized public boolean update(Order order) throws PersistenceException {
        if (order == null) {
            throw new PersistenceException(PersistenceException.UPDATE_OBJECT_ISNULL, "order cannot be null");
        }
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE Order "
                    + "SET IDT_status = ?,"
                    + "    VLR_price = ?,"
                    + "    COD_token = ?"
                    + "WHERE COD_ID_Bill = ? AND COD_Item = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(order.getIdtStatus()));
            pstmt.setDouble(2, order.getVlrPrice());
            pstmt.setString(3, order.getCodToken());
            pstmt.setInt(4, order.getCodItem());
            pstmt.setLong(5, order.getCodIDBill());
            
            int editedRows = pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            if(editedRows == 1){
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
    synchronized public boolean remove(Long billID, Integer itemID) throws PersistenceException {
        if(billID == null || itemID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM Order "
                    + "WHERE COD_ID_Bill = ? AND COD_Item = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, billID);
            pstmt.setInt(2, itemID);
            
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
    synchronized public Order getOrderByID(Long billID, Integer itemID) throws PersistenceException {
        if(billID == null || itemID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
       try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Order "
                    + "WHERE COD_ID_Bill = ? AND COD_Item = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, billID);
            pstmt.setInt(2, itemID);
            
            ResultSet rs = pstmt.executeQuery();

            Order order = new Order();
            
            if(rs.next()){
             order.setCodIDBill(billID);
             order.setCodItem(itemID);
             order.setIdtStatus(rs.getString("IDT_status").charAt(0));
             order.setVlrPrice(rs.getDouble("VLR_price"));
             order.setCodToken(rs.getString("COD_token"));
            }
            
            pstmt.close();
            connection.close();

            return order;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }
    }

    @Override
    synchronized public List<Order> listOrdersByBillID(Long billID) throws PersistenceException {
        if(billID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        ArrayList<Order> orders = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Order "
                    + "WHERE COD_ID_Bill = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, billID);
            
            ResultSet rs = pstmt.executeQuery();

            
            while(rs.next()){
                Order order = new Order();
                order.setCodIDBill(billID);
                order.setCodItem(rs.getInt("COD_Item"));
                order.setIdtStatus(rs.getString("IDT_status").charAt(0));
                order.setVlrPrice(rs.getDouble("VLR_price"));
                order.setCodToken(rs.getString("COD_token"));
            }
            
            pstmt.close();
            connection.close();

            return orders;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }
    }

    @Override
    synchronized public List<Item> listItemsByBillID(Long billID) throws PersistenceException {
        if(billID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        ArrayList<Item> items = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            Statement stmt = connection.createStatement();
            String sql = "SELECT B.VLR_price, B.NOM_item, B.DES_item, B.IDT_available, B.SEQ_Category "
                    + "FROM Order A JOIN Item B ON A.COD_Item = B.COD_Item"
                    + "WHERE A.COD_ID_Bill = ?;";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                Item item = new Item();
                
                item.setCodItem(rs.getInt("B.COD_Item"));
                item.setVlrPrice(rs.getDouble("B.VLR_price"));
                item.setNomItem(rs.getString("B.NOM_Item"));
                item.setDesItem(rs.getString("B.DES_item"));
                item.setIdtAvaliable(rs.getString("B.IDT_available").charAt(0));
                item.setCodCategory(rs.getInt("B.SEQ_Category"));
                
                items.add(item);
            }

            rs.close();
            stmt.close();
            connection.close();

            return items;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }
    }
}
