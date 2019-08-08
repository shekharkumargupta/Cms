/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service;

import com.opgea.cms.domain.modal.ExtJSTreeModel;
import com.opgea.cms.web.dto.TeamDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface TeamService {

    public TeamDTO create(TeamDTO teamDTO);
    public TeamDTO update(TeamDTO teamDTO);
    public TeamDTO remove(Long id);
    public TeamDTO find(Long id);
    public List<TeamDTO> findAll();
    public List<TeamDTO> findAllByBranchId(Long branchId);
    public List<TeamDTO> findAllByOpeningId(Long openingId);
    public ExtJSTreeModel findAllTeamAndMemberByBranchId(Long branchId);
    public ExtJSTreeModel findAllTeamAndOpeningByBranchId(Long branchId);
    public void addEmployee(Long teamId, Long employeeId);    
    public void addOpening(Long teamId, Long openingId);
}
