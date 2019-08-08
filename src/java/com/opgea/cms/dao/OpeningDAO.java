/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.Opening;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface OpeningDAO {
    
    
    public Opening create(Opening opening);
    public Opening update(Opening opening);
    public Opening remove(Long openingId);
    public Opening find(Long openingId);
    
    public List<Opening> findAll();
    public List<Opening> findAllByCompanyId(Long companyId);
    public List<Opening> findAllByClientId(Long clientId);
    public List<Opening> findAllByBranchId(Long branchId);
    public List<Opening> findAllByTeamId(Long teamId);
    public List<Opening> findAllByEmployeeId(Long employeeId);
    
    public void assignOpeningToTeam(Long teamId, Long openingId);
    public void assignOpeningToEmployee(Long employeeId, Long openingId);
    
    public Boolean isAreadyAssignedToTeam(Long teamId, Long openingId);
    public Boolean isAreadyAssignedToEmployee(Long employeeId, Long openingId);
}
