/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.PipelineHistory;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface PipelineHistoryDAO {
    
    public PipelineHistory create(PipelineHistory pipelineHistory);
    public PipelineHistory update(PipelineHistory pipelineHistory);
    public PipelineHistory remove(Long pipelineHistoryId);
    public PipelineHistory find(Long pipelineHistoryId);
    public List<PipelineHistory> findAll();
    public List<PipelineHistory> findAllByPipelineId(Long pipelineId);
}
