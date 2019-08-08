/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.controller;

import com.opgea.cms.domain.modal.JsonModelMap;
import com.opgea.cms.domain.modal.SessionData;
import com.opgea.cms.service.EmailMessageService;
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
 * @author Ramesh
 */
@Controller
@RequestMapping(value="emailMessage")
public class EmailMessageController {
    
    @Autowired
    private EmailMessageService messageService;
    
    @RequestMapping(value="create", method= RequestMethod.POST)
    public @ResponseBody Map<String, Object> create(EmailMessageDTO messageDTO, HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        messageDTO.setBranchId(sessionData.getBranchId());
        EmailMessageDTO messageDTO1 = messageService.create(messageDTO);
        return JsonModelMap.success().data(messageDTO1);
    }
    
    @RequestMapping(value="load", method= RequestMethod.GET)
    public @ResponseBody Map<String, Object> loadMessage(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        EmailMessageDTO messageDTO = messageService.findByBranchId(sessionData.getBranchId());
        return JsonModelMap.success().data(messageDTO);
    }
}
