/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.controller;

import com.opgea.cms.domain.modal.JsonModelMap;
import com.opgea.cms.domain.modal.SessionData;
import com.opgea.cms.service.BranchService;
import com.opgea.cms.web.dto.BranchDTO;
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
@RequestMapping(value="branch")
public class BranchController {

    @Autowired
    private BranchService branchService;
    
    @RequestMapping(method= RequestMethod.POST, value="create")
    public @ResponseBody Map<String, Object> create(BranchDTO branchDTO, HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        branchDTO.setCompanyId(sessionData.getCompanyId());
        branchService.create(branchDTO); 
        System.out.println("Cms >> branch >> PostCreate:");
        return JsonModelMap.success().data(branchDTO.getBranchName());
    }
    
    @RequestMapping(method= RequestMethod.GET, value="branchList")
    public @ResponseBody Map<String, Object> getBranchList(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        System.out.println("Company Id:"+sessionData.getCompanyId());
        List<BranchDTO> branches = branchService.findAllByCompanyId(sessionData.getCompanyId());
        return JsonModelMap.success().data(branches);
    }
}
