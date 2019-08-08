/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.BranchDetails;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface BranchDetailsDAO {
 
    public BranchDetails create(BranchDetails branchDetails);
    public BranchDetails update(BranchDetails branchDetails);
    public BranchDetails remove(Long id);
    public BranchDetails find(Long id);
    public List<BranchDetails> findAll();
}
