/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Employee;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import br.cefetmg.LeMaitre.util.db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Thalesgsn
 */
public class EmployeeDAOImpl implements EmployeeDAO {
    private static EmployeeDAOImpl employeeDAO = null;

    public static EmployeeDAOImpl getInstance(){
        if(employeeDAO == null){
            employeeDAO = new EmployeeDAOImpl();
        }
        return employeeDAO;
    }
    
    private EmployeeDAOImpl() { }
    
    
    @Override
    synchronized public Integer insert(Employee employee) throws PersistenceException {
        if (employee == null) {
            throw new PersistenceException(PersistenceException.INSERT_OBJECT_ISNULL, "Employee cannot be null");
        }
        Integer idEmployee = null;
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO Employee "
                    + "(NOM_name, IDT_profile, NOM_username, TXT_password) "
                    + "    VALUES (?, ?, ?, ?) returning COD_ID;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, employee.getNomName());
            pstmt.setString(2, String.valueOf(employee.getidtProfile()));
            pstmt.setString(3, employee.getNomUsername());
            pstmt.setString(4, employee.getTxtPassword());
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                idEmployee = rs.getInt("COD_ID");
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

        return idEmployee;
    }

    @Override
    synchronized public boolean update(Employee employee) throws PersistenceException {
        if (employee == null) {
            throw new PersistenceException(PersistenceException.UPDATE_OBJECT_ISNULL, "employee cannot be null");
        }

        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE Employee "
                    + " SET NOM_name = ?,"
                    + " IDT_profile =  ?,"
                    + " NOM_username = ?,"
                    + " TXT_password = ? "
                    + " WHERE COD_ID = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, employee.getNomName());
            pstmt.setString(2, String.valueOf(employee.getidtProfile()));
            pstmt.setString(3, employee.getNomUsername());
            pstmt.setString(4, employee.getTxtPassword());
            pstmt.setInt(5, employee.getCodID());
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
    synchronized public boolean remove(Integer employeeID) throws PersistenceException {
        if(employeeID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "DELETE FROM Employee WHERE COD_ID = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, employeeID);
            
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
    synchronized public Employee getEmployeeByID(Integer employeeID) throws PersistenceException {
        if(employeeID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters cant be null");
        
        try {
            Connection connection = ConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM employee WHERE COD_ID = ?;";
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, employeeID);
            ResultSet rs = pstmt.executeQuery();

            Employee employee = new Employee();
            if (rs.next()) {
                employee.setCodID(employeeID);
                employee.setNomName(rs.getString("NOM_name"));
                employee.setidtProfile(rs.getString("IDT_profile").charAt(0));
                employee.setNomUsername(rs.getString("NOM_username"));
                employee.setTxtPassword(rs.getString("TXT_password"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return employee;
        } catch (ClassNotFoundException ex) {
            throw new PersistenceException(PersistenceException.DRIVER_NOT_FOUND, "Driver not found");
        } catch(SQLException ex){
            throw new PersistenceException(ex);
        }  
    }

}
