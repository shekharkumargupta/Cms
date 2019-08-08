/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.TeamAndOpening;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface TeamAndOpeningDAO {
 
    public TeamAndOpening create(TeamAndOpening teamAndOpening);
    public TeamAndOpening update(TeamAndOpening teamAndOpening);
    public TeamAndOpening remove(Long teamAndOpeningId);
    public TeamAndOpening findById(Long teamAndOpeningId);
    public List<TeamAndOpening> findAll();
    public List<TeamAndOpening> findAllByOPeningId(Long openingId);
    public List<TeamAndOpening> findAllByTeamId(Long teamId);
}
