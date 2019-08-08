/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.controller;

import com.opgea.cms.domain.modal.JsonModelMap;
import com.opgea.cms.domain.modal.SessionData;
import com.opgea.cms.service.OpeningDetailsService;
import com.opgea.cms.service.OpeningService;
import com.opgea.cms.web.dto.OpeningDTO;
import com.opgea.cms.web.dto.OpeningDetailsDTO;
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
@RequestMapping("opening")
public class OpeningController {
    
    
    @Autowired
    private OpeningService openingService;
    
    @Autowired
    private OpeningDetailsService detailsService;
    
    @RequestMapping(value="create", method= RequestMethod.POST)
    public @ResponseBody Map<String, Object> create(OpeningDTO openingDTO,
                                    HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        openingDTO.setBranchId(sessionData.getBranchId());
        openingDTO.setCreatedById(sessionData.getEmpId());
        openingService.create(openingDTO);
        return JsonModelMap.success().data(openingDTO);
    }
    
    @RequestMapping(value="employeeOpeningList", method=RequestMethod.GET)
    public @ResponseBody Map<String, Object> openingListByEmployee(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        
        List<OpeningDTO> openingDTOList = openingService.findAllByEmployeeId(sessionData.getEmpId());
        return JsonModelMap.success().data(openingDTOList);
    }
    
    @RequestMapping(value="teamOpeningList", method=RequestMethod.GET)
    public @ResponseBody Map<String, Object> openingListByTeam(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        
        List<OpeningDTO> openingDTOList = openingService.findAllByTeamId(sessionData.getTeamId());
        return JsonModelMap.success().data(openingDTOList);
    }
    
    @RequestMapping(value="branchOpeningList", method=RequestMethod.GET)
    public @ResponseBody Map<String, Object> openingListByClient(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        List<OpeningDTO> openingDTOList = openingService.findAllByBranchId(sessionData.getBranchId());
        return JsonModelMap.success().data(openingDTOList);
    }
    
    @RequestMapping(value="clientOpeningList", method=RequestMethod.GET)
    public @ResponseBody Map<String, Object> openingListByClient(@RequestParam(value="clientId", required=false)Long clientId,
                                                HttpServletRequest request){
        List<OpeningDTO> openingDTOList = openingService.findAllByClientId(clientId);
        return JsonModelMap.success().data(openingDTOList);
    }
    
    @RequestMapping(value="updateDetails", method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateDetails(@RequestParam(value="openingId")Long openingId,
                                                OpeningDetailsDTO openingDetailsDTO,
                                                HttpServletRequest request){
        
        openingDetailsDTO.setOpeningId(openingId);
        detailsService.create(openingDetailsDTO);
        System.out.println("OpeningDetails: "+openingDetailsDTO.getDetails());
        
        return JsonModelMap.success();
    }
    
    @RequestMapping(value="getDetails", method=RequestMethod.GET)
    public @ResponseBody Map<String, Object> updateDetails(@RequestParam(value="openingId")Long openingId){
        
        String details = openingService.getOpeningDetails(openingId);
        return JsonModelMap.success().data(details);
    }
    
    @RequestMapping(value="isAlreadyAssignedToTeam", method= RequestMethod.GET)
    public @ResponseBody 
    Map<String, Object> isAlreadyAssignedToTeam( @RequestParam(value="openingId", required=true)Long openingId, 
                                        @RequestParam(value="teamId", required=true)Long teamId,
                                        HttpServletRequest request){
        
        if(openingService.isAreadyAssignedToTeam(teamId, openingId)){
            return JsonModelMap.success().data("YES");
        }else{
            return JsonModelMap.success().data("NO");
        }
    }
    
    @RequestMapping(value="isAlreadyAssignedToEmployee", method= RequestMethod.GET)
    public @ResponseBody 
    Map<String, Object> isAlreadyAssignedToEmployee( @RequestParam(value="openingId", required=true)Long openingId, 
                                        @RequestParam(value="employeeId", required=true)Long employeeId,
                                        HttpServletRequest request){
        
        if(openingService.isAreadyAssignedToEmployee(employeeId, openingId)){
            System.out.println("YES");
            return JsonModelMap.success().data("YES");
            
        }else{
            System.out.println("NO");
            return JsonModelMap.success().data("NO");
        }
    }
}
