/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Subcategory;
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
public class SubcategoryDAOImpl implements SubcategoryDAO {
    private static SubcategoryDAOImpl subcateoryDAO = null;

    public static SubcategoryDAOImpl getInstance() {
        if (subcateoryDAO == null) {
            subcateoryDAO = new SubcategoryDAOImpl();
        }
        return subcateoryDAO;
    }

    private SubcategoryDAOImpl() {}
    
    @Override
    synchronized public Integer insert(Subcategory subcategory) throws PersistenceException {
        if (subcategory == null) {
            throw new PersistenceException(PersistenceException.INSERT_OBJECT_ISNULL, "subcategory cannot be null");
        }
        
        Integer idSubcategory = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO subcategory(\n"
                    + "	seq_category, nom_subcategory)\n"
                    + "	VALUES (?, ?) returning seq_subcategory;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, subcategory.getSeqCategory());
            pstmt.setString(2, subcategory.getNomSubcategory());
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                idSubcategory = rs.getInt("seq_subcategory");
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

        return idSubcategory;
    }

    @Override
    synchronized public boolean update(Subcategory subcategory) throws PersistenceException {
        try {
            if (subcategory == null) {
                throw new PersistenceException(PersistenceException.UPDATE_OBJECT_ISNULL, "subcategory cannot be null");
            }
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE subcategory\n"
                    + "	SET  nom_subcategory = ?\n"
                    + "	WHERE seq_category = ? AND seq_subcategory = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, subcategory.getNomSubcategory());
            pstmt.setInt(2, subcategory.getSeqCategory());
            pstmt.setInt(3, subcategory.getSeqSubcategory());
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
    synchronized public boolean remove(Integer seqCategory, Integer seqSubcategory) throws PersistenceException {
        if(seqCategory == null || seqSubcategory == null )
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM subcategory\n" +
            "	WHERE seq_category = ? AND seq_subcategory = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, seqCategory);
            pstmt.setLong(2, seqSubcategory);
            
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
    synchronized public Subcategory getSubcategoryByID(Integer seqCategory, Integer seqSubcategory) throws PersistenceException {
        if(seqCategory == null || seqSubcategory == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
       try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT nom_subcategory\n"
                    + "	FROM subcategory "
                    + "WHERE seq_category = ? AND seq_subcategory = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, seqCategory);
            pstmt.setLong(2, seqSubcategory);
            
            ResultSet rs = pstmt.executeQuery();

            Subcategory subcategory = new Subcategory();
            
            if(rs.next()){
                subcategory.setSeqCategory(seqCategory);
                subcategory.setSeqSubcategory(seqSubcategory);
                subcategory.setNomSubcategory(rs.getString("nom_subcategory"));
            }
            
            pstmt.close();
            connection.close();

            return subcategory;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }
    }

    @Override
    synchronized public boolean containsThisSubcategoryID(Integer seqCategory, Integer seqSubcategory) throws PersistenceException {
        if (seqCategory == null || seqSubcategory == null) {
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        }

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT 1\n"
                    + "	FROM subcategory WHERE seq_category = ? "
                    + "AND seq_subcategory = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, seqCategory);
            pstmt.setInt(2, seqSubcategory);
            
            ResultSet rs = pstmt.executeQuery();
            boolean status = rs.next();

            rs.close();
            pstmt.close();
            connection.close();

            return status;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch (SQLException ex) {
            throw new PersistenceException(ex);
        }
    }
}
