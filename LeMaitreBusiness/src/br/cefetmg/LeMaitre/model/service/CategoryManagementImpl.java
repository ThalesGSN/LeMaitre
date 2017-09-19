/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.CategoryDAO;
import br.cefetmg.LeMaitre.model.domain.Category;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public class CategoryManagementImpl implements CategoryManagement {
    CategoryDAO DAO;

    public CategoryManagementImpl(CategoryDAO DAO) {
        this.DAO = DAO;
    }
    
    @Override
    public Long categoryInsert(Category category) throws BusinessException, PersistenceException {
        if(category == null)
            throw new BusinessException(BusinessException.NULL_INSERT_OBJECT, "Null category cannot be inserted.");
        
        if(category.getNomCategory() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "nomCategory cannot be null.");
        
        if(category.getNomCategory().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "nomCategory cannot be empty.");
        
        return DAO.insert(category);
    }

    @Override
    public boolean categoryUpdate(Category category) throws BusinessException, PersistenceException {
        if(category == null)
            throw new BusinessException(BusinessException.NULL_INSERT_OBJECT, "Null category cannot be inserted.");
        
        if(category.getSeqCategory() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "seqCategory cannot be null.");

        if(category.getNomCategory() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "nomCategory cannot be null.");
        
        if(category.getNomCategory().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "nomCategory cannot be empty.");
        
        return DAO.update(category);
    }

    @Override
    public boolean categoryRemove(Long categoryID) throws PersistenceException {
        if(categoryID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "categoryID cannot be null.");
        
        return DAO.remove(categoryID);
    }

    @Override
    public Category getCategoryByID(Long categoryID) throws PersistenceException {
        if(categoryID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "categoryID cannot be null.");
        
        return DAO.getCategoryByID(categoryID);
    }

    
    @Override
    public boolean containsThisCategoryID(Long categoryID) throws PersistenceException {
         if(categoryID == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "categoryID cannot be null.");
        
        return DAO.containsThisCategoryID(categoryID);
    }
    
    @Override
    public List<Category> listAll() throws PersistenceException {
        return DAO.listAllCategories();
    }
}
