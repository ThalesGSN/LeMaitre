/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.ImageDAO;
import br.cefetmg.LeMaitre.model.domain.Image;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;

/**
 *
 * @author thales
 */
public class ImageManagementImpl implements ImageManagement {
    private ImageDAO DAO;

    public ImageManagementImpl(ImageDAO DAO) {
        this.DAO = DAO;
    }

    @Override
    public Long imageInsert(Image image) throws BusinessException, PersistenceException {
        if(image == null)
            throw new BusinessException(BusinessException.NULL_INSERT_OBJECT, "Null bill cannot be inserted.");
        
        if(image.getLocation() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "Location cannot be null.");
        
        if(image.getLocation().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "Location cannot be empty.");
        
        return DAO.insert(image);
    }

    @Override
    public boolean imageUpdate(Image image) throws BusinessException, PersistenceException {
        if(image == null)
            throw new BusinessException(BusinessException.NULL_INSERT_OBJECT, "Null bill cannot be inserted.");
        
        if(image.getCodImage() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "Null codImage cannot be inserted.");
        
        if(image.getLocation() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "Location cannot be null.");
        
        if(image.getLocation().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "Location cannot be empty.");
        
        return DAO.update(image);
    }

    @Override
    public boolean imageRemove(Long imageID) throws PersistenceException {
        if(imageID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Null codImage cannot be removed.");
        
        return DAO.remove(imageID);
    }

    @Override
    public Image getImageByID(Long imageID) throws PersistenceException {
        if(imageID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Null codImage cannot be used to get Image.");
        
        return DAO.getImageByID(imageID);
    }

    @Override
    public boolean containsThisImageID(Long imageID) throws PersistenceException {
        if(imageID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Null codImage cannot be used to get Image.");
        
        return DAO.containsThisImageID(imageID);
    }
    
    
}
