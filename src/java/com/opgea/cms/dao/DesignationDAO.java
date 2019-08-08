/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.Designation;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface DesignationDAO {
    
    public Designation create(Designation designation);
    public Designation update(Designation designation);
    public Designation remove(Long designationId);
    public Designation find(Long designationId);
    public List<Designation> findAll();
    public List<Designation> findAllByCompanyId(Long companyId);
}
