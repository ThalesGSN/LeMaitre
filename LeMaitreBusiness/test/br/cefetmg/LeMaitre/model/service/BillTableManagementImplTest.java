/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.BillDAOImpl;
import br.cefetmg.LeMaitre.model.dao.BillTableDAO;
import br.cefetmg.LeMaitre.model.dao.BillTableDAOImpl;
import br.cefetmg.LeMaitre.model.dao.TableDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Bill;
import br.cefetmg.LeMaitre.model.domain.BillTable;
import br.cefetmg.LeMaitre.model.domain.Table;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thalesgsn
 */
public class BillTableManagementImplTest {
    private boolean inserted;
    
    private final BillTableDAO DAO;
    private final BillTableManagement management;
    private final BillManagement billManagement = new BillManagementImpl(BillDAOImpl.getInstance());
    private final TableManagement tableManagement= new TableManagementImpl(TableDAOImpl.getInstance());
    
    private final Bill bill;
    private Long codBill;
   
    
    private final Table table;
    private Long codTable;
    
    private final BillTable billTable;
   
    
    public BillTableManagementImplTest() {
        DAO = BillTableDAOImpl.getInstance();
        management = new BillTableManagementImpl(DAO);
        bill = new Bill(new Date(10,10,2010), 'O');
        table = new Table('O', 4);
        try{
           codBill = billManagement.billInsert(bill);
           codTable = tableManagement.tableInsert(table);
        } catch(BusinessException | PersistenceException ex){
            Logger.getLogger(BillTableManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        billTable = new BillTable();
    }
    

    @Before
    public void setUp() {
        inserted = false;
        billTable.setCodIDBill(codBill);
        billTable.setCodIDTable(codTable);
    }
    
    @After
    public void tearDown() throws PersistenceException {
        try {
            if(inserted){
                management.billTableRemove(codBill, codTable);
            }
        } catch (PersistenceException ex) {
            System.out.println("Failed to remove billTable after test");
        }
    }

    /**
     * Test of billTableInsert method, of class BillTableManagementImpl.
     */
    @Test
    public void testBillTableInsert() throws Exception {
        try {
            management.billTableInsert(billTable);
            
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to insert correct billTable");
        }
    }

    @Test
    public void testBillTableNullInsert() throws Exception {
        try {
            management.billTableInsert(null);
          fail("Failed to catch exception when updating null id");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testCategoryUpdateNullId test");
        }
    }
    
    @Test
    public void testBillTableNullBillIDInsert() throws Exception {
        try {
            billTable.setCodIDBill(null);
            management.billTableInsert(billTable);
          fail("Failed to catch exception when updating null id");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testCategoryUpdateNullId test");
        }
    }
    
    @Test
    public void testBillTableNullTableIDInsert() throws Exception {
        try {
            billTable.setCodIDTable(null);
            management.billTableInsert(billTable);
          fail("Failed to catch exception when updating null id");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testCategoryUpdateNullId test");
        }
    }
    /**
     * Test of billTableInsert method, of class BillTableManagementImpl.
     */
    @Test
    public void testBillTableRemove() throws Exception {
        try {
            System.err.println("codBill:" + codBill + "\ncodTable: " + codTable);
            management.billTableRemove(codBill, codTable);
            
        } catch ( PersistenceException ex) {
            fail("Failed to remove correct billTable");
        }
    }
    /**
     * Test of billTableRemove method, of class BillTableManagementImpl.
     */
    @Test
    public void testBillTableRemoveNullBillID() throws Exception {
        try {
            management.billTableRemove(null, codTable);
          fail("Failed to catch exception when removing");
        } catch ( PersistenceException ex) {
            System.out.println("Passed testCategoryUpdateNullId test");
        }
    }
    
    /**
     * Test of billTableRemove method, of class BillTableManagementImpl.
     */
    @Test
    public void testBillTableRemoveNullTableID() throws Exception {
        try {
            management.billTableRemove(codBill, null);
          fail("Failed to catch exception when removing");
        } catch ( PersistenceException ex) {
            System.out.println("Passed testCategoryUpdateNullId test");
        }
    }

    /**
     * Test of getBillsByTableID method, of class BillTableManagementImpl.
     */
    @Test
    public void testGetBillsByNullTableID() throws Exception {
        try {
            management.getBillsByTableID(null);
            fail("Failed to catch exception when geting bills");
        } catch ( PersistenceException ex) {
            System.out.println("Passed testGetBillsByNullTableID test");
        }
    }

    /**
     * Test of getTablesByBillID method, of class BillTableManagementImpl.
     */
    @Test
    public void testGetTablesByBillID() throws Exception {
        try {
            
            management.getTablesByBillID(codBill);    
        } catch (PersistenceException ex) {
            fail("Failed to update correct billTable");
        }
    }
    
    /**
     * Test of getBillsByTableID method, of class BillTableManagementImpl.
     */
    @Test
    public void testGetTablesByNullBillID() throws Exception {
        try {
            management.getTablesByBillID(null);
            fail("Failed to catch exception when geting bills");
        } catch ( PersistenceException ex) {
            System.out.println("Passed testGetBillsByNullTableID test");
        }
    }
    
}
