/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Category;
import br.cefetmg.LeMaitre.model.domain.Subcategory;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author Thalesgsn
 */
public interface CategoryDAO {
    public Integer insert(Category category) throws PersistenceException;
    public boolean update(Category category) throws PersistenceException;
    public boolean remove(Integer seqCategory) throws PersistenceException;
    public Category getCategoryByID(Integer seqCategory) throws PersistenceException;
    public boolean containsThisCategoryID(Integer seqCategory) throws PersistenceException;
    public List<Subcategory> listAllSubcategories(Integer categoryID) throws PersistenceException;
    public List<Category> listAllCategories() throws PersistenceException;
}
