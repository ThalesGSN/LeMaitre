/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Category;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public interface CategoryDAO {
    public Long insert(Category category) throws PersistenceException;
    public boolean update(Category category) throws PersistenceException;
    public boolean remove(Long categorySEQ) throws PersistenceException;
    public Category getCategoryByID(Long categorySEQ) throws PersistenceException;
    public boolean containsThisCategoryID(Long categorySEQ) throws PersistenceException ;
    public List<Category> listAllCategories() throws PersistenceException;
}
