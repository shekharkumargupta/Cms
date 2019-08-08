/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service;

import com.opgea.cms.web.dto.PipelineHistoryDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */

public interface PipelineHistoryService {
    
    public PipelineHistoryDTO create(PipelineHistoryDTO historyDTO, Long createdById);
    public PipelineHistoryDTO update(PipelineHistoryDTO historyDTO);
    public PipelineHistoryDTO remove(Long piplineHistoryId);
    public PipelineHistoryDTO find(Long pipelineHistoryId);
    public List<PipelineHistoryDTO> findAll();
    public List<PipelineHistoryDTO> findAllByPipelineId(Long pipelineId);
}
