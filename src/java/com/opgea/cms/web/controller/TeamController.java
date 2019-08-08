/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.controller;

import com.opgea.cms.domain.modal.ExtJSTreeModel;
import com.opgea.cms.domain.modal.JsonModelMap;
import com.opgea.cms.domain.modal.SessionData;
import com.opgea.cms.service.EmployeeService;
import com.opgea.cms.service.TeamService;
import com.opgea.cms.web.dto.TeamDTO;
import com.opgea.constraints.SessionConstraints;
import java.lang.String;
import java.util.HashMap;
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
@RequestMapping(value="/team")
public class TeamController {
    
    
    @Autowired
    private TeamService teamService;
    
    @Autowired
    private EmployeeService employeeService;
    
    @RequestMapping(value="create", method= RequestMethod.POST)
    public @ResponseBody Map<String, Object> create(TeamDTO teamDTO, HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        teamDTO.setBranchId(sessionData.getBranchId());
        teamService.create(teamDTO);
        return JsonModelMap.success().data(teamDTO);
    }
    
    @RequestMapping(value="teamListByOpeningId", method=RequestMethod.GET)
    public @ResponseBody Map<String, Object> getTeamListByOpeningId(@RequestParam(
                                                        required=false,
                                                        value="openingId") Long openingId){
        
        List<TeamDTO> teamList = teamService.findAllByOpeningId(openingId);
        return JsonModelMap.success().data(teamList);
    }
    
    @RequestMapping(value="addMember", method= RequestMethod.POST)
    public @ResponseBody Map<String, Object> addMember(@RequestParam(value="teamId")Long teamId,
                                            @RequestParam(value="memberId")Long employeeId, HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        teamService.addEmployee(teamId, employeeId);
        return JsonModelMap.success();
    }
    
    @RequestMapping(value="teamMember", method= RequestMethod.GET)
    public @ResponseBody Map<String, Object> teamMember(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        ExtJSTreeModel memberList = teamService.findAllTeamAndMemberByBranchId(sessionData.getBranchId());
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("success", "success");
        dataMap.put("children", memberList);
        return dataMap;
    }
    
    @RequestMapping(value="addOpening", method= RequestMethod.POST)
    public @ResponseBody Map<String, Object> addOpening(@RequestParam(value="teamId")Long teamId,
                                            @RequestParam(value="openingId")Long openingId,
                                            HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        teamService.addOpening(teamId, openingId);
        return JsonModelMap.success();
    }
    
    @RequestMapping(value="teamOpenings", method= RequestMethod.GET)
    public @ResponseBody Map<String, Object> teamOpenings(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        ExtJSTreeModel openingList = teamService.findAllTeamAndOpeningByBranchId(sessionData.getBranchId());
        //return JsonModelMap.success().data(memberList);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("success", "success");
        dataMap.put("children", openingList);
        return dataMap;
    }
    
    @RequestMapping(value="memberOpenings", method= RequestMethod.GET)
    public @ResponseBody Map<String, Object> memberOpenings(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        ExtJSTreeModel openingList = employeeService.findAllEmployeeAndOpeningByTeamId(sessionData.getTeamId());
        //return JsonModelMap.success().data(memberList);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("success", "success");
        dataMap.put("children", openingList);
        return dataMap;
    }
}
