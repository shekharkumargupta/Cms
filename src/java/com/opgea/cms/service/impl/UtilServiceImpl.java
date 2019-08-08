/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service.impl;

import com.opgea.cms.dao.CategoryDAO;
import com.opgea.cms.dao.CityDAO;
import com.opgea.cms.dao.CountryDAO;
import com.opgea.cms.dao.PipelineStageDAO;
import com.opgea.cms.dao.PositionDAO;
import com.opgea.cms.dao.QualificationDAO;
import com.opgea.cms.dao.TeamDAO;
import com.opgea.cms.domain.entities.Category;
import com.opgea.cms.domain.entities.City;
import com.opgea.cms.domain.entities.Country;
import com.opgea.cms.domain.entities.PipelineStage;
import com.opgea.cms.domain.entities.Position;
import com.opgea.cms.domain.entities.Qualification;
import com.opgea.cms.domain.modal.CityModel;
import com.opgea.cms.domain.modal.ExtJSTreeModel;
import com.opgea.cms.domain.modal.SimpleModel;
import com.opgea.cms.domain.qualifiers.BranchType;
import com.opgea.cms.domain.qualifiers.ConditionType;
import com.opgea.cms.domain.qualifiers.ContactType;
import com.opgea.cms.domain.qualifiers.DocumentType;
import com.opgea.cms.domain.qualifiers.EmployeeType;
import com.opgea.cms.domain.qualifiers.OpeningStatus;
import com.opgea.cms.domain.qualifiers.SalaryBasis;
import com.opgea.cms.service.UtilService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class UtilServiceImpl implements UtilService{

    
    @Autowired
    private CountryDAO countryDAO;
    @Autowired
    private CityDAO cityDAO;
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private PositionDAO positionDAO;
    @Autowired
    private QualificationDAO qualificationDAO;
    @Autowired
    private PipelineStageDAO pipelineStatusDAO;
    @Autowired
    private TeamDAO teamDAO;
    
    @Override
    public List<SimpleModel> getAllCountries() {
        List<Country> countries = countryDAO.findAll();
        List<SimpleModel> countryList = new ArrayList<SimpleModel>();
        for(Country country : countries){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(country.getId()));
            model.setValue(country.getName());
            countryList.add(model);
        }
        return countryList;
    }

    @Override
    public List<SimpleModel> getAllCities() {
        List<City> cities = cityDAO.findAll();
        List<SimpleModel> cityList = new ArrayList<SimpleModel>();
        for(City city : cities){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(city.getId()));
            model.setValue(city.getName());
            cityList.add(model);
        }
        return cityList;
    }

    @Override
    public List<CityModel> getCitiesByCountry(Long countryId) {
        List<City> cities = cityDAO.findAllByCountry(countryId);
        List<CityModel> cityList = new ArrayList<CityModel>();
        for(City city : cities){
            CityModel model = new CityModel();
            model.setId(new Long(city.getId()));
            model.setValue(city.getName());
            model.setCountryId(city.getCountry().getId());
            cityList.add(model);
        }
        return cityList;
    }

    @Override
    public List<SimpleModel> getAllCategories() {
        List<Category> categories = categoryDAO.findAll();
        List<SimpleModel> categoryList = new ArrayList<SimpleModel>();
        for(Category category: categories){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(category.getId()));
            model.setValue(category.getName());
            categoryList.add(model);
        }
        return categoryList;
    }

    @Override
    public List<SimpleModel> getPositionsByCategory(Long categoryId) {
        List<Position> positions = positionDAO.findAllByCategoryId(categoryId);
        List<SimpleModel> positionList = new ArrayList<SimpleModel>();
        for(Position position: positions){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(position.getId()));
            model.setValue(position.getName());
            positionList.add(model);
        }
        return positionList;
    }

    @Override
    public List<SimpleModel> getAllQualifications() {
        List<Qualification> qualifications = qualificationDAO.findAll();
        List<SimpleModel> qualificationList = new ArrayList<SimpleModel>();
        for(Qualification qualification: qualifications){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(qualification.getId()));
            model.setValue(qualification.getName());
            qualificationList.add(model);
        }
        return qualificationList;
    }

    
    @Override
    public List<SimpleModel> getQualificationsByCategory(Long categoryId) {
        List<Qualification> qualifications = qualificationDAO.findAllByCategoryId(categoryId);
        List<SimpleModel> qualificationList = new ArrayList<SimpleModel>();
        for(Qualification qualification: qualifications){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(qualification.getId()));
            model.setValue(qualification.getName());
            qualificationList.add(model);
        }
        return qualificationList;
    }
    
    @Override
    public List<SimpleModel> getPipelineStatusList(Long companyId) {
        List<PipelineStage> pipelineStatuses = pipelineStatusDAO.findAllByCompanyId(companyId);
        List<SimpleModel> qualificationList = new ArrayList<SimpleModel>();
        for(PipelineStage pipelineStatus: pipelineStatuses){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(pipelineStatus.getId()));
            model.setValue(pipelineStatus.getName());
            qualificationList.add(model);
        }
        return qualificationList;
    }

    
    @Override
    public List<SimpleModel> getAllDocumentTypes() {
        List<SimpleModel> employeeTypeList = new ArrayList<SimpleModel>();
        for(DocumentType qualifier : DocumentType.values()){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(qualifier.ordinal()));
            model.setValue(qualifier.name());
            employeeTypeList.add(model);
        }
        return employeeTypeList;
    }
    
    @Override
    public List<SimpleModel> getAllEmployeeTypes() {
        List<SimpleModel> employeeTypeList = new ArrayList<SimpleModel>();
        for(EmployeeType qualifier : EmployeeType.values()){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(qualifier.ordinal()));
            model.setValue(qualifier.name());
            employeeTypeList.add(model);
        }
        return employeeTypeList;
    }

    @Override
    public List<SimpleModel> getAllBranchTypes() {
        List<SimpleModel> branchTypeList = new ArrayList<SimpleModel>();
        for(BranchType qualifier : BranchType.values()){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(qualifier.ordinal()));
            model.setValue(qualifier.name());
            branchTypeList.add(model);
        }
        return branchTypeList;
    }

    @Override
    public List<SimpleModel> getAllContactTypes() {
        List<SimpleModel> contactTypeList = new ArrayList<SimpleModel>();
        for(ContactType qualifier : ContactType.values()){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(qualifier.ordinal()));
            model.setValue(qualifier.name());
            contactTypeList.add(model);
        }
        return contactTypeList;
    }

    @Override
    public List<SimpleModel> getAllConditionTypes() {
        List<SimpleModel> contactTypeList = new ArrayList<SimpleModel>();
        for(ConditionType qualifier : ConditionType.values()){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(qualifier.ordinal()));
            model.setValue(qualifier.name());
            contactTypeList.add(model);
        }
        return contactTypeList;
    }
    
    @Override
    public List<SimpleModel> getAllOpeningStatus() {
        List<SimpleModel> openingStatusList = new ArrayList<SimpleModel>();
        for(OpeningStatus qualifier : OpeningStatus.values()){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(qualifier.ordinal()));
            model.setValue(qualifier.name());
            openingStatusList.add(model);
        }
        return openingStatusList;
    }

    @Override
    public List<SimpleModel> getAllSalaryBasis() {
        List<SimpleModel> salaryBasisList = new ArrayList<SimpleModel>();
        for(SalaryBasis qualifier : SalaryBasis.values()){
            SimpleModel model = new SimpleModel();
            model.setId(new Long(qualifier.ordinal()));
            model.setValue(qualifier.name());
            salaryBasisList.add(model);
        }
        return salaryBasisList;
    }

    @Override
    public ExtJSTreeModel getMenuTree(EmployeeType employeeType) {
        ExtJSTreeModel rootMenu = new ExtJSTreeModel();
        rootMenu.setId("0");
        rootMenu.setText("Action");
        rootMenu.setDescription("Setting Basics of Application");
        rootMenu.setLeaf(false);
        rootMenu.setExpanded("true");
        rootMenu.setIconCls("settingIcon");
        List<ExtJSTreeModel> rootList = new ArrayList<ExtJSTreeModel>();

        ExtJSTreeModel branch = new ExtJSTreeModel("1", "Branch", "branchIcon", "Branch", Boolean.TRUE, "FALSE", null, null);
        ExtJSTreeModel designation = new ExtJSTreeModel("2", "Designation", "bookIcon", "Designation", Boolean.TRUE, "FALSE", null, null);
        ExtJSTreeModel workstation = new ExtJSTreeModel("3", "Pipeline Stage", "monitorIcon", "Workstation", Boolean.TRUE, "FALSE", null, null);
        ExtJSTreeModel employee = new ExtJSTreeModel("4", "Employee", "userIcon", "Employee", Boolean.TRUE, "FALSE", null, null);
        ExtJSTreeModel team = new ExtJSTreeModel("5", "Team", "teamIcon", "Team", Boolean.TRUE, "FALSE", null, null);
        ExtJSTreeModel client = new ExtJSTreeModel("6", "Client", "clientContactIcon", "Client", Boolean.TRUE, "FALSE", null, null);
        ExtJSTreeModel openings = new ExtJSTreeModel("7", "Openings", "openingListIcon", "Opening", Boolean.TRUE, "FALSE", null, null);
        ExtJSTreeModel jobAllotment = new ExtJSTreeModel("8", "Job Allotment", "assignToTeamIcon", "Job Allotment", Boolean.TRUE, "FALSE", null, null);
        ExtJSTreeModel uploadSearchResume = new ExtJSTreeModel("9", "Upload & Search Resume", "wordIcon", "Upload Resume", Boolean.TRUE, "FALSE", null, null);
        ExtJSTreeModel advanceSearch = new ExtJSTreeModel("10", "Advance Search", "wordIcon", "Advance Search", Boolean.TRUE, "FALSE", null, null);
        ExtJSTreeModel sendResume = new ExtJSTreeModel("11", "Pipeline Status", "greenBallIcon", "Search Resume", Boolean.TRUE, "FALSE", null, null);
        ExtJSTreeModel emailTemplateMessage = new ExtJSTreeModel("12", "Email Messaage", "pipelineProcessingIcon", "Template Message", Boolean.TRUE, "FALSE", null, null);
        ExtJSTreeModel openingsByEmployee = new ExtJSTreeModel("13", "Opening List", "openingListIcon", "Opening List", Boolean.TRUE, "FALSE", null, null);
        ExtJSTreeModel employeeReport = new ExtJSTreeModel("14", "Employee Wise Report", "greenBallIcon", "Employee Wise Report", Boolean.TRUE, "FALSE", null, null);
        ExtJSTreeModel emailConfig = new ExtJSTreeModel("15", "Email Configuration", "toolsIcon", "Email Configuration", Boolean.TRUE, "FALSE", null, null);
        ExtJSTreeModel massMailStatus = new ExtJSTreeModel("16", "Mass Mail Status", "settingIcon", "Mass Mail Status", Boolean.TRUE, "FALSE", null, null);
        ExtJSTreeModel excelUpload = new ExtJSTreeModel("17", "Excel Upload", "excelIcon", "Excel Upload", Boolean.TRUE, "FALSE", null, null);
        
        if(employeeType.equals(EmployeeType.CEO)){
            rootList.add(branch);
            rootList.add(designation);
            rootList.add(workstation);
            rootList.add(employee);
            rootList.add(team);
            rootList.add(client);
            rootList.add(openings);
            /*
             * CEO Does not have authority to Job Allotment
             */
            //rootList.add(jobAllotment);
            rootList.add(uploadSearchResume);
            rootList.add(advanceSearch);
            rootList.add(massMailStatus);
            rootList.add(sendResume);
            rootList.add(employeeReport);
            rootList.add(emailTemplateMessage);
            rootList.add(emailConfig);
            rootList.add(excelUpload);
        }
        if(employeeType.equals(EmployeeType.BranchHead)){
            rootList.add(employee);
            rootList.add(team);
            rootList.add(client);
            rootList.add(openings);
            rootList.add(uploadSearchResume);
            rootList.add(advanceSearch);
            rootList.add(massMailStatus);
            rootList.add(sendResume);
            rootList.add(emailTemplateMessage);
            rootList.add(emailConfig);
            rootList.add(excelUpload);
        }
        if(employeeType.equals(EmployeeType.TeamLeader)){
            rootList.add(jobAllotment);
            rootList.add(uploadSearchResume);
            rootList.add(advanceSearch);
            rootList.add(massMailStatus);
            rootList.add(sendResume);
            rootList.add(emailTemplateMessage);
            rootList.add(emailConfig);
            rootList.add(excelUpload);
        }
        if(employeeType.equals(EmployeeType.Recruiter)){
            rootList.add(openingsByEmployee);
            rootList.add(uploadSearchResume);
            rootList.add(advanceSearch);
            rootList.add(massMailStatus);
            rootList.add(sendResume);
            rootList.add(emailConfig);
        }
        rootMenu.setChildren(rootList);
        return rootMenu;
    }

 

    
    
    
}
