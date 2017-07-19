/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Reservation;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public interface ReservationDAO {
    public Long insert(Reservation reservation) throws BusinessException, PersistenceException;
    public boolean update(Reservation reservation) throws BusinessException, PersistenceException;
    public boolean remove(Integer tableID, Date datReservation, Time hourReservation) throws PersistenceException;
    public Reservation getReservationByID(Integer tableID, Date datReservation, Time hourReservation) throws PersistenceException;
    public List<Reservation> listReservationByTableID(Integer tableID) throws PersistenceException;
    
}
