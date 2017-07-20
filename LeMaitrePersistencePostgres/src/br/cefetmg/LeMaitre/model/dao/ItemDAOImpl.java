/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Item;
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
public class ItemDAOImpl implements ItemDAO {
    private static ItemDAOImpl itemDAO = null;

    public static ItemDAOImpl getInstance(){
        if(itemDAO == null){
            itemDAO = new ItemDAOImpl();
        }
        return itemDAO;
    }
    
    private ItemDAOImpl() { }
    
    
    @Override
    synchronized public Integer insert(Item item) throws PersistenceException {
        if (item == null) {
            throw new PersistenceException(PersistenceException.INSERT_OBJECT_ISNULL, "Item cannot be null");
        }
        Integer idItem = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO Item "
                    + "(VLR_price, NOM_item, DES_item, IDT_available, SEQ_Category) "
                    + "    VALUES (?, ?, ?, ?, ?) returning COD_Item;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setDouble(1, item.getVlrPrice());
            pstmt.setString(2, item.getNomItem());
            pstmt.setString(3, item.getDesItem());
            pstmt.setString(4, String.valueOf(item.getIdtAvaliable()));
            pstmt.setInt(5, item.getCodCategory());
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                idItem = rs.getInt("COD_Item");
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

        return idItem;
    }

    @Override
    synchronized public boolean update(Item item) throws PersistenceException {
        if (item == null) {
            throw new PersistenceException(PersistenceException.UPDATE_OBJECT_ISNULL, "item cannot be null");
        }
        
        try {    
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE Item "
                    + " SET VLR_price =?,"
                    + " NOM_item = ?,"
                    + " DES_item = ?,"
                    + " IDT_available = ?,"
                    + " SEQ_Category = ?"
                    + " WHERE COD_Item = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setDouble(1, item.getVlrPrice());
            pstmt.setString(2, item.getNomItem());
            pstmt.setString(3, item.getDesItem());
            pstmt.setString(4, String.valueOf(item.getIdtAvaliable()));
            pstmt.setInt(5, item.getCodCategory());
            pstmt.setInt(6, item.getCodItem());
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
    synchronized public boolean remove(Integer itemID) throws PersistenceException {
        if(itemID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM Item WHERE COD_Item = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, itemID);
            
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
    synchronized public Item getItemByID(Integer itemID) throws PersistenceException {
        if(itemID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM item WHERE COD_Item = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, itemID);
            ResultSet rs = pstmt.executeQuery();

            Item item = new Item();
            if (rs.next()) {                
                item.setCodItem(rs.getInt("COD_Item"));
                item.setVlrPrice(rs.getDouble("VLR_price"));
                item.setNomItem(rs.getString("NOM_Item"));
                item.setDesItem(rs.getString("DES_item"));
                item.setIdtAvaliable(rs.getString("IDT_available").charAt(0));
                item.setCodCategory(rs.getInt("SEQ_Category"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return item;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }  
    }
    
    @Override
    synchronized public boolean thisItemIDExists(Integer itemID) throws PersistenceException {
        if(itemID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT 1 FROM Item WHERE COD_Item = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, itemID);
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
    synchronized public List<Item> listAllItems() throws PersistenceException {
        ArrayList<Item> items = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM Item;";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                Item item = new Item();
                
                item.setCodItem(rs.getInt("COD_Item"));
                item.setVlrPrice(rs.getDouble("VLR_price"));
                item.setNomItem(rs.getString("NOM_Item"));
                item.setDesItem(rs.getString("DES_item"));
                item.setIdtAvaliable(rs.getString("IDT_available").charAt(0));
                item.setCodCategory(rs.getInt("SEQ_Category"));
                
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
