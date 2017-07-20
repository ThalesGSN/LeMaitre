/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.Employee;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thalesgsn
 */
public class EmployeeManagementImplTest {
    
    public EmployeeManagementImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of employeeInsert method, of class EmployeeManagementImpl.
     */
    @Test
    public void testEmployeeInsert() throws Exception {
        System.out.println("employeeInsert");
        Employee employee = null;
        EmployeeManagementImpl instance = null;
        Integer expResult = null;
        Integer result = instance.employeeInsert(employee);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of employeeUpdate method, of class EmployeeManagementImpl.
     */
    @Test
    public void testEmployeeUpdate() throws Exception {
        System.out.println("employeeUpdate");
        Employee employee = null;
        EmployeeManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.employeeUpdate(employee);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of employeeRemove method, of class EmployeeManagementImpl.
     */
    @Test
    public void testEmployeeRemove() throws Exception {
        System.out.println("employeeRemove");
        Integer employeeID = null;
        EmployeeManagementImpl instance = null;
        boolean expResult = false;
        boolean result = instance.employeeRemove(employeeID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmployeeByID method, of class EmployeeManagementImpl.
     */
    @Test
    public void testGetEmployeeByID() throws Exception {
        System.out.println("getEmployeeByID");
        Integer employeeID = null;
        EmployeeManagementImpl instance = null;
        Employee expResult = null;
        Employee result = instance.getEmployeeByID(employeeID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
