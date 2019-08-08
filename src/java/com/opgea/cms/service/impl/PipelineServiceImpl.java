/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service.impl;

import com.opgea.cms.dao.PipelineDAO;
import com.opgea.cms.domain.entities.Branchh;
import com.opgea.cms.domain.entities.Company;
import com.opgea.cms.domain.entities.Employee;
import com.opgea.cms.domain.entities.Pipeline;
import com.opgea.cms.domain.entities.PipelineStage;
import com.opgea.cms.domain.entities.Resume;
import com.opgea.cms.domain.modal.ChartModel;
import com.opgea.cms.domain.modal.MailModel;
import com.opgea.cms.service.PipelineService;
import com.opgea.cms.service.mail.MailService;
import com.opgea.cms.web.dto.ResumeDTO;
import com.opgea.constraints.ApplicationConstraints;
import com.opgea.util.DateUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service

public class PipelineServiceImpl implements PipelineService{

    @Autowired
    private PipelineDAO pipelineDAO;
    
    @Autowired
    private MailService mailService;
    
    @Override
    public boolean pipelineResume(Long resumeId, Long openingId, Long pipelinedById, Long companyId) {
        boolean status = false;
        pipelineDAO.create(resumeId, openingId, pipelinedById, companyId);
        status = true;
        return status;
    }

