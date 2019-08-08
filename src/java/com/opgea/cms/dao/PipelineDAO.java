/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.Pipeline;
import com.opgea.cms.domain.modal.ChartModel;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface PipelineDAO {

    public Pipeline create(Long resumeId, Long openingId, Long pipelinedBy, Long companyId);
    public Pipeline update(Pipeline pipeline);
    public Pipeline remove(Long pipelineId);
    public Pipeline find(Long pipelineId);
    public List<Pipeline> find(Long resumeId, Long openingId);
    public List<Pipeline> findAll();
    public List<Pipeline> findAllByEmployeeId(Long employeeId);
    public List<Pipeline> findAllByTeamId(Long teamId);
    public List<Pipeline> findAllByBranchId(Long branchId);
    public List<Pipeline> findAllByCompanyId(Long companyId);
    public List<Pipeline> findAllByOpeningId(Long openingId);
    public List<Pipeline> findAllByClientId(Long clientId);
    public List<Pipeline> findAllByDate(String fromDate, String toDate, Long employeeId, Long teamId, Integer stageId);
    public List<ChartModel> getCountByDate(String fromDate, String toDate, Long employeeId, Long teamId, Long companyId);
}
