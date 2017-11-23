/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Subcategory;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author thales
 */
public interface SubcategoryDAO {
    public Integer insert(Subcategory subcategory) throws PersistenceException;
    public boolean update(Subcategory subcategory) throws PersistenceException;
    public boolean remove(Integer seqCategory, Integer seqSubcategory) throws PersistenceException;
    public Subcategory getSubcategoryByID(Integer seqCategory, Integer seqSubcategory) throws PersistenceException;
    public boolean containsThisSubcategoryID(Integer seqCategory, Integer seqSubcategory) throws PersistenceException ;
}
