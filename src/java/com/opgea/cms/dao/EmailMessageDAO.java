/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.EmailMessage;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface EmailMessageDAO {
    
    public EmailMessage create(EmailMessage emailMessage);
    public EmailMessage update(EmailMessage emailMessage);
    public EmailMessage findById(Long id);
    public EmailMessage findByBranchId(Long branchId);
    public List<EmailMessage> findAll();
}
