/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service.impl;

import com.opgea.cms.dao.BranchDAO;
import com.opgea.cms.dao.BranchDetailsDAO;
import com.opgea.cms.dao.CompanyDAO;
import com.opgea.cms.dao.EmployeeDAO;
import com.opgea.cms.dao.LoginDAO;
import com.opgea.cms.domain.entities.Branchh;
import com.opgea.cms.domain.entities.BranchDetails;
import com.opgea.cms.domain.entities.Company;
import com.opgea.cms.domain.entities.Employee;
import com.opgea.cms.domain.entities.Login;
import com.opgea.cms.domain.modal.MailModel;
import com.opgea.cms.domain.qualifiers.BranchType;
import com.opgea.cms.domain.qualifiers.EmployeeType;
import com.opgea.cms.service.CompanyService;
import com.opgea.cms.service.mail.MailService;
import com.opgea.cms.web.dto.CompanyDTO;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private BranchDAO branchDAO;
    @Autowired
    private BranchDetailsDAO branchDetailsDAO;
    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private LoginDAO loginDAO;
    @Autowired
    private MailService mailService;
    
    @Override
    public CompanyDTO create(CompanyDTO companyDTO) {
        
        Company company = new Company();
        company.setCompanyName(companyDTO.getName());
        company.setEmail(companyDTO.getEmail());
        company.setWebsite(companyDTO.getWebsite());
        company.setContactNo(companyDTO.getContactNo());
        companyDAO.create(company);
        
        Branchh branch = new Branchh();
        branch.setCompany(company);
        //branch.addEmployee(employee);
        branchDAO.create(branch);
        
        BranchDetails branchDetails = new BranchDetails();
        branchDetails.setBranchName(companyDTO.getName());
        branchDetails.setBranchType(BranchType.HEAD_OFFICE.ordinal());
        branchDetails.setBranch(branch);
        branchDetailsDAO.create(branchDetails);
        
        Employee employee = new Employee();
        employee.setFirstName(companyDTO.getFirstName());
        employee.setMiddleInitial(companyDTO.getMiddleInitial());
        employee.setLastName(companyDTO.getLastName());
        employee.setEmail(companyDTO.getEmail());
        employee.setContactNo(companyDTO.getContactNo());
        employee.setBranch(branch);
        employee.setEmployeeType(EmployeeType.CEO);
        employeeDAO.create(employee);
        
        Login login = new Login();
        login.setLoginId(companyDTO.getEmail());
        login.setPassword(String.valueOf(System.currentTimeMillis()));
        login.setEmployee(employee);
        login.setIsActive(true);
        loginDAO.create(login);
        
        company.addBranch(branch);
        companyDAO.update(company);
        branch.addEmployee(employee);
        branchDAO.update(branch);
        
         /*
         * OPGEA sending mail to the newly registered company;
         */
        
        MailModel mailModel = new MailModel();
        mailModel.setTo(companyDTO.getEmail());
        mailModel.setFrom("OPGEA");
        mailModel.setSubject("OPGEA Recruitment Managment");
        mailModel.setMessage("<html><b>Congratulations!</b> <br><br>" +
                             "Your Login Id has been created for <b>OPGEA Recruitment Management</b> Application as <br>" +
                             "<b>User Id : </b>"+login.getLoginId()+"<br> " +
                             "<b>Password : </b>"+login.getPassword()+"<br> " +
                             "Click on this link to Login: <a href='http://opgea.com/Cms/app/login'>Login</login>" +
                             "</html>");
        try {
            mailService.sendMailToCompany(mailModel);
           } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CompanyServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        return companyDTO;
    }

    @Override
    public CompanyDTO update(CompanyDTO companyDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CompanyDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CompanyDTO find(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<CompanyDTO> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
