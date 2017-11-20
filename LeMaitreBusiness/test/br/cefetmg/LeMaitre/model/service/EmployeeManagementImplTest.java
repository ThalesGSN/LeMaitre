/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.EmployeeDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Employee;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.Objects;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Breno Mariz
 */
public class EmployeeManagementImplTest {
    
    private Employee employee;
    private Integer codID;
    private String nomName;
    private char idtProfile;
    private String nomUsername;
    private String txtPassword;
    private EmployeeManagement employeeManagement;
    
    
    public EmployeeManagementImplTest() {
        idtProfile = 'M';
        codID = new Integer(1);
        nomName = new String("Dimas");
        nomUsername = new String("didi");
        txtPassword = new String("123456");
        employee = new Employee(codID, nomName, idtProfile, nomUsername, txtPassword);
        employeeManagement = new EmployeeManagementImpl(EmployeeDAOImpl.getInstance());

    }

    
    @Before
    public void setUp() {
        idtProfile = 'M'; // M = manager
        codID = -1;
        txtPassword = new String("123456");
        nomUsername = new String("didi");
        employee.setidtProfile(idtProfile);
        employee.setTxtPassword(txtPassword);
        employee.setNomUsername(nomUsername);
    }
    
    @After
    public void tearDown() {
        try {
            if (codID != -1L) {
                employeeManagement.employeeRemove(codID);
            }
        } catch (PersistenceException ex) {
            System.out.println("Failed to remove employee after test");
        }
    }

    /**
     * Test of employeeInsert method, of class EmployeeManagementImpl.
     */
    @Test
    public void testEmployeeInsert() {
        try {
            codID = employeeManagement.employeeInsert(employee);
        } catch (BusinessException | PersistenceException ex) {
            fail ("Failed to insert employee");
        }
        System.out.println("Passed testEmployeeInsert test");
    }

    
    /**
     * Test of employeeInsertNull method, of class EmployeeManagementImpl.
     */
    @Test
    public void testEmployeeInsertNull() {
        try {
            employee = null;
            codID = employeeManagement.employeeInsert(employee);
            fail("Failed to catch exception when inserting null employee");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testEmployeeInsertNull test");

        }
    }
    
    @Test
    public void testEmployeeInsertInvalidIdt() {
        try {
            employee.setidtProfile('X');
            codID = employeeManagement.employeeInsert(employee);
            fail("Failed to catch exception when inserting invalid idt");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testEmployeeInsertInvalidIdt test");
        }
    }

    /**
     * Test of employeeUpdate method, of class EmployeeManagementImpl.
     */
    @Test
    public void testEmployeeUpdate(){
        try {
            codID = employeeManagement.employeeInsert(employee);
            employee.setCodID(codID);
            employeeManagement.employeeUpdate(employee);
            System.out.println("Passed testEmployeeUpdate test");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to update correct employee");
        }
    }


    @Test
    public void testEmployeeUpdateNullId() {
        try {
            codID = employeeManagement.employeeInsert(employee);
            employeeManagement.employeeUpdate(employee);
            fail("Failed to catch exception when updating  null id");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testEmployeeUpdateNullId test");
        }
    }
    
    @Test
    public void testTableUpdateInvalidIdt() {
        try {
            codID = employeeManagement.employeeInsert(employee);
            employee.setidtProfile('X');
            employee.setCodID(codID);
            employeeManagement.employeeUpdate(employee);
            fail("Failed to catch exception when updating null idt");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testEmployeeInsertInvalidIdt test");
        }
    }
    
    /**
     * Test of employeeRemove method, of class EmployeeManagementImpl.
     */
    @Test
    public void testEmployeeRemove(){
        try {
            codID = employeeManagement.employeeInsert(employee);
            employeeManagement.employeeRemove(codID);
            codID = -1;
            System.out.println("Correctly removed employee");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to remove correct employee");
        }
    }

    /**
     * Test of getEmployeeByID method, of class EmployeeManagementImpl.
     */
    @Test
    public void testGetEmployeeByID(){
        try {
            codID = employeeManagement.employeeInsert(employee);
            Employee newEmployee = employeeManagement.getEmployeeByID(codID);
            if (!Objects.equals(newEmployee.getCodID(), codID)) {
                fail("Failed to retrieve correct employee");
            }
            System.out.println("Correctly retrieved employee");
        } catch (BusinessException | PersistenceException ex) {
            ex.printStackTrace();
            fail("Failed to retrieve correct employee");

        }
    }
    
    @Test
    public void testTableUpdateNullnomName() {
        try {
            codID = employeeManagement.employeeInsert(employee);
            employee.setNomName(null);
            employee.setCodID(codID);
            employeeManagement.employeeUpdate(employee);
            fail("Failed to catch exception when updating  null id");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testEmployeeUpdateNullId test");
        }
    }
    
    @Test
    public void testTableUpdateNullNomUserName() {
        try {
            codID = employeeManagement.employeeInsert(employee);
            employee.setNomUsername(null);
            employee.setCodID(codID);
            employeeManagement.employeeUpdate(employee);
            fail("Failed to catch exception when updating  null id");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testEmployeeUpdateNullId test");
        }
    }
    
    @Test
    public void testTableUpdateNullPassword() {
        try {
            codID = employeeManagement.employeeInsert(employee);
            employee.setTxtPassword(null);
            employee.setCodID(codID);
            employeeManagement.employeeUpdate(employee);
            fail("Failed to catch exception when updating  null id");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testEmployeeUpdateNullId test");
        }
    }
    
}
