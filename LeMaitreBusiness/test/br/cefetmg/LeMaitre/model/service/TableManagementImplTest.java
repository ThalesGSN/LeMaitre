/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.TableDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Table;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thalesgsn
 */
public class TableManagementImplTest {
    
    private Table table;
    private Long codID;
    private char idtStatus;
    private int nroSeat;
    private TableManagement tableManagement;
    
    public TableManagementImplTest() {
        idtStatus = 'O';
        nroSeat = 4;
        table = new Table(idtStatus, nroSeat);
        tableManagement = new TableManagementImpl(TableDAOImpl.getInstance());
    }
        
    @Before
    public void setUp() {
        idtStatus = 'O';
        codID = -1L;
        nroSeat = 4;
        table.setIdtStatus(idtStatus);
        table.setNroSeat(nroSeat);
    }
    
    @After
    public void tearDown() {
        try {
            if (codID != -1L) {
                tableManagement.tableRemove(codID);
            }
        } catch (PersistenceException ex) {
            System.out.println("Failed to remove table after test");
        }
    }

    /**
     * Test of tableInsert method, of class TableManagementImpl.
     */
    @Test
    public void testTableInsert() {
        try {
            codID = tableManagement.tableInsert(table);
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to insert correct table");
        }
        System.out.println("Passed testTableInsert test");
    }
    
    /**
     * Test of tableInsert method, of class TableManagementImpl.
     */
    @Test
    public void testTableInsertNull() {
        try {
            table = null;
            codID = tableManagement.tableInsert(table);
            fail("Failed to catch exception when inserting null table");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testTableInsertNull test");
            
        }
    }
    
    /**
     * Test of tableInsert method, of class TableManagementImpl.
     */
    @Test
    public void testTableInsertInvalidIdt() {
        try {
            table.setIdtStatus('P');
            codID = tableManagement.tableInsert(table);
            fail("Failed to catch exception when inserting invalid idt");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testTableInsertInvalidIdt test");
        }
    }

    /**
     * Test of tableUpdate method, of class TableManagementImpl.
     */
    @Test
    public void testTableUpdate() {
        try {
            codID = tableManagement.tableInsert(table);
            table.setCodID(codID);
            tableManagement.tableUpdate(table);
            System.out.println("Passed testTableUpdate test");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to update correct table");
        }
    }
    
    /**
     * Test of tableUpdate method, of class TableManagementImpl.
     */
    @Test
    public void testTableUpdateNullId() {
        try {
            codID = tableManagement.tableInsert(table);
            tableManagement.tableUpdate(table);
            fail("Failed to catch exception when updating  null id");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testTableUpdateNullId test");
        }
    }
        
    /**
     * Test of tableUpdate method, of class TableManagementImpl.
     */
    @Test
    public void testTableUpdateInvalidIdt() {
        try {
            codID = tableManagement.tableInsert(table);
            table.setIdtStatus('P');
            table.setCodID(codID);
            tableManagement.tableUpdate(table);
            fail("Failed to catch exception when updating null idt");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testTableInsertInvalidIdt test");
        }
    }

    /**
     * Test of tableRemove method, of class TableManagementImpl.
     */
    @Test
    public void testTableRemove() {
        try {
            codID = tableManagement.tableInsert(table);
            tableManagement.tableRemove(codID);
            codID = -1L;
            System.out.println("Correctly removed table");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to remove correct table");
        }
    }

    /**
     * Test of getTableByID method, of class TableManagementImpl.
     */
    @Test
    public void testGetTableByID() {
        try {
            codID = tableManagement.tableInsert(table);
            Table newTable = tableManagement.getTableByID(codID);
            if (newTable.getCodID() != codID) {
                fail("Failed to retrieve correct table");
            }
            System.out.println("Correctly retrieved table");
        } catch (BusinessException | PersistenceException ex) {
            ex.printStackTrace();
            fail("Failed to retrieve correct table");
            
        }
    }

    /**
     * Test of containsThisTableID method, of class TableManagementImpl.
     */
    @Test
    public void testThisTableIDExists() {
        try {
            codID = tableManagement.tableInsert(table);
            List list = tableManagement.listAll();
            if (list.isEmpty()) {
                fail("Failed to retrieve correct table");
            }
            System.out.println("Correctly retrieved table");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to retrieve correct table");
        }
    }
    
}
