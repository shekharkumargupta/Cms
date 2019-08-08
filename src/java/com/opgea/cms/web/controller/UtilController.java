/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.controller;

import com.opgea.cms.domain.modal.CityModel;
import com.opgea.cms.domain.modal.ExtJSTreeModel;
import com.opgea.cms.domain.modal.JsonModelMap;
import com.opgea.cms.domain.modal.SessionData;
import com.opgea.cms.domain.modal.SimpleModel;
import com.opgea.cms.domain.qualifiers.EmployeeType;
import com.opgea.cms.service.UtilService;
import com.opgea.constraints.SessionConstraints;
import java.util.ArrayList;
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
@RequestMapping(value="util")
public class UtilController {
    
    
    @Autowired
    private UtilService utilService;
    
    @RequestMapping(method= RequestMethod.GET, value="categoryList")
    public @ResponseBody Map<String, Object> getCategories(){
        List<SimpleModel> categoryList = utilService.getAllCategories();
        return JsonModelMap.success().data(categoryList);
    }
    
    @RequestMapping(method= RequestMethod.GET, value="positionList")
    public @ResponseBody Map<String, Object> getPositionsByCategory(
                                @RequestParam(value="categoryId", required=false)Long categoryId){
        List<SimpleModel> positionList = utilService.getPositionsByCategory(categoryId);
        return JsonModelMap.success().data(positionList);
    }
    
    @RequestMapping(method= RequestMethod.GET, value="qualificationListByCategory")
    public @ResponseBody Map<String, Object> getQualificationByCategory(
                                @RequestParam(value="categoryId", required=false)Long categoryId){
        List<SimpleModel> qualificationList = utilService.getQualificationsByCategory(categoryId);
        return JsonModelMap.success().data(qualificationList);
    }
    
    @RequestMapping(method= RequestMethod.GET, value="qualificationList")
    public @ResponseBody Map<String, Object> getQualifications(){
        List<SimpleModel> qualificationList = utilService.getAllQualifications();
        return JsonModelMap.success().data(qualificationList);
    }
    
    @RequestMapping(method= RequestMethod.GET, value="openingStatusList")
    public @ResponseBody Map<String, Object> getOpeningStatus(){
        List<SimpleModel> openingStatusList = utilService.getAllOpeningStatus();
        return JsonModelMap.success().data(openingStatusList);
    }
    
    @RequestMapping(method= RequestMethod.GET, value="pipelineStatusList")
    public @ResponseBody Map<String, Object> getPipelineStatusList(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        List<SimpleModel> pipelineStatusList = utilService.getPipelineStatusList(sessionData.getCompanyId());
        return JsonModelMap.success().data(pipelineStatusList);
    }

    @RequestMapping(method= RequestMethod.GET, value="branchTypeList")
    public @ResponseBody Map<String, Object> getBranchTypes(){
        List<SimpleModel> branchList = utilService.getAllBranchTypes();
        return JsonModelMap.success().data(branchList);
    }
    
    @RequestMapping(method= RequestMethod.GET, value="countryList")
    public @ResponseBody Map<String, Object> getCountryList(){
        List<SimpleModel> countryList = utilService.getAllCountries();
        return JsonModelMap.success().data(countryList);
    }
    
    
    @RequestMapping(method= RequestMethod.GET, value="cityList")
    public @ResponseBody Map<String, Object> getCityList(@RequestParam(value="countryId", required=false)Long countryId, HttpServletRequest request){
        
        
        if(countryId == null){
            List<SimpleModel> cityList = utilService.getAllCities();
            return JsonModelMap.success().data(cityList);
        }else{
            List<CityModel> cityList = utilService.getCitiesByCountry(countryId);
            return JsonModelMap.success().data(cityList);
        }
        
    }
    
    @RequestMapping(method= RequestMethod.GET, value="contactTypeList")
    public @ResponseBody Map<String, Object> getContactTypeList(){
        List<SimpleModel> contactTypeList = utilService.getAllContactTypes();
        return JsonModelMap.success().data(contactTypeList);
    }
    
    @RequestMapping(method= RequestMethod.GET, value="conditionTypeList")
    public @ResponseBody Map<String, Object> getConditionTypeList(){
        List<SimpleModel> conditionTypeList = utilService.getAllConditionTypes();
        return JsonModelMap.success().data(conditionTypeList);
    }

    @RequestMapping(method= RequestMethod.GET, value="employeeTypeList")
    public @ResponseBody Map<String, Object> getEmployeeTypeList(){
        List<SimpleModel> employeeTypeList = utilService.getAllEmployeeTypes();
        return JsonModelMap.success().data(employeeTypeList);
    }
    
    @RequestMapping(method= RequestMethod.GET, value="documentTypeList")
    public @ResponseBody Map<String, Object> getDocumentTypeList(){
        List<SimpleModel> documentTypeList = utilService.getAllDocumentTypes();
        return JsonModelMap.success().data(documentTypeList);
    }
    
    @RequestMapping(method= RequestMethod.GET, value="menuTree")
    public @ResponseBody Map<String, Object> getMenuTree(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        EmployeeType employeeType = EmployeeType.values()[sessionData.getEmployeeType()];
        
        ExtJSTreeModel treeModel = new ExtJSTreeModel();
        treeModel.setText("ROOT");
        treeModel.setLeaf(false);
        
        ExtJSTreeModel menuNode = utilService.getMenuTree(employeeType);
        List<ExtJSTreeModel> menuList = new ArrayList<ExtJSTreeModel>();
        menuList.add(menuNode);
        return JsonModelMap.success().add("children", menuList);
    }
}
