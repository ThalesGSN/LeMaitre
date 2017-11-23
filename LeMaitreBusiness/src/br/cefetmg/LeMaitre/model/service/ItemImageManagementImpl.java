/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.ItemImageDAO;
import br.cefetmg.LeMaitre.model.domain.Image;
import br.cefetmg.LeMaitre.model.domain.ItemImage;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author thales
 */
public class ItemImageManagementImpl implements ItemImageManagement{
    private ItemImageDAO DAO;

    public ItemImageManagementImpl(ItemImageDAO DAO) {
        this.DAO = DAO;
    }

    @Override
    public boolean itemImageInsert(ItemImage itemImage) throws BusinessException, PersistenceException {
        if(itemImage == null)
            throw new BusinessException(BusinessException.NULL_INSERT_OBJECT, "Null itemImage cannot be inserted.");
        
        if(itemImage.getCodItem() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "codItem cannot be null.");
        
        if(itemImage.getCodImage() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "codImage cannot be null.");
        
        return DAO.insert(itemImage);
    }

    @Override
    public boolean itemImageRemove(Integer itemID, Long imageID) throws PersistenceException {
        if(itemID == null || imageID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "None of parameters can be null.");
        
        return DAO.remove(itemID, imageID);
    }

    @Override
    public List<Image> getImagesByItemID(Integer itemID) throws PersistenceException {
        if(itemID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "itenID cann't be null.");
        
        return DAO.getImagesByItemID(itemID);
    }
    
}
