/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.EmailConfig;
import java.util.List;

/**
 *
 * @author Shekhar
 */
public interface EmailConfigDAO {
    
    public EmailConfig create(EmailConfig emailConfig);
    public EmailConfig update(EmailConfig emailConfig);
    public EmailConfig remove(Long emailConfigId);
    public EmailConfig findById(Long emailConfigId);
    public EmailConfig findByEmployeeId(Long employeeId);
    public List<EmailConfig> findAll();
    public List<EmailConfig> findAllByCompanyId(Long companyId);
}
