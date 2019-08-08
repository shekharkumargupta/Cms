/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.controller;

import com.opgea.cms.domain.modal.JsonModelMap;
import com.opgea.cms.domain.modal.SessionData;
import com.opgea.cms.service.EmployeeService;
import com.opgea.cms.web.dto.EmployeeDTO;
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
@RequestMapping(value="/user")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    
    @RequestMapping(value="create", method=RequestMethod.POST)
    public @ResponseBody
            Map<String, Object> create(EmployeeDTO employeeDTO, HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        employeeDTO.setCompanyId(sessionData.getCompanyId());
        employeeDTO.setBranchId(sessionData.getBranchId());
        employeeDTO = employeeService.create(employeeDTO);
        return JsonModelMap.success().data(employeeDTO.getFirstName()+" saved successfully!");
    }
    
    @RequestMapping(value="empList", method= RequestMethod.GET)
    public @ResponseBody
            Map<String, Object> getEmployeeList(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        List<EmployeeDTO> empDTOList = employeeService.findAllByBranchId(sessionData.getBranchId());
        return JsonModelMap.success().data(empDTOList);
    }
    
    @RequestMapping(value="addOpening", method= RequestMethod.POST)
    public @ResponseBody Map<String, Object> addOpening(@RequestParam(value="employeeId")Long employeeId,
                                            @RequestParam(value="openingId")Long openingId,
                                            HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        employeeService.addOpening(employeeId, openingId);
        return JsonModelMap.success();
    }
}
