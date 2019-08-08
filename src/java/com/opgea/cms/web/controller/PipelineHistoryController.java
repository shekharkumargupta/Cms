/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.controller;

import com.opgea.cms.domain.modal.JsonModelMap;
import com.opgea.cms.domain.modal.SessionData;
import com.opgea.cms.service.PipelineHistoryService;
import com.opgea.cms.web.dto.PipelineHistoryDTO;
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
@RequestMapping(value="history")
public class PipelineHistoryController {
    
    @Autowired
    private PipelineHistoryService historyService;
    
    @RequestMapping(value="saveComment", method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> saveComment(@RequestParam(value="pipelineId")
                                                        Long pipelineId,
                                                        PipelineHistoryDTO historyDTO,
                                                        HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        historyDTO.setPipelineId(pipelineId);
        historyService.create(historyDTO, sessionData.getEmpId());
        return JsonModelMap.success().data(historyDTO);
    }
    
    @RequestMapping(value="historyList", method=RequestMethod.GET)
    public @ResponseBody Map<String, Object> pipelineHistoryList(@RequestParam(value="pipelineId")
                                                        Long pipelineId){

        List<PipelineHistoryDTO> historyList = historyService.findAllByPipelineId(pipelineId);
        return JsonModelMap.success().data(historyList);
    }
}
