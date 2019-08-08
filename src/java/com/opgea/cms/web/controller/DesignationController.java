/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.controller;

import com.opgea.cms.domain.modal.JsonModelMap;
import com.opgea.cms.domain.modal.SessionData;
import com.opgea.cms.service.DesignationService;
import com.opgea.cms.web.dto.DesignationDTO;
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
@RequestMapping(value="designation")
public class DesignationController {
    
    @Autowired
    private DesignationService designationService;
    
    
    @RequestMapping(method= RequestMethod.POST, value="create")
    public @ResponseBody Map<String, Object> create(DesignationDTO designationDTO,
                                        HttpServletRequest request){
        System.out.println("Designation >> Controller >> "+designationDTO);        
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) 
                session.getAttribute(SessionConstraints.SESSION_DATA.name());
        designationDTO.setCompanyId(sessionData.getCompanyId());
        designationService.create(designationDTO); 
        return JsonModelMap.success().data(designationDTO.getName());
    }
    
    @RequestMapping(method= RequestMethod.GET, value="designationList")
    public @ResponseBody Map<String, Object> getDesginationList(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) 
                session.getAttribute(SessionConstraints.SESSION_DATA.name());
        List<DesignationDTO> designationList =
                designationService.findAllByCompanyId(sessionData.getCompanyId());
        System.out.println("Designation >> Controller >> desginationList");
        return JsonModelMap.success().data(designationList);
    }
}
