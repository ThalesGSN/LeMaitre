/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Image;
import br.cefetmg.LeMaitre.model.domain.ItemImage;
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
 * @author thales
 */
public class ItemImageDAOImpl implements ItemImageDAO {
    private static ItemImageDAOImpl itemImageDAO = null;

    public static ItemImageDAOImpl getInstance(){
        if(itemImageDAO == null){
            itemImageDAO = new ItemImageDAOImpl();
        }
        return itemImageDAO;
    }
    private ItemImageDAOImpl(){ }
    
    @Override
    synchronized public boolean insert(ItemImage itemImage) throws PersistenceException {
        if (itemImage == null) {
            throw new PersistenceException(PersistenceException.INSERT_OBJECT_ISNULL, "BillTable cannot be null");
        }
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO item_image(\n"
                    + "	cod_item, cod_image)\n"
                    + "	VALUES (?, ?);";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, itemImage.getCodItem());
            pstmt.setLong(2, itemImage.getCodImage());
            
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
    synchronized public boolean remove(Integer itemID, Long imageID) throws PersistenceException {
        if(itemID == null || imageID == null )
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM BillTable "
                    + "WHERE COD_ID_Bill = ? AND COD_ID_Table = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, itemID);
            pstmt.setLong(2, imageID);
            
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
    synchronized public List<Image> getImagesByItemID(Integer itemID) throws PersistenceException {
        List<Image> list = new ArrayList();
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
                                   
            String sql = "SELECT B.cod_image, B.des_location \n"
                    + "	from item_image A join image B\n"
                    + "	ON A.cod_image =  B.cod_image where A.cod_item = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            Image img = null;
            
            while (rs.next()) {
                img = new Image(rs.getLong("cod_image"), rs.getString("des_location"));
                list.add(img);
            }
            
            rs.close();
            pstmt.close();
            connection.close();
                        
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }
        
        return list;
    }
}
