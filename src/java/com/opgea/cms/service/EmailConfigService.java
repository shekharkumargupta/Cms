/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service;

import com.opgea.cms.web.dto.EmailConfigDTO;
import java.util.List;

/**
 *
 * @author Shekhar
 */
public interface EmailConfigService {
    
    public EmailConfigDTO create(EmailConfigDTO emailConfigDTO);
    public EmailConfigDTO update(EmailConfigDTO emailConfigDTO);
    public EmailConfigDTO remove(Long emailConfigId);
    public EmailConfigDTO findById(Long emailConfigId);
    public EmailConfigDTO findByEmployeeId(Long employeeId);
    public List<EmailConfigDTO> findAll();
    //public List<EmailConfigDTO> findAllByCompanyId(Long companyId);
    
}
