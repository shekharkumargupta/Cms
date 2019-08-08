/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service.impl;

import com.opgea.cms.dao.EmployeeDAO;
import com.opgea.cms.dao.PipelineDAO;
import com.opgea.cms.dao.PipelineHistoryDAO;
import com.opgea.cms.dao.PipelineStageDAO;
import com.opgea.cms.domain.entities.Employee;
import com.opgea.cms.domain.entities.Pipeline;
import com.opgea.cms.domain.entities.PipelineHistory;
import com.opgea.cms.domain.entities.PipelineStage;
import com.opgea.cms.service.PipelineHistoryService;
import com.opgea.cms.web.dto.PipelineHistoryDTO;
import com.opgea.util.DateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class PipelineHistoryServiceImpl implements PipelineHistoryService{
    
    @Autowired
    private PipelineHistoryDAO historyDAO;
    
    @Autowired
    private PipelineDAO pipelineDAO;

    @Autowired
    private PipelineStageDAO statusDAO;
    
    @Autowired
    private EmployeeDAO employeeDAO;
    
    @Override
    public PipelineHistoryDTO create(PipelineHistoryDTO historyDTO, Long createdById) {
        Pipeline pipeline = pipelineDAO.find(historyDTO.getPipelineId());
        PipelineStage status = statusDAO.find(historyDTO.getPipelineStatusId());
        Employee createdBy = employeeDAO.find(createdById);
        
        PipelineHistory history = null;
        
        if(historyDTO.getId() > 0){
            history = historyDAO.find(historyDTO.getId());
        }else{
            history = new PipelineHistory();
        }
        history.setRemarks(historyDTO.getRemarks());
        history.setPipelineStatus(status);
        history.setPipeline(pipeline);
        history.setCreatedBy(createdBy);
        history.setCreatedAt(Calendar.getInstance().getTime());
        
        if(historyDTO.getId() > 0){
            historyDAO.update(history);
        }else{
            historyDAO.create(history);
        }
        
        return historyDTO;
    }

    @Override
    public PipelineHistoryDTO update(PipelineHistoryDTO historyDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PipelineHistoryDTO remove(Long piplineHistoryId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PipelineHistoryDTO find(Long pipelineHistoryId) {
       PipelineHistory history = historyDAO.find(pipelineHistoryId);
       Employee createdBy = history.getCreatedBy();
       PipelineHistoryDTO historyDTO = new PipelineHistoryDTO();
       historyDTO.setId(history.getId());
       historyDTO.setPipelineId(history.getPipeline().getId());
       historyDTO.setPipelineStatusId(history.getPipelineStatus().getId());
       historyDTO.setPipelineStatusName(history.getPipelineStatus().getName());
       historyDTO.setRemarks(history.getRemarks());
       historyDTO.setCreatedById(createdBy.getId());
       historyDTO.setCreatedByName(createdBy.getFirstName()+" "+createdBy.getMiddleInitial()+" "+createdBy.getLastName());
       historyDTO.setCreatedAt(DateUtil.getDateTimeString(history.getCreatedAt().getTime()));
       return historyDTO;
    }

    @Override
    public List<PipelineHistoryDTO> findAll() {
       List<PipelineHistory> historyList = historyDAO.findAll();
       List<PipelineHistoryDTO> historyDTOList = new ArrayList<PipelineHistoryDTO>();
       for(PipelineHistory history: historyList){
           Employee createdBy = history.getCreatedBy();
           PipelineHistoryDTO historyDTO = new PipelineHistoryDTO();
           historyDTO.setId(history.getId());
           historyDTO.setPipelineId(history.getPipeline().getId());
           historyDTO.setPipelineStatusId(history.getPipelineStatus().getId());
           historyDTO.setPipelineStatusName(history.getPipelineStatus().getName());
           historyDTO.setRemarks(history.getRemarks());
           historyDTO.setCreatedById(createdBy.getId());
           historyDTO.setCreatedByName(createdBy.getFirstName()+" "+createdBy.getMiddleInitial()+" "+createdBy.getLastName());
           historyDTO.setCreatedAt(DateUtil.getDateTimeString(history.getCreatedAt().getTime()));
           historyDTOList.add(historyDTO);
       }
       return historyDTOList;
    }

    @Override
    public List<PipelineHistoryDTO> findAllByPipelineId(Long pipelineId) {
       List<PipelineHistory> historyList = historyDAO.findAllByPipelineId(pipelineId);
       List<PipelineHistoryDTO> historyDTOList = new ArrayList<PipelineHistoryDTO>();
       for(PipelineHistory history: historyList){
           Employee createdBy = history.getCreatedBy();
           PipelineHistoryDTO historyDTO = new PipelineHistoryDTO();
           historyDTO.setId(history.getId());
           historyDTO.setPipelineId(history.getPipeline().getId());
           historyDTO.setPipelineStatusId(history.getPipelineStatus().getId());
           historyDTO.setPipelineStatusName(history.getPipelineStatus().getName());
           historyDTO.setRemarks(history.getRemarks());
           historyDTO.setCreatedById(createdBy.getId());
           historyDTO.setCreatedByName(createdBy.getFirstName()+" "+createdBy.getMiddleInitial()+" "+createdBy.getLastName());
           historyDTO.setCreatedAt(DateUtil.getDateTimeString(history.getCreatedAt().getTime()));
           historyDTOList.add(historyDTO);
       }
       System.out.println("PipelineHistoryServiceImpl >> findAllByPipelineId >> "+historyDTOList.size());
       return historyDTOList;
    }
    
}
