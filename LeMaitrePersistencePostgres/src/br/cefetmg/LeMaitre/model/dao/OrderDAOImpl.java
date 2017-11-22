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
    public synchronized Timestamp insert(Order order) throws PersistenceException {
        if (order == null) {
            throw new PersistenceException(PersistenceException.INSERT_OBJECT_ISNULL, "Order cannot be null");
        }
        Timestamp stamp = null;
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO \"order\"(\n"
                    + "	cod_id_bill, dat_order, cod_item, idt_status, vlr_price, qtd_item)\n"
                    + "	VALUES (?, current_timestamp, ?, ?, ?, ?) returning dat_order;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, order.getCodToken());
            pstmt.setInt(2, order.getCodItem());
            pstmt.setString(3, String.valueOf(order.getIdtStatus()));
            pstmt.setDouble(4, order.getVlrPrice());
            pstmt.setInt(5, order.getQtdItem());
            
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                stamp = rs.getTimestamp("dat_order");
            }

            pstmt.close();
            connection.close();
            
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            if(ex.getErrorCode() == 1062)
                throw new PersistenceException(PersistenceException.DUPLICATED_KEY, "Duplicated Key");
            throw  new PersistenceException(PersistenceException.DUPLICATED_KEY, ex.getMessage());
        }

        return stamp;
    }

    @Override
    synchronized public boolean update(Order order) throws PersistenceException {
        if (order == null) {
            throw new PersistenceException(PersistenceException.UPDATE_OBJECT_ISNULL, "order cannot be null");
        }
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE \"order\"\n"
                    + "	SET  cod_item=?, idt_status=?, vlr_price=?, qtd_item=?\n"
                    + "	WHERE  cod_id_bill = ? AND dat_order = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setInt(1, order.getCodItem());
            pstmt.setString(2, String.valueOf(order.getIdtStatus()));
            pstmt.setDouble(3, order.getVlrPrice());
            pstmt.setInt(4, order.getQtdItem());
            
            pstmt.setString(5, order.getCodToken());
            pstmt.setTimestamp(6, order.getDatOrder());
            
            int editedRows = pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            if(editedRows == 1){
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
    synchronized public boolean remove(String codToken, Timestamp datOrder) throws PersistenceException {
        if(codToken == null || datOrder == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM public.\"order\"\n"
                    + "	WHERE cod_id_bill = ? AND dat_order = ?;";
            
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
                    + "	FROM \"order\" WHERE cod_id_bill = ? AND dat_order = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, codToken);
            pstmt.setTimestamp(2, datOrder);
            
            ResultSet rs = pstmt.executeQuery();

            Order order = null;
            
            if(rs.next()){
                order = new Order();
                order.setCodToken(codToken);
                order.setDatOrder(datOrder);
                order.setCodItem(rs.getInt("cod_item"));
                order.setIdtStatus(rs.getString("idt_status").charAt(0));
                order.setVlrPrice(rs.getDouble("vlr_price"));
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

            String sql = "SELECT dat_order, cod_item, idt_status, vlr_price, qtd_item\n"
                    + "	FROM \"order\" Where cod_id_bill = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, codToken);
            
            ResultSet rs = pstmt.executeQuery();

            Order order = null;
            orders = new ArrayList();
            while(rs.next()){
                order = new Order();
                
                order.setCodToken(codToken);
                order.setDatOrder(rs.getTimestamp("dat_order"));
                order.setCodItem(rs.getInt("cod_item"));
                order.setIdtStatus(rs.getString("idt_status").charAt(0));
                order.setVlrPrice(rs.getDouble("vlr_price"));
                order.setQtdItem(rs.getInt("qtd_item"));
                
                orders.add(order);
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
            String sql = "SELECT B.cod_item, B.vlr_price, B.nom_item, B.des_item, B.idt_available, B.seq_Category \n"
                    + "FROM \"order\" A JOIN Item B ON A.cod_item = B.cod_item\n"
                    + "WHERE A.COD_ID_Bill = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, codToken);
            
            ResultSet rs = pstmt.executeQuery();
            
            Item item = null;
            items = new ArrayList();
            while(rs.next()){
                item = new Item();
                
                item.setCodItem(rs.getInt("cod_item"));
                item.setVlrPrice(rs.getDouble("vlr_price"));
                item.setNomItem(rs.getString("nom_item"));
                item.setDesItem(rs.getString("des_item"));
                item.setIsAvaliable(rs.getBoolean("idt_available"));
                item.setCodCategory(rs.getInt("seq_Category"));
                
                items.add(item);
            }

            rs.close();
            pstmt.close();
            connection.close();

            return items;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }
    }
}
