/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.Position;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface PositionDAO {

    
    public Position create(Position position);
    public Position update(Position position);
    public Position delete(Long id);
    public Position find(Long id);
    public List<Position> findAll();
    public List<Position> findAllByCategoryId(Long categoryId);
    
}
