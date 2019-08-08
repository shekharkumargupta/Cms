/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service;

import com.opgea.cms.web.dto.OpeningDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface OpeningService {
    
    public OpeningDTO create(OpeningDTO openingDTO);
    public OpeningDTO update(OpeningDTO openingDTO);
    public OpeningDTO remove(Long openingId);
    public OpeningDTO find(Long openingId);
    
    public List<OpeningDTO> findAll();
    public List<OpeningDTO> findAllByBranchId(Long branchId);
    public List<OpeningDTO> findAllByTeamId(Long teamId);
    public List<OpeningDTO> findAllByEmployeeId(Long employeeId);
    public List<OpeningDTO> findAllByCompanyId(Long companyId);
    public List<OpeningDTO> findAllByClientId(Long clientId);
    
    public Boolean isAreadyAssignedToTeam(Long teamId, Long openingId);
    public Boolean isAreadyAssignedToEmployee(Long employeeId, Long openingId);

    public String getOpeningDetails(Long openingId);
    
}
