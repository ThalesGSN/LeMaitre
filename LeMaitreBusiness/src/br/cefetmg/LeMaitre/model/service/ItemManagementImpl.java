/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.CategoryDAOImpl;
import br.cefetmg.LeMaitre.model.dao.ItemDAO;
import br.cefetmg.LeMaitre.model.domain.Item;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public class ItemManagementImpl implements ItemManagement {
    ItemDAO DAO;
    private final CategoryManagement categoryManagement;

    public ItemManagementImpl(ItemDAO DAO) {
        this.DAO = DAO;
        categoryManagement = new CategoryManagementImpl(CategoryDAOImpl.getInstance());
    }
    
    @Override
    public Integer itemInsert(Item item) throws BusinessException, PersistenceException {
        if(item == null)
            throw new BusinessException(BusinessException.NULL_INSERTED_OBJECT, "Null item cannot be inserted.");
        
        if(item.getVlrPrice() < 0)
            throw new BusinessException(BusinessException.INVALID_PARAMETER, "VlrPrice cannot be negative");

        if(item.getNomItem() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "nomItem cannot be null.");
        
        if(item.getNomItem().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "nomItem cannot be empty.");
        
        if(item.getDesItem() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "desItem cannot be null.");
        
        if(item.getDesItem().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "desItem cannot be empty.");
        
        if(String.copyValueOf(Item.IDT_AVALIABLE_POSSIBLE_VALUES)
                .indexOf(item.getIdtAvaliable()) < 0){
            throw new BusinessException(BusinessException.INVALID_IDT_VALUE, "idtAvaliable invalid value.");
        }
        
        if(item.getCodCategory() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "codItem cannot be null.");
        
        if(!categoryManagement.thisCategoryIDExists(item.getCodCategory()))
            throw new BusinessException(BusinessException.INVALID_FOREING_KEY, "codCategory doesn't exist in the persistence");
        
        return DAO.insert(item);
    }

    @Override
    public boolean itemUpdate(Item item) throws BusinessException, PersistenceException {
        if(item == null)
            throw new BusinessException(BusinessException.NULL_INSERTED_OBJECT, "Null item cannot be inserted.");
        
        if(item.getCodItem() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "codItem cannot be null.");
        
        if(item.getVlrPrice() < 0)
            throw new BusinessException(BusinessException.INVALID_PARAMETER, "VlrPrice cannot be negative");

        if(item.getNomItem() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "nomItem cannot be null.");
        
        if(item.getNomItem().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "nomItem cannot be empty.");
        
        if(item.getDesItem() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "desItem cannot be null.");
        
        if(item.getDesItem().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "desItem cannot be empty.");
        
        if(String.copyValueOf(Item.IDT_AVALIABLE_POSSIBLE_VALUES)
                .indexOf(item.getIdtAvaliable()) < 0){
            throw new BusinessException(BusinessException.INVALID_IDT_VALUE, "idtAvaliable invalid value.");
        }
        
        if(item.getCodCategory() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "codItem cannot be null.");
        
        if(!categoryManagement.thisCategoryIDExists(item.getCodCategory()))
            throw new BusinessException(BusinessException.INVALID_FOREING_KEY, "codCategory doesn't exist in the persistence");
        
        return DAO.update(item);
    }

    @Override
    public boolean itemRemove(Integer itemID) throws PersistenceException {
        if(itemID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "itemID cannot be null.");
        
        return DAO.remove(itemID);
    }

    @Override
    public Item getItemByID(Integer itemID) throws PersistenceException {
        if(itemID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "itemID cannot be null.");
        
        return DAO.getItemByID(itemID);
    }

    @Override
    public boolean thisItemIDExists(Integer itemID) throws PersistenceException {
        if(itemID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "itemID cannot be null.");
        
        return DAO.thisItemIDExists(itemID);
    }

    @Override
    public List<Item> getAllItems() throws PersistenceException {
        return DAO.listAllItems();
    }
    
}
