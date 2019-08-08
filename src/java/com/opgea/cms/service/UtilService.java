/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service;

import com.opgea.cms.domain.modal.CityModel;
import com.opgea.cms.domain.modal.ExtJSTreeModel;
import com.opgea.cms.domain.modal.SimpleModel;
import com.opgea.cms.domain.qualifiers.EmployeeType;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface UtilService {
    

    public List<SimpleModel> getAllCountries();
    public List<SimpleModel> getAllCities();
    public List<CityModel> getCitiesByCountry(Long countryId);
    
    
    public List<SimpleModel> getAllCategories();
    public List<SimpleModel> getPositionsByCategory(Long categoryId);
    public List<SimpleModel> getAllQualifications();
    public List<SimpleModel> getQualificationsByCategory(Long categoryId);
    public List<SimpleModel> getPipelineStatusList(Long companyId);
    public List<SimpleModel> getAllEmployeeTypes();
    public List<SimpleModel> getAllDocumentTypes();
    public List<SimpleModel> getAllBranchTypes();
    public List<SimpleModel> getAllContactTypes();
    public List<SimpleModel> getAllConditionTypes();
    public List<SimpleModel> getAllOpeningStatus();
    public List<SimpleModel> getAllSalaryBasis();
    
    public ExtJSTreeModel getMenuTree(EmployeeType employeeType);
}
