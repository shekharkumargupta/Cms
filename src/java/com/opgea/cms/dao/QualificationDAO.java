/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.Qualification;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface QualificationDAO {

    public Qualification create(Qualification qualification);
    public Qualification update(Qualification qualification);
    public Qualification delete(Long id);
    public Qualification find(Long id);
    public List<Qualification> findAll();
    public List<Qualification> findAllByCategoryId(Long categoryId);
}
