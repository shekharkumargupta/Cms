/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service;

import com.opgea.cms.web.dto.BranchDTO;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public interface BranchService {
    
    public BranchDTO create(BranchDTO branchDTO);
    public BranchDTO update(BranchDTO branchDTO);
    public BranchDTO remove(BranchDTO branchDTO);
    public BranchDTO find(BranchDTO branchDTO);
    public List<BranchDTO> findAll();
    public List<BranchDTO> findAllByCompanyId(Long companyId);
    
}
