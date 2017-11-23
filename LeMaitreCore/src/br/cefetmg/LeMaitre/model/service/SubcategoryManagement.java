/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.dao.*;
import br.cefetmg.LeMaitre.model.domain.Subcategory;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author thales
 */
public interface SubcategoryManagement {
    public Integer subcategoryInsert(Subcategory subcategory) throws BusinessException, PersistenceException;
    public boolean subcategoryUpdate(Subcategory subcategory) throws BusinessException, PersistenceException;
    public boolean subcategoryRemove(Integer categorySEQ, Integer subcategorySEQ) throws PersistenceException;
    public Subcategory getSubcategoryByID(Integer categorySEQ, Integer subcategorySEQ) throws PersistenceException;
    public boolean containsThisSubcategoryID(Integer categorySEQ, Integer subcategorySEQ) throws PersistenceException;
}
