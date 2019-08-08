/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service;

import com.opgea.cms.web.dto.EmailMessageDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface EmailMessageService {
    
    public EmailMessageDTO create(EmailMessageDTO messageDTO);
    public EmailMessageDTO update(EmailMessageDTO messageDTO);
    public EmailMessageDTO findById(Long emailMessageId);
    public EmailMessageDTO findByBranchId(Long branchId);
    public List<EmailMessageDTO> findAll();
}
