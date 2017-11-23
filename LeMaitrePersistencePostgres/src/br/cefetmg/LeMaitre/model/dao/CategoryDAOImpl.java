 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Category;
import br.cefetmg.LeMaitre.model.domain.Subcategory;
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
public class CategoryDAOImpl implements CategoryDAO {
    private static CategoryDAOImpl categoryDAO = null;

    public static CategoryDAOImpl getInstance(){
        if(categoryDAO == null){
            categoryDAO = new CategoryDAOImpl();
        }
        return categoryDAO;
    }
    
    private CategoryDAOImpl() { }
    
    
    @Override
    synchronized public Integer insert(Category category) throws PersistenceException {
        if (category == null) {
            throw new PersistenceException(PersistenceException.INSERT_OBJECT_ISNULL, "Category cannot be null");
        }
        Integer idCategory = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO Category "
                    + "(NOM_category) "
                    + "    VALUES (?) returning SEQ_Category;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, category.getNomCategory());
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                idCategory = rs.getInt("SEQ_Category");
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

        return idCategory;
    }

    @Override
    synchronized public boolean update(Category category) throws PersistenceException {
        if (category == null) {
            throw new PersistenceException(PersistenceException.UPDATE_OBJECT_ISNULL, "category cannot be null");
        }

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE Category "
                    + " SET NOM_category = ?"
                    + " WHERE SEQ_Category = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, category.getNomCategory());
            pstmt.setLong(2, category.getSeqCategory());
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
    synchronized public boolean remove(Integer categoryID) throws PersistenceException {
        if(categoryID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM Category WHERE SEQ_Category = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, categoryID);
            
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
    synchronized public Category getCategoryByID(Integer categoryID) throws PersistenceException {
        if(categoryID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM category WHERE SEQ_Category = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, categoryID);
            ResultSet rs = pstmt.executeQuery();

            Category category = new Category();
            if (rs.next()) {
                category.setSeqCategory(categoryID);
                category.setNomCategory(rs.getString("NOM_category"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return category;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }  
    }

    @Override
    synchronized public boolean containsThisCategoryID(Integer categoryID) throws PersistenceException {
        if(categoryID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT 1 FROM Category WHERE SEQ_Category = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, categoryID);
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
    synchronized public List<Category> listAllCategories() throws PersistenceException {
        ArrayList<Category> categories = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM Category;";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            categories = new ArrayList();
            while(rs.next()){
                Category category = new Category();
                
                category.setSeqCategory(rs.getInt("SEQ_Category"));
                category.setNomCategory(rs.getString("NOM_Category"));
                
                categories.add(category);
            }

            rs.close();
            stmt.close();
            connection.close();

            return categories;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }
    }

    @Override
    public List<Subcategory> listAllSubcategories(Integer categoryID) throws PersistenceException {
         if (categoryID == null) {
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        }

        ArrayList<Subcategory> subcategories = null;

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT seq_subcategory, nom_subcategory\n"
                    + "	FROM subcategory where seq_category = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, categoryID);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Subcategory subcategory = new Subcategory();

                subcategory.setSeqCategory(categoryID);
                subcategory.setSeqCategory(rs.getInt("seq_subcategory"));
                subcategory.setNomSubcategory(rs.getString("nom_subcategory"));


                subcategories.add(subcategory);
            }

            pstmt.close();
            connection.close();

            throw new PersistenceException(PersistenceException.NOT_A_DELETE, "Something went wrong when delete.");

        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch (SQLException ex) {
            throw new PersistenceException(ex);
        }
    }
}
