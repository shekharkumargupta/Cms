/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.controller;

import com.opgea.cms.domain.modal.JsonModelMap;
import com.opgea.cms.domain.modal.SessionData;
import com.opgea.cms.service.EmailConfigService;
import com.opgea.cms.web.dto.EmailConfigDTO;
import com.opgea.cms.web.dto.EmailMessageDTO;
import com.opgea.constraints.SessionConstraints;
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
 * @author Shekhar
 */
@Controller
@RequestMapping(value="emailConfig")
public class EmailConfigController {
    
    
    @Autowired
    private EmailConfigService emailConfigService;
    
    @RequestMapping(value="create", method= RequestMethod.POST)
    public @ResponseBody Map<String, Object> create(EmailConfigDTO configDTO, HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        configDTO.setEmployeeId(sessionData.getEmpId());
        emailConfigService.create(configDTO);
        return JsonModelMap.success().data(configDTO);
    }
    
    @RequestMapping(value="load", method= RequestMethod.GET)
    public @ResponseBody Map<String, Object> loadMessage(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        EmailConfigDTO configDTO = emailConfigService.findByEmployeeId(sessionData.getEmpId());
        System.out.println("ConfigDTO: "+configDTO);
        return JsonModelMap.success().data(configDTO);
    }
}
