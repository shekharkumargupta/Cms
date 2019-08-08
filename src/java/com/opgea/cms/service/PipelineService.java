/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service;

import com.opgea.cms.domain.modal.ChartModel;
import com.opgea.cms.domain.modal.MailModel;
import com.opgea.cms.web.dto.ResumeDTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ramesh
 */
public interface PipelineService {
    
    public boolean pipelineResume(Long resumeId, Long openingId, Long pipelinedById, Long companyId);
    public List<ResumeDTO> findPipelinedResumeByOpeningId(Long openingId);
    public List<ResumeDTO> isResumeAlreadyPipelined(Long resumeId, Long openingId);
    public void sendEmail(MailModel mailModel, String resumeName, String[] documentNames, HttpServletRequest request, Long employeeId);
    public List<ResumeDTO> findAllByDate(String fromDate, String toDate, Long employeeId, Long teamId, Integer stageId);
    
    public List<ChartModel> getCountByDate(String fromDate, String toDate, Long employeeId, Long teamId, Long companyId);
}
