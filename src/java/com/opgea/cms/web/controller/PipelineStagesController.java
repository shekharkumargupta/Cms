/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.controller;

import com.opgea.cms.domain.modal.JsonModelMap;
import com.opgea.cms.domain.modal.SessionData;
import com.opgea.cms.service.PipelineStageService;
import com.opgea.cms.web.dto.PipelineStagesDTO;
import com.opgea.constraints.SessionConstraints;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ramesh
 */
@Controller
@RequestMapping(value="pipelineStage")
public class PipelineStagesController {
    
    
    @Autowired
    private PipelineStageService stagesService;
    
    
    @RequestMapping(method= RequestMethod.POST, value="create")
    public @ResponseBody Map<String, Object> create(PipelineStagesDTO stagesDTO,
                                        HttpServletRequest request){
      
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) 
                session.getAttribute(SessionConstraints.SESSION_DATA.name());
        stagesDTO.setCompanyId(sessionData.getCompanyId());
        stagesService.create(stagesDTO); 
        return JsonModelMap.success().data(stagesDTO.getName());
    }
    
    @RequestMapping(method= RequestMethod.GET, value="pipelineStagesList")
    public @ResponseBody Map<String, Object> getPipelineStagesList(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) 
                session.getAttribute(SessionConstraints.SESSION_DATA.name());
        List<PipelineStagesDTO> pipelineStagesList =
                stagesService.findAllByCompanyId(sessionData.getCompanyId());
        return JsonModelMap.success().data(pipelineStagesList);
    }

}
