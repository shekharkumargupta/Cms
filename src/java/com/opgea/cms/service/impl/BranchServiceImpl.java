/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service.impl;

import com.opgea.cms.dao.BranchDAO;
import com.opgea.cms.dao.BranchDetailsDAO;
import com.opgea.cms.dao.CityDAO;
import com.opgea.cms.dao.CompanyDAO;
import com.opgea.cms.dao.CountryDAO;
import com.opgea.cms.dao.EmployeeDAO;
import com.opgea.cms.dao.LoginDAO;
import com.opgea.cms.domain.entities.BranchDetails;
import com.opgea.cms.domain.entities.Branchh;
import com.opgea.cms.domain.entities.City;
import com.opgea.cms.domain.entities.Company;
import com.opgea.cms.domain.entities.Country;
import com.opgea.cms.domain.entities.Employee;
import com.opgea.cms.domain.entities.Login;
import com.opgea.cms.domain.qualifiers.BranchType;
import com.opgea.cms.domain.qualifiers.EmployeeType;
import com.opgea.cms.service.BranchService;
import com.opgea.cms.web.dto.BranchDTO;
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
public class BranchServiceImpl implements BranchService{

    @Autowired
    private BranchDAO branchDAO;
    @Autowired
    private BranchDetailsDAO branchDetailsDAO;
    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private CountryDAO countryDAO;
    @Autowired
    private CityDAO cityDAO;
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private LoginDAO loginDAO;
    
    /**
     * This method is use to create the Branch with
     * branch details.
     * @param branchDTO type of BranchDTO
     * @return BranchDTO
     */
    @Override
    public BranchDTO create(BranchDTO branchDTO) {
        
        Company company = companyDAO.find(branchDTO.getCompanyId());
        City city = cityDAO.find(new Long(branchDTO.getCity()));
        Country country = city.getCountry();
        
        Branchh branch = null;
        BranchDetails branchDetails = new BranchDetails();
        
        if(branchDTO.getBranchId() > 0){
            branch = branchDAO.find(branchDTO.getBranchId());
            branchDetails = branch.getBranchDetails();
        }else{
            branch = new Branchh();
            branch.setCompany(company);
            branchDetails = new BranchDetails();            
        }
        
        branchDetails.setBranchName(branchDTO.getBranchName());
        branchDetails.setBranchType(BranchType.BRANCH_OFFICE.ordinal());
        branchDetails.setStreet1(branchDTO.getStreet1());
        branchDetails.setStreet2(branchDTO.getStreet2());
        branchDetails.setPinCode(branchDTO.getPinCode());
        branchDetails.setCountry(country);
        branchDetails.setCity(city);
        branchDetails.setBranch(branch);
        
        if(branchDTO.getBranchId() > 0){
            branchDetailsDAO.update(branchDetails);        
        }else{
            branchDAO.create(branch);
            branchDetailsDAO.create(branchDetails);
            
            Employee employee = new Employee();
            employee.setFirstName(branchDTO.getFirstName());
            employee.setMiddleInitial(branchDTO.getMiddleInitial());
            employee.setLastName(branchDTO.getLastName());
            employee.setEmail(branchDTO.getEmail());
            employee.setContactNo(branchDTO.getContactNo());
            employee.setBranch(branch);
            employee.setEmployeeType(EmployeeType.BranchHead);
            
            employeeDAO.create(employee);
            
            Login login = new Login();
            login.setLoginId(branchDTO.getEmail());
            login.setPassword(String.valueOf(Calendar.getInstance().getTimeInMillis()));
            login.setIsActive(Boolean.TRUE);
            login.setEmployee(employee);
            loginDAO.create(login);
        }
        
        return branchDTO;
    }

