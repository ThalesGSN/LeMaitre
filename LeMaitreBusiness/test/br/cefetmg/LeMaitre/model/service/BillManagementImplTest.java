/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.BillDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Bill;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.sql.Date;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thalesgsn
 */
public class BillManagementImplTest {
    
    private Long codID;
    
    private Date datUse;
    
    private Date date;

    private char idtStatus;
    
    private Bill bill;
    
    private BillManagement billManagement;
    
    public BillManagementImplTest() {
        date = new Date(10,02,2000);
        billManagement = new BillManagementImpl(BillDAOImpl.getInstance());
        bill = new Bill(datUse, idtStatus);
    }
        
    @Before
    public void setUp() {
        datUse = date;
        idtStatus = 'O';
        codID = -1L;
        bill.setDatUse(datUse);
        bill.setIdtStatus(idtStatus);
        bill.setCodID(null);
    }
    
    @After
    public void tearDown() {
        try {
            if (codID != -1L) {
                billManagement.billRemove(codID);
            }
        } catch (PersistenceException ex) {
            System.out.println("Failed to remove bill after test");
        }
    }

    /**
     * Test of billInsert method, of class BillManagementImpl.
     */
    @Test
    public void testBillInsert() {
        try {
            codID = billManagement.billInsert(bill);
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to insert correct bill");
        }
        System.out.println("Passed testBillInsert test");
    }
    
    /**
     * Test of billInsert method, of class BillManagementImpl.
     */
    @Test
    public void testBillInsertNull() {
        try {
            bill = null;
            codID = billManagement.billInsert(bill);
            fail("Failed to catch exception when inserting null bill");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testBillInsertNull test");
            
        }
    }
    
    /**
     * Test of billInsert method, of class BillManagementImpl.
     */
    @Test
    public void testBillInsertNullDatUse() {
        try {
            bill.setDatUse(null);
            codID = billManagement.billInsert(bill);
            fail("Failed to catch exception when inserting null date");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testBillInsertNullDatUse test");
        }
        
    }
    
    /**
     * Test of billInsert method, of class BillManagementImpl.
     */
    @Test
    public void testBillInsertInvalidIdt() {
        try {
            bill.setIdtStatus('R');
            codID = billManagement.billInsert(bill);
            fail("Failed to catch exception when inserting null idt");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testBillInsertInvalidIdt test");
        }
    }

    /**
     * Test of billUpdate method, of class BillManagementImpl.
     */
    @Test
    public void testBillUpdate() {
        try {
            codID = billManagement.billInsert(bill);
            bill.setCodID(codID);
            billManagement.billUpdate(bill);
            System.out.println("Passed testBillUpdate test");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to update correct bill");
        }
    }
    
    /**
     * Test of billUpdate method, of class BillManagementImpl.
     */
    @Test
    public void testBillUpdateNullId() {
        try {
            codID = billManagement.billInsert(bill);
            billManagement.billUpdate(bill);
            fail("Failed to catch exception when updating  null id");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testBillUpdateNullId test");
        }
    }
    
    /**
     * Test of billUpdate method, of class BillManagementImpl.
     */
    @Test
    public void testBillUpdateNullDatUse() {
        try {
            codID = billManagement.billInsert(bill);
            bill.setDatUse(null);
            bill.setCodID(codID);
            billManagement.billUpdate(bill);
            fail("Failed to catch exception when updating null date");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testBillUpdateNullDatUse test");
        }
    }
    
    /**
     * Test of billUpdate method, of class BillManagementImpl.
     */
    @Test
    public void testBillUpdateInvalidIdt() {
        try {
            codID = billManagement.billInsert(bill);
            bill.setIdtStatus('R');
            bill.setCodID(codID);
            billManagement.billUpdate(bill);
            fail("Failed to catch exception when updating invalid idt");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testBillInsertInvalidIdt test");
        }
    }

    /**
     * Test of billRemove method, of class BillManagementImpl.
     */
    @Test
    public void testBillRemove() {
        try {
            codID = billManagement.billInsert(bill);
            billManagement.billRemove(codID);
            codID = -1L;
            System.out.println("Correctly removed bill");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to remove correct bill");
        }
    }

    /**
     * Test of getBillByID method, of class BillManagementImpl.
     */
    @Test
    public void testGetBillByID() {
        try {
            codID = billManagement.billInsert(bill);
            Bill newBill = billManagement.getBillByID(codID);
            if (newBill.getCodID() != codID) {
                fail("Failed to retrieve correct bill");
            }
            System.out.println("Correctly retrieved bill");
        } catch (BusinessException | PersistenceException ex) {
            ex.printStackTrace();
            fail("Failed to retrieve correct bill");
            
        }
    }

    /**
     * Test of containsThisBillID method, of class BillManagementImpl.
     */
    @Test
    public void testThisBillIDExists() {
        try {
            codID = billManagement.billInsert(bill);
            List list = billManagement.listAll();
            if (list.isEmpty()) {
                fail("Failed to retrieve correct bill");
            }
            System.out.println("Correctly retrieved bill");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to retrieve correct bill");
        }
    }
    
}
