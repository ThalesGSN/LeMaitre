/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.service;

import br.cefetmg.LeMaitre.model.domain.Category;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public interface CategoryManagement {
    public Integer categoryInsert(Category category) throws BusinessException, PersistenceException;
    public boolean categoryUpdate(Category category) throws BusinessException, PersistenceException;
    public boolean categoryRemove(Integer categorySEQ) throws PersistenceException;
    public Category getCategoryByID(Integer categorySEQ) throws PersistenceException;
    public boolean thisCategoryIDExists(Integer categoryID) throws PersistenceException;
    public List<Category> listAllCategories() throws PersistenceException;
}
