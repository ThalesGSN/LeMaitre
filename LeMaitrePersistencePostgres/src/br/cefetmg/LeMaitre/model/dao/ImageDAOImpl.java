/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Image;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.util.db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thales
 */
public class ImageDAOImpl implements ImageDAO {
    private static ImageDAOImpl imageDAO = null;

    public static ImageDAOImpl getInstance(){
        if(imageDAO == null){
            imageDAO = new ImageDAOImpl();
        }
        return imageDAO;
    }
    
    private ImageDAOImpl() { }
    
    @Override
    synchronized public Long insert(Image image) throws PersistenceException {
        if (image == null) {
            throw new PersistenceException(PersistenceException.INSERT_OBJECT_ISNULL, "image cannot be null");
        }
        
        Long idImage = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO image(\n"
                    + "	 des_location)\n"
                    + "	VALUES (?) RETURNING cod_image;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, image.getLocation());
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                idImage = rs.getLong("COD_image");
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

        return idImage
;
    }

    @Override
    synchronized public boolean update(Image image) throws PersistenceException {
        try {
            if (image == null) {
                throw new PersistenceException(PersistenceException.UPDATE_OBJECT_ISNULL, "image cannot be null");
            }
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE image\n"
                    + "	SET  des_location=?\n"
                    + "	WHERE cod_image=?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, image.getLocation());
            pstmt.setLong(2, image.getCodImage());
            int changedRows = pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            if (changedRows == 1) {
                return true;
            }
            throw new PersistenceException(PersistenceException.NOT_A_UPDATE, "Something went wrong when update.");
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch (SQLException ex) {
            throw new PersistenceException(ex);
        }
    }

    @Override
    synchronized public boolean remove(Long imageID) throws PersistenceException {
        if (imageID == null) {
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        }

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM image\n"
                    + "	WHERE cod_image = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, imageID);

            int removedRows = pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            if (removedRows == 1) {
                return true;
            }
            throw new PersistenceException(PersistenceException.NOT_A_DELETE, "Something went wrong when delete.");

        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch (SQLException ex) {
            throw new PersistenceException(ex);
        }
    }

    @Override
    synchronized public Image getImageByID(Long imageID) throws PersistenceException {
        if(imageID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT des_location\n"
                    + "	FROM image where cod_image = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, imageID);
            ResultSet rs = pstmt.executeQuery();

            Image image = new Image();
            if (rs.next()) {
                image.setCodImage(imageID);
                image.setLocation(rs.getString("des_location"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return image;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        } 
    }

    @Override
    synchronized public boolean containsThisImageID(Long imageID) throws PersistenceException {
        if(imageID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT 1\n"
                    + "	FROM image where cod_image = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, imageID);
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
