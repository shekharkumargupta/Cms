/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.Category;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface CategoryDAO {
    
    public Category create(Category category);
    public Category update(Category category);
    public Category delete(Long id);
    public Category find(Long id);
    public List<Category> findAll();
}
