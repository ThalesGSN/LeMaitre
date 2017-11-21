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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
    public synchronized boolean insert(Order order) throws PersistenceException {
        if (order == null) {
            throw new PersistenceException(PersistenceException.INSERT_OBJECT_ISNULL, "Order cannot be null");
        }
        Date datOrder = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO \"order\" (\n"
                    + "	cod_id_bill, cod_item, idt_status, vlr_price, qtd_item, dat_order)\n"
                    + "	VALUES (?, ?, ?, ?, ?);";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, order.getCodToken());
            pstmt.setObject(2, order.getDatOrder());
            pstmt.setInt(3, order.getCodItem());
            pstmt.setString(3, String.valueOf(order.getIdtStatus()));
            pstmt.setDouble(5, order.getVlrPrice());
            pstmt.setInt(6, order.getQtdItem());
            pstmt.setTimestamp(7, order.getDatOrder());    
            
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
            pstmt.setString(5, order.getCodToken());
            
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
    synchronized public boolean remove(String codToken, Timestamp datOrder) throws PersistenceException {
        if(codToken == null || datOrder == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM Order "
                    + "WHERE COD_ID_Bill = ? AND COD_Item = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, codToken);
            pstmt.setTimestamp(2, datOrder);
            
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
    synchronized public Order getOrderByID(String codToken, Timestamp datOrder) throws PersistenceException {
        if(codToken == null || datOrder == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
       try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT cod_item, idt_status, vlr_price, qtd_item\n"
                    + "	FROM \"order\" cod_id_bill = ? AND dat_order = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, codToken);
            pstmt.setTimestamp(2, datOrder);
            
            ResultSet rs = pstmt.executeQuery();

            Order order = new Order();
            
            if(rs.next()){
             order.setCodToken(codToken);
             order.setDatOrder(datOrder);
             order.setCodItem(rs.getInt("cod_item"));
             order.setIdtStatus(rs.getString("IDT_status").charAt(0));
             order.setVlrPrice(rs.getDouble("VLR_price"));
             order.setQtdItem(rs.getInt("qtd_item"));
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
    synchronized public List<Order> listOrdersByToken(String codToken) throws PersistenceException {
        if(codToken == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        ArrayList<Order> orders = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM Order "
                    + "WHERE COD_ID_Bill = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, codToken);
            
            ResultSet rs = pstmt.executeQuery();

            
            while(rs.next()){
                Order order = new Order();
                order.setCodToken(codToken);
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
    synchronized public List<Item> listItemsByToken(String codToken) throws PersistenceException {
        if(codToken == null)
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
                item.setIsAvaliable(rs.getBoolean("B.IDT_available"));
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
