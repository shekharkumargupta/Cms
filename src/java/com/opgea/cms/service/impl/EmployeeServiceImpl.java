/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service.impl;

import com.opgea.cms.dao.BranchDAO;
import com.opgea.cms.dao.DesignationDAO;
import com.opgea.cms.dao.EmployeeDAO;
import com.opgea.cms.dao.LoginDAO;
import com.opgea.cms.dao.OpeningDAO;
import com.opgea.cms.domain.entities.Branchh;
import com.opgea.cms.domain.entities.Designation;
import com.opgea.cms.domain.entities.Employee;
import com.opgea.cms.domain.entities.Login;
import com.opgea.cms.domain.entities.Opening;
import com.opgea.cms.domain.modal.ExtJSTreeModel;
import com.opgea.cms.domain.qualifiers.EmployeeType;
import com.opgea.cms.service.EmployeeService;
import com.opgea.cms.web.dto.EmployeeDTO;
import com.opgea.util.DateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDAO employeeDAO;
    
    @Autowired
    private BranchDAO branchDAO;
    
    @Autowired
    private DesignationDAO designationDAO;
    
    @Autowired
    private LoginDAO loginDAO;
    
    @Autowired
    private OpeningDAO openingDAO;
    
    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        Employee employee = null;
        Branchh branch = branchDAO.find(employeeDTO.getBranchId());
        Designation designation = designationDAO.find(employeeDTO.getDesignationId());
        System.out.println("Designation: "+designation);
        if(employeeDTO.getId() > 0){
            employee = employeeDAO.find(employeeDTO.getId());
        }else{
            employee = new Employee();
        }
        
        employee.setEmployeeType(EmployeeType.values()[employeeDTO.getEmployeeTypeId()]);
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setMiddleInitial(employeeDTO.getMiddleInitial());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setContactNo(employeeDTO.getPhone1());
        employee.setBranch(branch);
        employee.setDesignation(designation);
        if(employeeDTO.getDateOfBirth() != null){
            employee.setDateOfBirth(DateUtil.getDateFromYYYYMMDD(employeeDTO.getDateOfBirth(), "-"));
        }
        if(employeeDTO.getDateOfAnniversary() != null){
            employee.setDateOfAnniversary(DateUtil.getDateFromYYYYMMDD(employeeDTO.getDateOfAnniversary(), "-"));
        }

        if(employeeDTO.getId() > 0){
            employeeDAO.update(employee);
        }else{
            employeeDAO.create(employee);
            Login login = new Login();
            login.setLoginId(employeeDTO.getEmail());
            login.setPassword(String.valueOf(Calendar.getInstance().getTimeInMillis()));
            login.setIsActive(Boolean.TRUE);
            login.setEmployee(employee);
            loginDAO.create(login);
        }

        return employeeDTO;
    }

    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EmployeeDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EmployeeDTO find(Long id) {
        Employee employee = employeeDAO.find(id);
        Branchh branch = employee.getBranch();
        Login login = loginDAO.findByEmployeeId(id);
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setEmployeeTypeId(employee.getEmployeeType().ordinal());
        employeeDTO.setEmployeeTypeName(employee.getEmployeeType().name());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setMiddleInitial(employee.getMiddleInitial());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPassword(login.getPassword());
        if(employee.getDateOfBirth() != null){
            employeeDTO.setDateOfBirth(DateUtil.getYYYYMMDDFromDate(employee.getDateOfBirth(), "-"));
        }
        if(employee.getDateOfAnniversary() != null){
            employeeDTO.setDateOfAnniversary(DateUtil.getYYYYMMDDFromDate(employee.getDateOfAnniversary(), "-"));
        }
        //employeeDTO.setContactNo();
        if(employee.getTeam() != null){
            employeeDTO.setTeamId(employee.getTeam().getId());
            employeeDTO.setTeamName(employee.getTeam().getName());
        }
        if(branch != null){
            employeeDTO.setBranchId(branch.getId());
            employeeDTO.setBranchName(branch.getBranchDetails().getBranchName());
        }
        if(branch.getCompany() != null){
            employeeDTO.setCompanyId(branch.getCompany().getId());
            employeeDTO.setCompanyName(branch.getCompany().getCompanyName());
        }
        return employeeDTO;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> employees = employeeDAO.findAll();
        List<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();
        for(Employee employee : employees){
            Branchh branch = employee.getBranch();
            Login login = loginDAO.findByEmployeeId(employee.getId());
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setEmployeeTypeId(employee.getEmployeeType().ordinal());
            employeeDTO.setEmployeeTypeName(employee.getEmployeeType().name());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setMiddleInitial(employee.getMiddleInitial());
            employeeDTO.setLastName(employee.getLastName());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setPhone1(employee.getContactNo());
            employeeDTO.setPassword(login.getPassword());
            if(employee.getDateOfBirth() != null){
                employeeDTO.setDateOfBirth(DateUtil.getYYYYMMDDFromDate(employee.getDateOfBirth(), "-"));
            }
            if(employee.getDateOfAnniversary() != null){
                employeeDTO.setDateOfAnniversary(DateUtil.getYYYYMMDDFromDate(employee.getDateOfAnniversary(), "-"));
            }
            if(employee.getDesignation() != null){
                employeeDTO.setDesignationId(employee.getDesignation().getId());
                employeeDTO.setDesignationName(employee.getDesignation().getName());
            }
            if(employee.getTeam() != null){
                employeeDTO.setTeamId(employee.getTeam().getId());
                employeeDTO.setTeamName(employee.getTeam().getName());
            }
            if(branch != null){
                employeeDTO.setBranchId(branch.getId());
                employeeDTO.setBranchName(branch.getBranchDetails().getBranchName());
            }
            if(branch.getCompany() != null){
                employeeDTO.setCompanyId(branch.getCompany().getId());
                employeeDTO.setCompanyName(branch.getCompany().getCompanyName());
            }
            employeeList.add(employeeDTO);
        }
        return employeeList;
    }

     @Override
    public List<EmployeeDTO> findAllByCompanyId(Long companyId) {
        List<Employee> employees = employeeDAO.findAllByCompanyId(companyId);
        List<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();
        for(Employee employee : employees){
            Branchh branch = employee.getBranch();
            Login login = loginDAO.findByEmployeeId(employee.getId());
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setEmployeeTypeId(employee.getEmployeeType().ordinal());
            employeeDTO.setEmployeeTypeName(employee.getEmployeeType().name());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setMiddleInitial(employee.getMiddleInitial());
            employeeDTO.setLastName(employee.getLastName());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setPhone1(employee.getContactNo());
            employeeDTO.setPassword(login.getPassword());
            if(employee.getDateOfBirth() != null){
                employeeDTO.setDateOfBirth(DateUtil.getYYYYMMDDFromDate(employee.getDateOfBirth(), "-"));
            }
            if(employee.getDateOfAnniversary() != null){
                employeeDTO.setDateOfAnniversary(DateUtil.getYYYYMMDDFromDate(employee.getDateOfAnniversary(), "-"));
            }
            if(employee.getDesignation() != null){
                employeeDTO.setDesignationId(employee.getDesignation().getId());
                employeeDTO.setDesignationName(employee.getDesignation().getName());
            }
            if(employee.getTeam() != null){
                employeeDTO.setTeamId(employee.getTeam().getId());
                employeeDTO.setTeamName(employee.getTeam().getName());
            }
            if(branch != null){
                employeeDTO.setBranchId(branch.getId());
                employeeDTO.setBranchName(branch.getBranchDetails().getBranchName());
            }
            if(branch.getCompany() != null){
                employeeDTO.setCompanyId(branch.getCompany().getId());
                employeeDTO.setCompanyName(branch.getCompany().getCompanyName());
            }
            employeeList.add(employeeDTO);
        }
        return employeeList;
    }
     
    @Override
    public List<EmployeeDTO> findAllByBranchId(Long branchId) {
        List<Employee> employees = employeeDAO.findAllByBranchId(branchId);
        List<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();
        for(Employee employee : employees){
            Branchh branch = employee.getBranch();
            Login login = loginDAO.findByEmployeeId(employee.getId());
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setEmployeeTypeId(employee.getEmployeeType().ordinal());
            employeeDTO.setEmployeeTypeName(employee.getEmployeeType().name());
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setMiddleInitial(employee.getMiddleInitial());
            employeeDTO.setLastName(employee.getLastName());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setPhone1(employee.getContactNo());
            employeeDTO.setPassword(login.getPassword());
            if(employee.getDateOfBirth() != null){
                employeeDTO.setDateOfBirth(DateUtil.getYYYYMMDDFromDate(employee.getDateOfBirth(), "-"));
            }
            if(employee.getDateOfAnniversary() != null){
                employeeDTO.setDateOfAnniversary(DateUtil.getYYYYMMDDFromDate(employee.getDateOfAnniversary(), "-"));
            }
            if(employee.getDesignation() != null){
                employeeDTO.setDesignationId(employee.getDesignation().getId());
                employeeDTO.setDesignationName(employee.getDesignation().getName());
            }
            if(employee.getTeam() != null){
                employeeDTO.setTeamId(employee.getTeam().getId());
                employeeDTO.setTeamName(employee.getTeam().getName());
            }
            if(branch != null){
                employeeDTO.setBranchId(branch.getId());
                employeeDTO.setBranchName(branch.getBranchDetails().getBranchName());
            }
            if(branch.getCompany() != null){
                employeeDTO.setCompanyId(branch.getCompany().getId());
                employeeDTO.setCompanyName(branch.getCompany().getCompanyName());
            }
            employeeList.add(employeeDTO);
        }
        return employeeList;
    }

    @Override
    public ExtJSTreeModel findAllEmployeeAndOpeningByTeamId(Long teamId) {
        ExtJSTreeModel rootMenu = new ExtJSTreeModel();
        rootMenu.setId("0");
        rootMenu.setText("Members");
        rootMenu.setDescription("Setting Basics of Application");
        rootMenu.setLeaf(false);
        rootMenu.setExpanded("true");
        rootMenu.setIconCls("teamIcon");
        List<ExtJSTreeModel> rootList = new ArrayList<ExtJSTreeModel>();

        List<Employee> empList = employeeDAO.findAllByTeamId(teamId);
        for(Employee emp: empList){
            List<ExtJSTreeModel> openingList = new ArrayList<ExtJSTreeModel>();
            //List<Opening> openings = emp.getOpenings();
            List<Opening> openings = openingDAO.findAllByEmployeeId(emp.getId());
            for(Opening opening : openings){
                ExtJSTreeModel openingModel = new ExtJSTreeModel(String.valueOf(emp.getId()+"_"+opening.getId()), String.valueOf(opening.getId()), "openingIcon", opening.getPositionName(), Boolean.TRUE, "FALSE", opening.getPositionName(), null);
                if(rootList.contains(openingModel) == true){
                    rootList.remove(openingModel);
                }
                openingList.add(openingModel);
            }
            StringBuilder name = new StringBuilder();
            name.append(emp.getFirstName());
            name.append(" ");
            name.append(emp.getMiddleInitial());
            name.append(" ");
            name.append(emp.getLastName());
            if(emp.getEmployeeType().ordinal() == EmployeeType.TeamLeader.ordinal()){
                name.append(" <b>TL</b>");
            }
            ExtJSTreeModel teamModel = new ExtJSTreeModel(String.valueOf(emp.getId()), name.toString()+" <b>"+openingList.size()+"</b> ", "userIcon", "", Boolean.FALSE, "FALSE", "", openingList);
            rootList.add(teamModel);
        }
        rootMenu.setChildren(rootList);
        return rootMenu;
    }

    @Override
    public void addOpening(Long employeeId, Long openingId) {
        openingDAO.assignOpeningToEmployee(employeeId, openingId);
    }

    

    
}