    /*
     * Mailing resume to the client's contact person
     */
    @Override
    public void sendEmail(MailModel model, String fileName, String[] documentNames, HttpServletRequest request, Long employeeId) {
        
        String resume = ApplicationConstraints.getResumeFolderLocation(request)+"/"+fileName+".doc";
        //String[] filePath = {path}; 
        String[] documents = new String[documentNames.length+1];
        System.out.println("No. Of Documents: "+documents.length);
        //documents[0] = resume;
        for(int a=0; a<documents.length-1; a++){
            documents[a] = ApplicationConstraints.getResumeFolderLocation(request)+"/"+documentNames[a];
        }
        documents[documents.length-1] = resume;
        
        MailModel mailModel = new MailModel();
        mailModel.setTo(model.getTo());
        mailModel.setFrom(model.getFrom());
        mailModel.setSubject(model.getSubject());
        mailModel.setMessage(model.getMessage());
        mailModel.setFilePath(documents);
        try {
            mailService.sendMimeMail(mailModel, employeeId);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PipelineServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<ResumeDTO> findPipelinedResumeByOpeningId(Long openingId) {
        List<Pipeline> pipelineList = pipelineDAO.findAllByOpeningId(openingId);
        List<ResumeDTO> resumeDTOList = new ArrayList<ResumeDTO>();
        for(Pipeline pipeline: pipelineList){
            Resume resume = pipeline.getResume();
            Branchh branch = resume.getBranch();
            Company company = branch.getCompany();
            Employee pipelinedBy = pipeline.getPipelinedBy();
            PipelineStage status = pipeline.getPipelineStatus();
            ResumeDTO resumeDTO = new ResumeDTO();
            resumeDTO.setId(resume.getId());
            resumeDTO.setPipelineId(pipeline.getId());
            resumeDTO.setName(resume.getName());
            resumeDTO.setContactNo(resume.getContactNo());
            resumeDTO.setEmail(resume.getEmail());
            resumeDTO.setExperience(resume.getExperience());
            resumeDTO.setKeySkills(resume.getKeySkills());
            if(branch != null){
                resumeDTO.setBranchId(branch.getId());
                resumeDTO.setBranchName(branch.getBranchDetails().getBranchName());
            }
            if(company != null){
                resumeDTO.setCompanyId(company.getId());
                resumeDTO.setCompanyName(company.getCompanyName());
            }
            if(pipelinedBy != null){
                resumeDTO.setCreatedById(pipelinedBy.getId());
                resumeDTO.setCreatedByName(pipelinedBy.getFirstName()+" "+pipelinedBy.getMiddleInitial()+" "+pipelinedBy.getLastName());
            }
            if(pipeline.getPipelineAt() != null){
                resumeDTO.setCreatedAt(DateUtil.getDateTimeString(pipeline.getPipelineAt().getTime()));
            }
            if(status != null){
                resumeDTO.setPipelineStatusId(status.getId());
                resumeDTO.setPipelineStatusName(status.getName());
            }
            resumeDTOList.add(resumeDTO);
        }
        return resumeDTOList;
    }
    
    
    @Override
    public List<ResumeDTO> findAllByDate(String fromDate, String toDate, Long employeeId, Long teamId, Integer stageId) {

        List<Pipeline> pipelineList = pipelineDAO.findAllByDate(fromDate, toDate, employeeId, teamId, stageId);
        List<ResumeDTO> resumeDTOList = new ArrayList<ResumeDTO>();
        for(Pipeline pipeline: pipelineList){
            Resume resume = pipeline.getResume();
            Branchh branch = resume.getBranch();
            Company company = branch.getCompany();
            Employee pipelinedBy = pipeline.getPipelinedBy();
            PipelineStage status = pipeline.getPipelineStatus();
            ResumeDTO resumeDTO = new ResumeDTO();
            resumeDTO.setId(resume.getId());
            resumeDTO.setPipelineId(pipeline.getId());
            resumeDTO.setName(resume.getName());
            resumeDTO.setContactNo(resume.getContactNo());
            resumeDTO.setEmail(resume.getEmail());
            resumeDTO.setExperience(resume.getExperience());
            resumeDTO.setKeySkills(resume.getKeySkills());
            if(branch != null){
                resumeDTO.setBranchId(branch.getId());
                resumeDTO.setBranchName(branch.getBranchDetails().getBranchName());
            }
            if(company != null){
                resumeDTO.setCompanyId(company.getId());
                resumeDTO.setCompanyName(company.getCompanyName());
            }
            if(pipelinedBy != null){
                resumeDTO.setCreatedById(pipelinedBy.getId());
                resumeDTO.setCreatedByName(pipelinedBy.getFirstName()+" "+pipelinedBy.getMiddleInitial()+" "+pipelinedBy.getLastName());
            }
            if(pipeline.getPipelineAt() != null){
                resumeDTO.setCreatedAt(DateUtil.getDateTimeString(pipeline.getPipelineAt().getTime()));
            }
            if(status != null){
                resumeDTO.setPipelineStatusId(status.getId());
                resumeDTO.setPipelineStatusName(status.getName());
            }
            resumeDTOList.add(resumeDTO);
        }
        return resumeDTOList;
    }
    
    @Override
    public List<ChartModel> getCountByDate(String fromDate, String toDate, Long employeeId, Long teamId, Long companyId) {
        List<ChartModel> chartList = pipelineDAO.getCountByDate(fromDate, toDate, employeeId, teamId, companyId);
        return chartList;
    }

    @Override
    public List<ResumeDTO> isResumeAlreadyPipelined(Long resumeId, Long openingId) {
        List<Pipeline> pipelineList = pipelineDAO.find(resumeId, openingId);
        List<ResumeDTO> resumeDTOList = new ArrayList<ResumeDTO>();
        for(Pipeline pipeline: pipelineList){
            Resume resume = pipeline.getResume();
            Branchh branch = resume.getBranch();
            Company company = branch.getCompany();
            Employee pipelinedBy = pipeline.getPipelinedBy();
            PipelineStage status = pipeline.getPipelineStatus();
            ResumeDTO resumeDTO = new ResumeDTO();
            resumeDTO.setId(resume.getId());
            resumeDTO.setPipelineId(pipeline.getId());
            resumeDTO.setName(resume.getName());
            resumeDTO.setContactNo(resume.getContactNo());
            resumeDTO.setEmail(resume.getEmail());
            resumeDTO.setExperience(resume.getExperience());
            resumeDTO.setKeySkills(resume.getKeySkills());
            if(branch != null){
                resumeDTO.setBranchId(branch.getId());
                resumeDTO.setBranchName(branch.getBranchDetails().getBranchName());
            }
            if(company != null){
                resumeDTO.setCompanyId(company.getId());
                resumeDTO.setCompanyName(company.getCompanyName());
            }
            if(pipelinedBy != null){
                resumeDTO.setCreatedById(pipelinedBy.getId());
                resumeDTO.setCreatedByName(pipelinedBy.getFirstName()+" "+pipelinedBy.getMiddleInitial()+" "+pipelinedBy.getLastName());
            }
            if(pipeline.getPipelineAt() != null){
                resumeDTO.setCreatedAt(DateUtil.getDateTimeString(pipeline.getPipelineAt().getTime()));
            }
            if(status != null){
                resumeDTO.setPipelineStatusId(status.getId());
                resumeDTO.setPipelineStatusName(status.getName());
            }
            resumeDTOList.add(resumeDTO);
        }
        return resumeDTOList;
    }

}
