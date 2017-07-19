/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Category;
import br.cefetmg.LeMaitre.model.exception.BusinessException;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;

/**
 *
 * @author Thalesgsn
 */
public interface CategoryDAO {
    public Long insert(Category category) throws BusinessException, PersistenceException;
    public boolean update(Category category) throws BusinessException, PersistenceException;
    public boolean remove(Integer categoryID) throws PersistenceException;
    public Category getCategoryByID(Integer categoryID) throws PersistenceException;
}
