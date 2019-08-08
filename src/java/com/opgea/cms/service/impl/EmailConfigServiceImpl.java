/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service.impl;

import com.opgea.cms.dao.BranchDAO;
import com.opgea.cms.dao.EmailConfigDAO;
import com.opgea.cms.dao.EmployeeDAO;
import com.opgea.cms.domain.entities.EmailConfig;
import com.opgea.cms.domain.entities.Employee;
import com.opgea.cms.service.EmailConfigService;
import com.opgea.cms.web.dto.EmailConfigDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Shekhar
 */
@Service
public class EmailConfigServiceImpl implements EmailConfigService{
    
    @Autowired
    private EmailConfigDAO emailConfigDAO;
    
    @Autowired
    private BranchDAO branchDAO;
    
    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public EmailConfigDTO create(EmailConfigDTO emailConfigDTO) {
         
        EmailConfig config = new EmailConfig();
        if(emailConfigDTO.getId() > 0){
            config = emailConfigDAO.findById(emailConfigDTO.getId());
        }
        config.setEmailId(emailConfigDTO.getEmailId());
        config.setPassword(emailConfigDTO.getPassword());
        config.setSmtpHost(emailConfigDTO.getSmtpHost());
        config.setPort(emailConfigDTO.getPortNo());
        
        if(emailConfigDTO.getId() > 0){
            emailConfigDAO.update(config);
        }
        
        else{
            Employee employee = employeeDAO.find(emailConfigDTO.getEmployeeId());
            config.setEmployee(employee);
            emailConfigDAO.create(config);
        }
        return emailConfigDTO;
    }

    @Override
    public EmailConfigDTO update(EmailConfigDTO emailConfigDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EmailConfigDTO remove(Long emailConfigId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EmailConfigDTO findById(Long emailConfigId) {
        EmailConfig config = emailConfigDAO.findById(emailConfigId);
        EmailConfigDTO configDTO = new EmailConfigDTO();
        Employee employee = config.getEmployee();
        configDTO.setId(config.getId());
        configDTO.setEmailId(config.getEmailId());
        configDTO.setPassword(config.getPassword());
        configDTO.setSmtpHost(config.getSmtpHost());
        configDTO.setPortNo(config.getPort());
        configDTO.setEmployeeId(employee.getId());
        configDTO.setEmployeeName(employee.getFirstName()+" "+employee.getMiddleInitial()+" "+employee.getLastName());
        return configDTO;
    }

    @Override
    public EmailConfigDTO findByEmployeeId(Long employeeId) {
        EmailConfig config = emailConfigDAO.findByEmployeeId(employeeId);
        EmailConfigDTO configDTO = new EmailConfigDTO();
        Employee employee = config.getEmployee();
        configDTO.setId(config.getId());
        configDTO.setEmailId(config.getEmailId());
        configDTO.setPassword(config.getPassword());
        configDTO.setSmtpHost(config.getSmtpHost());
        configDTO.setPortNo(config.getPort());
        configDTO.setEmployeeId(employee.getId());
        configDTO.setEmployeeName(employee.getFirstName()+" "+employee.getMiddleInitial()+" "+employee.getLastName());
        return configDTO;
    }

    @Override
    public List<EmailConfigDTO> findAll() {
        List<EmailConfig> configs = emailConfigDAO.findAll();
        List<EmailConfigDTO> configDTOList = new ArrayList<EmailConfigDTO>();
        for(EmailConfig config : configs){
            EmailConfigDTO configDTO = new EmailConfigDTO();
            Employee employee = config.getEmployee();
            configDTO.setId(config.getId());
            configDTO.setEmailId(config.getEmailId());
            configDTO.setPassword(config.getPassword());
            configDTO.setSmtpHost(config.getSmtpHost());
            configDTO.setPortNo(config.getPort());
            configDTO.setEmployeeId(employee.getId());
            configDTO.setEmployeeName(employee.getFirstName()+" "+employee.getMiddleInitial()+" "+employee.getLastName());
            configDTOList.add(configDTO);
        }
        return configDTOList;
    }

    /*
    @Override
    public List<EmailConfigDTO> findAllByCompanyId(Long companyId) {
        List<EmailConfig> configs = emailConfigDAO.findAllByCompanyId(companyId);
        List<EmailConfigDTO> configDTOList = new ArrayList<EmailConfigDTO>();
        for(EmailConfig config : configs){
            EmailConfigDTO configDTO = new EmailConfigDTO();
            Employee employee = config.getEmployee();
            configDTO.setId(config.getId());
            configDTO.setEmailId(config.getEmailId());
            configDTO.setPassword(config.getPassword());
            configDTO.setSmtpHost(config.getSmtpHost());
            configDTO.setPortNo(config.getPort());
            configDTO.setEmployeeId(employee.getId());
            configDTO.setEmployeeName(employee.getFirstName()+" "+employee.getMiddleInitial()+" "+employee.getLastName());
            configDTOList.add(configDTO);
        }
        return configDTOList;
    }
     *
     */
    
}