    @Override
    public BranchDTO update(BranchDTO branchDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BranchDTO remove(BranchDTO branchDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BranchDTO find(BranchDTO branchDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BranchDTO> findAll() {
        
        List<Branchh> branches = branchDAO.findAll();
        List<BranchDTO> branchList = new ArrayList<BranchDTO>();
        for(Branchh branch: branches){
            BranchDetails branchDetails = branch.getBranchDetails();
            BranchDTO branchDTO = new BranchDTO();
            branchDTO.setBranchId(branch.getId());
            Employee branchHead = employeeDAO.getBranchHead(branch.getId());
            if(branchDetails != null){
                branchDTO.setBranchType(branchDetails.getBranchType());
                //branchDTO.setBranchTypeString(BranchType.values()[branchDetails.getBranchType()].toString());
                branchDTO.setBranchName(branchDetails.getBranchName());
                if(branchDetails.getStreet1() != null){
                    branchDTO.setStreet1(branchDetails.getStreet1());
                }
                if(branchDetails.getStreet2() != null){
                    branchDTO.setStreet2(branchDetails.getStreet2());
                }
                if(branchDetails.getCity() != null){
                    branchDTO.setCity(branchDetails.getCity().getId());
                    branchDTO.setCityString(branchDetails.getCity().getName());
                }
                if(branchDetails.getCountry() != null){
                    branchDTO.setCountry(branchDetails.getCountry().getId());
                    branchDTO.setCountryString(branchDetails.getCountry().getName());
                }
                if(branchDetails.getPinCode() != null){
                    branchDTO.setPinCode(branchDetails.getPinCode());
                }
            }
            if(branchHead != null) 
            {
                branchDTO.setFirstName(branchHead.getFirstName());
                branchDTO.setMiddleInitial(branchHead.getMiddleInitial());
                branchDTO.setLastName(branchHead.getLastName());
                branchDTO.setEmail(branchHead.getEmail());
                branchDTO.setContactNo(branchHead.getContactNo());
            }
            
            branchList.add(branchDTO);
        }
        return branchList;
    }

    @Override
    public List<BranchDTO> findAllByCompanyId(Long companyId) {
        List<Branchh> branches = branchDAO.findByCompanyId(companyId);
        List<BranchDTO> branchList = new ArrayList<BranchDTO>();
        
        for(Branchh branch: branches){
            BranchDetails branchDetails = branch.getBranchDetails();
            BranchDTO branchDTO = new BranchDTO();
            branchDTO.setBranchId(branch.getId());
            Employee branchHead = employeeDAO.getBranchHead(branch.getId());
            if(branchDetails != null){
                branchDTO.setBranchType(branchDetails.getBranchType());
                //branchDTO.setBranchTypeString(BranchType.values()[branchDetails.getBranchType()].toString());
                branchDTO.setBranchName(branchDetails.getBranchName());
                if(branchDetails.getStreet1() != null){
                    branchDTO.setStreet1(branchDetails.getStreet1());
                }
                if(branchDetails.getStreet2() != null){
                    branchDTO.setStreet2(branchDetails.getStreet2());
                }
                if(branchDetails.getCity() != null){
                    branchDTO.setCity(branchDetails.getCity().getId());
                    branchDTO.setCityString(branchDetails.getCity().getName());
                }
                if(branchDetails.getCountry() != null){
                    branchDTO.setCountry(branchDetails.getCountry().getId());
                    branchDTO.setCountryString(branchDetails.getCountry().getName());
                }
                if(branchDetails.getPinCode() != null){
                    branchDTO.setPinCode(branchDetails.getPinCode());
                }
            }
            if(branchHead != null){
                branchDTO.setFirstName(branchHead.getFirstName());
                branchDTO.setMiddleInitial(branchHead.getMiddleInitial());
                branchDTO.setLastName(branchHead.getLastName());
                branchDTO.setEmail(branchHead.getEmail());
                branchDTO.setContactNo(branchHead.getContactNo());
            }
            
            branchList.add(branchDTO);
        }
        return branchList;
    }
    
    
    
}
