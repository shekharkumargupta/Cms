/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service;

import com.opgea.cms.web.dto.PipelineStagesDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface PipelineStageService {
    
    
    public PipelineStagesDTO create(PipelineStagesDTO stagesDTO);
    public PipelineStagesDTO update(PipelineStagesDTO stagesDTO);
    public PipelineStagesDTO remove(Long id);
    public PipelineStagesDTO find(Long id);
    public List<PipelineStagesDTO> findAll();
    public List<PipelineStagesDTO> findAllByCompanyId(Long companyId);
}
