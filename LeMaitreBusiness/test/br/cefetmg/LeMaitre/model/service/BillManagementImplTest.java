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
    
    private String Token;

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
        Token = null;
        bill.setDatUse(datUse);
        bill.setIdtStatus(idtStatus);
        bill.setCodToken(null);
    }
    
    @After
    public void tearDown() {
        try {
            if (Token != null) {
                billManagement.billRemove(Token);
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
            Token = billManagement.billInsert(bill);
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
            Token = billManagement.billInsert(bill);
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
            Token = billManagement.billInsert(bill);
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
            Token = billManagement.billInsert(bill);
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
            Token = billManagement.billInsert(bill);
            bill.setCodToken(Token);
            billManagement.billUpdate(bill);
            System.out.println("Passed testBillUpdate test");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            fail("Failed to update correct bill");
        }
    }
    
    /**
     * Test of billUpdate method, of class BillManagementImpl.
     */
    @Test
    public void testBillUpdateNullId() {
        try {
            Token = billManagement.billInsert(bill);
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
            Token = billManagement.billInsert(bill);
            bill.setDatUse(null);
            bill.setCodToken(Token);
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
            Token = billManagement.billInsert(bill);
            bill.setIdtStatus('R');
            bill.setCodToken(Token);
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
            Token = billManagement.billInsert(bill);
            billManagement.billRemove(Token);
            Token = null;
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
            Token = billManagement.billInsert(bill);
            Bill newBill = billManagement.getBillByID(Token);
            if (newBill.getCodToken() == null ? Token != null : !newBill.getCodToken().equals(Token)) {
                fail("Failed to retrieve correct bill");
            }
            System.out.println("Correctly retrieved bill");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to retrieve correct bill");
            
        }
    }

    /**
     * Test of listAll method, of class BillManagementImpl.
     */
    @Test
    public void testListAll() {
        try {
            Token = billManagement.billInsert(bill);
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
