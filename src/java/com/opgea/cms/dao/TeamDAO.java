/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.Team;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface TeamDAO {

    public Team create(Team team);
    public Team update(Team team);
    public Team remove(Long Id);
    public Team find(Long id);
    public List<Team> findAll();
    public List<Team> findAllByOpeningId(Long openingId);
    public List<Team> findAllByBranchId(Long branchId);
    public void addEmployeeToTeam(Long teamId, Long employeeId);
}
