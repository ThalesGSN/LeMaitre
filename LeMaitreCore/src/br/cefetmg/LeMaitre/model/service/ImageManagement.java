/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.*;
import br.cefetmg.LeMaitre.model.domain.Image;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;

/**
 *
 * @author thales
 */
public interface ImageManagement {
    public Long imageInsert(Image image) throws BusinessException, PersistenceException;
    public boolean imageUpdate(Image image) throws BusinessException, PersistenceException;
    public boolean imageRemove(Long imageID) throws PersistenceException;
    public Image getImageByID(Long imageID) throws PersistenceException;
    public boolean containsThisImageID(Long imageID) throws PersistenceException;
}
