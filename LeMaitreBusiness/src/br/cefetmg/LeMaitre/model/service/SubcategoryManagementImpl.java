/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.SubcategoryDAO;
import br.cefetmg.LeMaitre.model.domain.Subcategory;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;

/**
 *
 * @author thales
 */
public class SubcategoryManagementImpl implements SubcategoryManagement{
    private SubcategoryDAO DAO;

    public SubcategoryManagementImpl(SubcategoryDAO DAO) {
        this.DAO = DAO;
    }

    @Override
    public Integer subcategoryInsert(Subcategory subcategory) throws BusinessException, PersistenceException {
        if(subcategory == null)
            throw new BusinessException(BusinessException.NULL_INSERT_OBJECT, "Null bill cannot be inserted.");
        
        if(subcategory.getSeqCategory() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "seqCategory cannot be null.");
        
        if(subcategory.getNomSubcategory()== null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "nomSubcategory cannot be null.");
        
        if(subcategory.getNomSubcategory().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "nomSubcategory cannot be empty.");
        
        return DAO.insert(subcategory);
    }

    @Override
    public boolean subcategoryUpdate(Subcategory subcategory) throws BusinessException, PersistenceException {
        if(subcategory == null)
            throw new BusinessException(BusinessException.NULL_INSERT_OBJECT, "Null bill cannot be inserted.");
        
        if(subcategory.getSeqCategory() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "seqCategory cannot be null.");
        
        if(subcategory.getSeqSubcategory() == null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "seqSubcategory cannot be null.");
        
        if(subcategory.getNomSubcategory()== null)
            throw new BusinessException(BusinessException.NOTNULL_ATRIBUTE_ISNULL, "nomSubcategory cannot be null.");
        
        if(subcategory.getNomSubcategory().isEmpty())
            throw new BusinessException(BusinessException.EMPTY_STRING, "nomSubcategory cannot be empty.");
        
        return DAO.update(subcategory);
    }

    @Override
    public boolean subcategoryRemove(Integer categorySEQ, Integer subcategorySEQ) throws PersistenceException {
        if(categorySEQ == null || subcategorySEQ == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters can't be null.");
        
        return DAO.remove(categorySEQ, subcategorySEQ);
    }

    @Override
    public Subcategory getSubcategoryByID(Integer categorySEQ, Integer subcategorySEQ) throws PersistenceException {
        if(categorySEQ == null || subcategorySEQ == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters can't be null.");
        
        return DAO.getSubcategoryByID(categorySEQ, subcategorySEQ);
    }

    @Override
    public boolean containsThisSubcategoryID(Integer categorySEQ, Integer subcategorySEQ) throws PersistenceException {
        if(categorySEQ == null || subcategorySEQ == null)
            throw new PersistenceException(PersistenceException.PARAMETER_ISNULL, "Parameters can't be null.");
        
        return DAO.containsThisSubcategoryID(categorySEQ, subcategorySEQ);
    }


    
}
