/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.Reservation;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Temp
 */
public interface ReservationManagement {
    public Long reservationInsert(Reservation reservation) throws BusinessException, PersistenceException;
    public boolean reservationUpdate(Reservation reservation) throws BusinessException, PersistenceException;
    public boolean reservationRemove(Long COD_questao, Long COD_discussao, Long reservationSeq) throws PersistenceException;
    public Reservation getreservationByID(Long COD_questao, Long COD_discussao, Long reservationSeq) throws PersistenceException;
    public List<Reservation> getCommentariesByForumId(Long forumId, Long questionId) throws PersistenceException;
}
