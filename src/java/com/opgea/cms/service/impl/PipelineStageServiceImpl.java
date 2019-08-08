/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service.impl;

import com.opgea.cms.dao.CompanyDAO;
import com.opgea.cms.dao.PipelineStageDAO;
import com.opgea.cms.domain.entities.Company;
import com.opgea.cms.domain.entities.PipelineStage;
import com.opgea.cms.service.PipelineStageService;
import com.opgea.cms.web.dto.PipelineStagesDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class PipelineStageServiceImpl implements PipelineStageService{

    @Autowired
    private PipelineStageDAO stagesDAO;
            
    @Autowired
    private CompanyDAO companyDAO;
            
    @Override
    public PipelineStagesDTO create(PipelineStagesDTO stagesDTO) {
        PipelineStage stages = new PipelineStage();
        if(stagesDTO.getId() > 0){
            stages = stagesDAO.find(stagesDTO.getId());
        }
        
        stages.setName(stagesDTO.getName());
        stages.setStepNo(stagesDTO.getStepNo());
        if(stagesDTO.getId() > 0){
            stagesDAO.update(stages);
        }else{
            Company company = companyDAO.find(stagesDTO.getCompanyId());
            stages.setCompany(company);
            stagesDAO.create(stages);
        }
        
        return stagesDTO;
    }

    @Override
    public PipelineStagesDTO update(PipelineStagesDTO stagesDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PipelineStagesDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PipelineStagesDTO find(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PipelineStagesDTO> findAll() {
        List<PipelineStage> stages = stagesDAO.findAll();
        List<PipelineStagesDTO> stagesList = new ArrayList<PipelineStagesDTO>();
        for(PipelineStage stage : stages){
            PipelineStagesDTO stagesDTO = new PipelineStagesDTO();
            stagesDTO.setId(stage.getId());
            stagesDTO.setName(stage.getName());
            stagesDTO.setStepNo(stage.getStepNo());
            stagesDTO.setCompanyId(stage.getCompany().getId());
            stagesList.add(stagesDTO);
        }
        return stagesList;
    }

    @Override
    public List<PipelineStagesDTO> findAllByCompanyId(Long companyId) {
        List<PipelineStage> stages = stagesDAO.findAllByCompanyId(companyId);
        List<PipelineStagesDTO> stagesList = new ArrayList<PipelineStagesDTO>();
        for(PipelineStage stage : stages){
            PipelineStagesDTO stagesDTO = new PipelineStagesDTO();
            stagesDTO.setId(stage.getId());
            stagesDTO.setName(stage.getName());
            stagesDTO.setStepNo(stage.getStepNo());
            stagesDTO.setCompanyId(stage.getCompany().getId());
            stagesList.add(stagesDTO);
        }
        return stagesList;
    }
    
}
