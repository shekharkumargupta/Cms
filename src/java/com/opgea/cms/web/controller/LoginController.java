/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.controller;

import com.opgea.cms.domain.modal.JsonModelMap;
import com.opgea.cms.domain.modal.SessionData;
import com.opgea.cms.domain.qualifiers.EmployeeType;
import com.opgea.cms.service.EmployeeService;
import com.opgea.cms.service.LoginService;
import com.opgea.cms.web.dto.ChangePasswordDTO;
import com.opgea.cms.web.dto.EmployeeDTO;
import com.opgea.cms.web.dto.LoginDTO;
import com.opgea.constraints.ActionConstraints;
import com.opgea.constraints.SessionConstraints;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
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
@RequestMapping(value="/login")
public class LoginController {
    
    private static final Logger logger = Logger.getLogger(LoginController.class);
    
    @Autowired
    private LoginService loginService;
    
    @Autowired
    private EmployeeService employeeService;
    
    @RequestMapping(method= RequestMethod.GET)
    public String show(HttpServletRequest request){
        HttpSession session = request.getSession();
        String targetPage = "login";
        if(session.getAttribute(SessionConstraints.SESSION_DATA.name()) != null){
            targetPage = "redirect: home";
        }
        return targetPage;
    }
    
    @RequestMapping(method= RequestMethod.POST, value="verify")
    public @ResponseBody Map<String, Object> verify(LoginDTO loginDTO, HttpServletRequest request){
        String success = ActionConstraints.FAILURE;
        Boolean authentic = loginService.isAuthenticUser(loginDTO.getLoginId(), loginDTO.getPassword());

        if(authentic){
            success = ActionConstraints.SUCCESS;
            Long employeeId = loginService.find(loginDTO.getLoginId()).getEmployeeId();
            
            EmployeeDTO employeeDTO = employeeService.find(employeeId);
            HttpSession session  = request.getSession();
            SessionData sessionData = new SessionData();
            sessionData.setLoginId(loginDTO.getLoginId());
            sessionData.setEmpId(employeeDTO.getId());
            sessionData.setEmployeeType(employeeDTO.getEmployeeTypeId());
            sessionData.setEmployeeTypeName(EmployeeType.values()[employeeDTO.getEmployeeTypeId()].name());
            sessionData.setEmpName(employeeDTO.getFirstName()+" "+employeeDTO.getMiddleInitial()+" "+employeeDTO.getLastName().trim());
            sessionData.setBranchId(employeeDTO.getBranchId());
            sessionData.setCompanyId(employeeDTO.getCompanyId());
            sessionData.setCompanyName(employeeDTO.getCompanyName());
            sessionData.setTeamId(employeeDTO.getTeamId());
            session.setAttribute(SessionConstraints.SESSION_DATA.name(), sessionData);
            System.out.println("Long controller >> verify >> companyName: "+employeeDTO.getCompanyName());
            
            if(logger.isDebugEnabled()){
                logger.debug(employeeDTO);
            }
        }
        
        
 
        
        return JsonModelMap.success().data(success);
    }
    
    @RequestMapping(value="loginInfo", method= RequestMethod.GET)
    public @ResponseBody 
    Map<String, Object> getLoginInfo(HttpServletRequest request){
        System.out.println("Get Login Info...");
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.toString());
        
        return JsonModelMap.success().data(sessionData);
    }
    
    @RequestMapping(value="logout", method= RequestMethod.GET)
    public @ResponseBody 
    Map<String, Object> logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return JsonModelMap.success().data("login");
    }
    
    @RequestMapping(value="updatePassword", method= RequestMethod.POST)
    public @ResponseBody Map<String, Object> updatePassword(ChangePasswordDTO passwordDTO,
                                                HttpServletRequest request){
        
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        
        passwordDTO.setLoginId(sessionData.getLoginId());
        Boolean status = loginService.updatePassword(passwordDTO);
        if(status == false){
            return JsonModelMap.success().data("Password could not updated.");
        }else{
            return JsonModelMap.success().data("Password changed.");
        }
    }
    
    @RequestMapping(value="isExistingUser", method= RequestMethod.GET)
    public @ResponseBody 
    Map<String, Object> isExistingUser(@RequestParam(value="emailId", required=false)String emailId, HttpServletRequest request){
        
        if(loginService.find(emailId) != null){
            return JsonModelMap.success().data("YES");
        }else{
            return JsonModelMap.success().data("NO");
        }
    }
    
}
