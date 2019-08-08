/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.controller;

import com.opgea.cms.domain.modal.ChartModel;
import com.opgea.cms.domain.modal.JsonModelMap;
import com.opgea.cms.domain.modal.MailModel;
import com.opgea.cms.domain.modal.SessionData;
import com.opgea.cms.service.PipelineService;
import com.opgea.cms.web.dto.ResumeDTO;
import com.opgea.constraints.SessionConstraints;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ramesh
 */
@Controller
@RequestMapping(value="pipeline")
public class PipelineController {
    
    @Autowired
    private PipelineService pipelineService;
    
    @RequestMapping(value="sendResume", method=RequestMethod.POST)
    public @ResponseBody
            Map<String, Object> sendResume(@RequestParam(value="resumeId")Long resumeId,
                                           @RequestParam(value="documentNames")String documentNames,
                                           @RequestParam(value="openingId")Long openingId,  
                                            HttpServletRequest request,
                                            MailModel model){

        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        
        model.setSubject("Resume Id: "+resumeId);
        boolean status = pipelineService.pipelineResume(resumeId, openingId, 
                            sessionData.getEmpId(), sessionData.getCompanyId());
        if(status == true){
            pipelineService.sendEmail(model, resumeId.toString(), documentNames.split(","), request, sessionData.getEmpId());
        }
        return JsonModelMap.success().data(model);
    }
    
    @RequestMapping(method= RequestMethod.GET, value="pipelinedResumeByOpeningId")
    public @ResponseBody Map<String, Object> pipelinedResumeByOpeningId(@RequestParam(value="openingId")
                                                Long openingId, HttpServletRequest request){
        List<ResumeDTO> resumeList = pipelineService.findPipelinedResumeByOpeningId(openingId);
        return JsonModelMap.success().data(resumeList);
    }
    
    @RequestMapping(method= RequestMethod.GET, value="pipelinedByDate")
    public @ResponseBody Map<String, Object> pipelinedByDate(
                                                @RequestParam(value="fromDate")String fromDate, 
                                                @RequestParam(value="toDate")String toDate,
                                                @RequestParam(value="employeeId")Long employeeId, 
                                                @RequestParam(value="teamId")Long teamId, 
                                                @RequestParam(value="stageId")Integer stageId, 
                                                HttpServletRequest request){
        
        
        List<ResumeDTO> resumeList = pipelineService.findAllByDate(fromDate, 
                                                toDate, 
                                                employeeId, 
                                                teamId, 
                                                stageId);
        return JsonModelMap.success().data(resumeList);
    }
    
    /*
     * 
     * Get Chart Data
     */
    @RequestMapping(method= RequestMethod.GET, value="countByDate")
    public @ResponseBody Map<String, Object> getCountByDate(
                                                @RequestParam(value="fromDate", required=false)String fromDate, 
                                                @RequestParam(value="toDate", required=false)String toDate,
                                                @RequestParam(value="employeeId", required=false)Long employeeId, 
                                                @RequestParam(value="teamId", required=false)Long teamId, 
                                                HttpServletRequest request){
        
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        
        List<ChartModel> dataList = pipelineService.getCountByDate("2012-08-01",
                                                "2012-08-31", 
                                                employeeId, 
                                                teamId,
                                                sessionData.getCompanyId());
        return JsonModelMap.success().data(dataList);
    }
}
