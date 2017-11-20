/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.LeMaitre.model.dao;

import br.cefetmg.LeMaitre.model.domain.Image;
import br.cefetmg.LeMaitre.model.domain.ItemImage;
import br.cefetmg.LeMaitre.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author thales
 */
public interface ItemImageDAO {
    public boolean insert(ItemImage itemImage) throws PersistenceException;
    public boolean remove(Integer itemID, Long imageID) throws PersistenceException;
    public List<Image> getImagesByItemID(Integer itemID) throws PersistenceException;
}
