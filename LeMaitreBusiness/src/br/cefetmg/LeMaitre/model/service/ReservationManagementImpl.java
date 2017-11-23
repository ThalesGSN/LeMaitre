/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.ReservationDAO;
import br.cefetmg.LeMaitre.model.dao.TableDAOImpl;
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
public class ReservationManagementImpl implements ReservationManagement {
    private ReservationDAO DAO;
    private final TableManagement tableManagement;

    public ReservationManagementImpl(ReservationDAO DAO) {
        this.DAO = DAO;
        tableManagement = new TableManagementImpl(TableDAOImpl.getInstance());
    }
    
    @Override
    public void reservationInsert(Reservation reservation) throws BusinessException, PersistenceException {
        if(reservation == null)
            throw new BusinessException(BusinessException.NULL_INSERT_OBJECT, "Null reservation cannot be inserted.");
        
        if(reservation.getCodIDTable() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "codIDTable cannot be null.");
        
        if(!tableManagement.containsThisTableID(reservation.getCodIDTable()))
            throw new BusinessException(BusinessException.INVALID_FOREING_KEY, "tableID doesn't exist in the persistence");
        
        if(reservation.getDatReservation() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "DatReservation cannot be null.");
        
        if(reservation.getDatHourReservation()== null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "HourReservation cannot be null.");
        
        if(reservation.getNroPersons() < 0)
            throw new BusinessException(BusinessException.INVALID_PARAMETER, "VlrPrice cannot be negative");
        
        if(reservation.getTxtContactName() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "ContactName cannot be null.");
        
        if(reservation.getTxtContactName().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "ContactName cannot be empty.");
        
        if(reservation.getTxtCellphone()== null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "cellphone cannot be null.");
        
        if(reservation.getTxtCellphone().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "Cellphone cannot be empty.");
        
        DAO.insert(reservation);
    }

    @Override
    public boolean reservationUpdate(Reservation reservation) throws BusinessException, PersistenceException {
        if(reservation == null)
            throw new BusinessException(BusinessException.NULL_INSERT_OBJECT, "Null reservation cannot be inserted.");
        
        if(reservation.getCodIDTable() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "codIDTable cannot be null.");
        
        if(!tableManagement.containsThisTableID(reservation.getCodIDTable()))
            throw new BusinessException(BusinessException.INVALID_FOREING_KEY, "tableID doesn't exist in the persistence");
        
        if(reservation.getDatReservation() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "DatReservation cannot be null.");
        
        if(reservation.getDatHourReservation()== null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "HourReservation cannot be null.");
        
        if(reservation.getNroPersons() < 0)
            throw new BusinessException(BusinessException.INVALID_PARAMETER, "VlrPrice cannot be negative");
        
        if(reservation.getTxtContactName() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "ContactName cannot be null.");
        
        if(reservation.getTxtContactName().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "ContactName cannot be empty.");
        
        if(reservation.getTxtCellphone()== null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "cellphone cannot be null.");
        
        if(reservation.getTxtCellphone().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "Cellphone cannot be empty.");
        
        return DAO.update(reservation);
    }

    @Override
    public boolean reservationRemove(Integer tableID, Date datReservation, Time hourReservation)  throws PersistenceException {
        if(tableID == null || datReservation == null || hourReservation == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "reservationID cannot be null.");
        
        return DAO.remove(tableID, datReservation, hourReservation);
    }

    @Override
    public Reservation getReservationByID(Integer tableID, Date datReservation, Time hourReservation)  throws PersistenceException {
        if(tableID == null || datReservation == null || hourReservation == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "None of parameters can be null.");
        
        return DAO.getReservationByID(tableID, datReservation, hourReservation);
    }

    
    @Override
    public List<Reservation> getReservationsByTableID(Integer tableID) throws PersistenceException {
        if(tableID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "None of parameters can be null.");
        
        return DAO.listReservationByTableID(tableID);
    }

    @Override
    public List<Reservation> listAll() throws PersistenceException {
        return DAO.listAll();
    }

    @Override
    public List<Reservation> listReservationByTableIDWithinSevenDays(Integer tableID) throws PersistenceException {
        if(tableID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "None of parameters can be null.");
        
        return DAO.listReservationByTableIDWithinSevenDays(tableID);
    }

    @Override
    public List<Reservation> listAllWithinSevenDays() throws PersistenceException {
        return DAO.listAllWithinSevenDays();
    }

    @Override
    public List<Reservation> getReservationsByDate(Date datReservation) throws PersistenceException {
        if(datReservation == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "None of parameters can be null.");
        
        return DAO.getReservationsByDate(datReservation);
    }
    
    
}
