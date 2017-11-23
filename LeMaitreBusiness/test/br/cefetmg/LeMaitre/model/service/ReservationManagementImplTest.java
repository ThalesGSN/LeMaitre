/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.ReservationDAOImpl;
import br.cefetmg.LeMaitre.model.dao.TableDAOImpl;
import br.cefetmg.LeMaitre.model.domain.Reservation;
import br.cefetmg.LeMaitre.model.domain.Table;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.sql.Time;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
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
public class ReservationManagementImplTest {
    
    private boolean inserted;
    
    private Integer codIDTable;
    
    private Integer nroPersons;
    
    private String txtContactName;

    private String txtTelephone;

    private String txtCellphone;

    private Date datReservation;
    
    private Date date;

    private Time datHourReservation;
    
    private Time time;
    
    private Reservation reservation;
    
    private ReservationManagement reservationManagement;
    
    private TableManagement tableManagement = new TableManagementImpl(TableDAOImpl.getInstance());
    
    public ReservationManagementImplTest() {
        try {
            codIDTable = tableManagement.tableInsert(new Table('O', 3));
        } catch (BusinessException | PersistenceException ex) {
            Logger.getLogger(ReservationManagementImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        nroPersons = 4;
        txtContactName = "Paula";
        txtCellphone = "999999999";
        txtTelephone = "333333333";
        LocalDateTime now = LocalDateTime.now();
        date = new Date(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        datReservation = date;
        time = new Time(now.getHour(), now.getMinute(), now.getSecond());
        datHourReservation = time;
        reservation = new Reservation(codIDTable, datReservation, datHourReservation, nroPersons, txtContactName, txtTelephone, txtCellphone);
        reservationManagement = new ReservationManagementImpl(ReservationDAOImpl.getInstance());
    }
        
    @Before
    public void setUp() {
        inserted = false;
        reservation.setCodIDTable(codIDTable);
        reservation.setNroPersons(nroPersons);
        reservation.setTxtContactName(txtContactName);
        reservation.setTxtCellphone(txtCellphone);
        reservation.setTxtTelephone(txtTelephone);
        reservation.setDatReservation(datReservation);
        reservation.setDatHourReservation(datHourReservation);
    }
    
    @After
    public void tearDown() {
        try {
            if (inserted) {
                reservationManagement.reservationRemove(codIDTable, datReservation, datHourReservation);
            }
        } catch (PersistenceException ex) {
            System.out.println("Failed to remove reservation after test");
        }
    }

    /**
     * Test of reservationInsert method, of class ReservationManagementImpl.
     */
    @Test
    public void testReservationInsert() {
        try {
            reservationManagement.reservationInsert(reservation);
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to insert correct reservation");
            return;
        }
        inserted = true;
        System.out.println("Passed testReservationInsert test");
    }
    
    /**
     * Test of reservationInsert method, of class ReservationManagementImpl.
     */
    @Test
    public void testReservationInsertNullTableId() {
        try {
            reservation.setCodIDTable(null);
            reservationManagement.reservationInsert(reservation);
            fail("Failed to catch exception when inserting null table id");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testReservationInsertNull test");
            
        }
    }
    
    /**
     * Test of reservationInsert method, of class ReservationManagementImpl.
     */
    @Test
    public void testReservationInsertNullCellphone() {
        try {
            reservation.setTxtCellphone(null);
            reservationManagement.reservationInsert(reservation);
            fail("Failed to catch exception when inserting null cellphone");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testReservationInsertNull test");
            
        }
    }
    
    /**
     * Test of reservationInsert method, of class ReservationManagementImpl.
     */
    @Test
    public void testReservationInsertNullContactName() {
        try {
            reservation.setTxtContactName(null);
            reservationManagement.reservationInsert(reservation);
            fail("Failed to catch exception when inserting null contact name");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testReservationInsertNull test");
            
        }
    }
    
    /**
     * Test of reservationInsert method, of class ReservationManagementImpl.
     */
    @Test
    public void testReservationInsertNull() {
        try {
            reservation = null;
            reservationManagement.reservationInsert(reservation);
            fail("Failed to catch exception when inserting null reservation");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testReservationInsertNull test");
            
        }
    }
    
    /**
     * Test of reservationUpdate method, of class ReservationManagementImpl.
     */
    @Test
    public void testReservationUpdate() {
        try {
            reservationManagement.reservationInsert(reservation);
            inserted = true;
            reservationManagement.reservationUpdate(reservation);
            System.out.println("Passed testReservationUpdate test");
        } catch (BusinessException | PersistenceException ex) {
            ex.printStackTrace();
            fail("Failed to update correct reservation");
        }
    }
    
    /**
     * Test of reservationUpdate method, of class ReservationManagementImpl.
     */
    @Test
    public void testReservationUpdateNullTableId() {
        try {
            reservationManagement.reservationInsert(reservation);
            reservation.setCodIDTable(null);
            inserted = true;
            reservationManagement.reservationUpdate(reservation);
            fail("Failed to catch exception when updating  null id");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testReservationUpdateNullId test");
        }
    }
    
    /**
     * Test of reservationUpdate method, of class ReservationManagementImpl.
     */
    @Test
    public void testReservationUpdateNullCellphone() {
        try {
            reservationManagement.reservationInsert(reservation);
            reservation.setTxtCellphone(null);
            reservationManagement.reservationUpdate(reservation);
            inserted = true;
            fail("Failed to catch exception when inserting null cellphone");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testReservationInsertNull test");
            
        }
    }
    
    /**
     * Test of reservationUpdate method, of class ReservationManagementImpl.
     */
    @Test
    public void testReservationUpdateNullContactName() {
        try {
            reservationManagement.reservationInsert(reservation);
            reservation.setTxtContactName(null);
            inserted = true;
            reservationManagement.reservationUpdate(reservation);
            fail("Failed to catch exception when inserting null contact name");
        } catch (BusinessException | PersistenceException ex) {
            System.out.println("Passed testReservationInsertNull test");
            
        }
    }
            
    /**
     * Test of reservationRemove method, of class ReservationManagementImpl.
     */
    @Test
    public void testReservationRemove() {
        try {
            reservationManagement.reservationInsert(reservation);
            reservationManagement.reservationRemove(codIDTable, datReservation, datHourReservation);
            System.out.println("Correctly removed reservation");
        } catch (BusinessException | PersistenceException ex) {
            inserted = true;
            ex.printStackTrace();
            fail("Failed to remove correct reservation");
        }
    }

    /**
     * Test of getReservationByID method, of class ReservationManagementImpl.
     */
    @Test
    public void testGetReservationByID() {
        try {
            reservationManagement.reservationInsert(reservation);
            Reservation newReservation = reservationManagement.getReservationByID(codIDTable, datReservation, datHourReservation);
            if (newReservation.getCodIDTable() != codIDTable) {
                fail("Failed to retrieve correct reservation");
            }
            System.out.println("Correctly retrieved reservation");
        } catch (BusinessException | PersistenceException ex) {
            ex.printStackTrace();
            fail("Failed to retrieve correct reservation");
            
        }
    }

    /**
     * Test of listAll method, of class ReservationManagementImpl.
     */
    @Test
    public void testListAll() {
        try {
            reservationManagement.reservationInsert(reservation);
            List list = reservationManagement.listAll();
            if (list.isEmpty()) {
                fail("Failed to retrieve correct reservation");
            }
            System.out.println("Correctly retrieved reservation");
        } catch (BusinessException | PersistenceException ex) {
            fail("Failed to retrieve correct reservation");
        }
    }
    
}
