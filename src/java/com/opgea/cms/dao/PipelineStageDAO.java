/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.PipelineStage;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface PipelineStageDAO {
    
    public PipelineStage create(PipelineStage pipelineStatus);
    public PipelineStage update(PipelineStage pipelineStatus);
    public PipelineStage remove(Long pipelineStatusId);
    public PipelineStage find(Long pipelineStatusId);
    public List<PipelineStage> findAll();
    public List<PipelineStage> findAllByCompanyId(Long companyId);
}
