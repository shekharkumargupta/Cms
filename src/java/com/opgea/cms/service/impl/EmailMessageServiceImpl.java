/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service.impl;

import com.opgea.cms.dao.BranchDAO;
import com.opgea.cms.dao.EmailMessageDAO;
import com.opgea.cms.domain.entities.Branchh;
import com.opgea.cms.domain.entities.EmailMessage;
import com.opgea.cms.service.EmailMessageService;
import com.opgea.cms.web.dto.EmailMessageDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class EmailMessageServiceImpl implements EmailMessageService{
    
    @Autowired
    private EmailMessageDAO messageDAO;
    
    @Autowired
    private BranchDAO branchDAO;

    @Override
    public EmailMessageDTO create(EmailMessageDTO messageDTO) {
        
        EmailMessage message = new EmailMessage();
        if(messageDTO.getId() > 0){
            message = messageDAO.findById(messageDTO.getId());
        }
        message.setMessage(messageDTO.getMessage());
        
        if(messageDTO.getId() > 0){
            messageDAO.update(message);
        }else{
            Branchh branch = branchDAO.find(messageDTO.getBranchId());
            message.setBranch(branch);
            messageDAO.create(message);
        }
        return messageDTO;
    }

    @Override
    public EmailMessageDTO update(EmailMessageDTO messageDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EmailMessageDTO findById(Long emailMessageId) {
        EmailMessage message = messageDAO.findById(emailMessageId);
        EmailMessageDTO messageDTO = new EmailMessageDTO();
        if(message != null){
            messageDTO.setId(message.getId());
            messageDTO.setMessage(message.getMessage());
            messageDTO.setBranchId(message.getBranch().getId());
        }
        return messageDTO;
    }

    @Override
    public EmailMessageDTO findByBranchId(Long branchId) {
        EmailMessage message = messageDAO.findByBranchId(branchId);
        EmailMessageDTO messageDTO = new EmailMessageDTO();
        if(message != null){
            messageDTO.setId(message.getId());
            messageDTO.setMessage(message.getMessage());
            messageDTO.setBranchId(message.getBranch().getId());
        }
        return messageDTO;
    }

    @Override
    public List<EmailMessageDTO> findAll() {
        List<EmailMessage> messages = messageDAO.findAll();
        List<EmailMessageDTO> messageDTOList = new ArrayList<EmailMessageDTO>();
        for(EmailMessage message : messages){
            if(message != null){
                EmailMessageDTO messageDTO = new EmailMessageDTO();
                messageDTO.setId(message.getId());
                messageDTO.setMessage(message.getMessage());
                messageDTO.setBranchId(message.getBranch().getId());
            }
        }
        return messageDTOList;
    }
    
}
