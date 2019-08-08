/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.Branchh;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface BranchDAO {
 
    public Branchh create(Branchh branch);
    public Branchh update(Branchh branch);
    public Branchh remove(Long id);
    public Branchh find(Long id);
    public List<Branchh> findByCompanyId(Long companyId);
    public List<Branchh> findAll();
}
