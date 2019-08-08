/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service.impl;

import com.opgea.cms.dao.EmployeeDAO;
import com.opgea.cms.dao.MassMailDAO;
import com.opgea.cms.dao.OpeningDAO;
import com.opgea.cms.dao.ResumeDAO;
import com.opgea.cms.domain.entities.Employee;
import com.opgea.cms.domain.entities.MassMail;
import com.opgea.cms.domain.entities.Opening;
import com.opgea.cms.domain.entities.Resume;
import com.opgea.cms.domain.qualifiers.MassMailStatus;
import com.opgea.cms.service.MassMailService;
import com.opgea.cms.web.dto.MassMailDTO;
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
public class MassMailServiceImpl implements MassMailService{
    
    @Autowired
    private MassMailDAO massMailDAO;
    
    @Autowired
    private OpeningDAO openingDAO;
    
    @Autowired
    private EmployeeDAO employeeDAO;
    
    @Autowired
    private ResumeDAO resumeDAO;
    
    @Override
    public void create(List<MassMailDTO> massMailDTOList, Long openingId, Long senderId) {
        
        
        Opening opening = openingDAO.find(openingId);
        Employee sender = employeeDAO.find(senderId);
        
        List<MassMail> massMailList = new ArrayList<MassMail>();
        for(MassMailDTO dto : massMailDTOList){
            MassMail massMail = new MassMail();
            massMail.setEmail(dto.getEmail());
            massMail.setStatus(MassMailStatus.SEND_MAIL.name());
            massMail.setOpening(opening);
           
            massMail.setSentBy(sender);
            massMail.setSentAt(Calendar.getInstance().getTime());
            massMailList.add(massMail);
        }
        if(massMailList.size() > 0){
            massMailDAO.create(massMailList);
        }
    }

    @Override
    public List<MassMailDTO> findAll() {
        List<MassMail> massMailList = massMailDAO.findAll();
        List<MassMailDTO> massMailDTOList = new ArrayList<MassMailDTO>();
        for(MassMail mail : massMailList){
            MassMailDTO dto = new MassMailDTO();
            Opening opening = mail.getOpening();
            Employee sender = mail.getSentBy();
            Resume resume = resumeDAO.find(dto.getEmail());
            dto.setId(mail.getId());
            dto.setEmail(mail.getEmail());
            dto.setOpeningId(opening.getId());
            dto.setPositionName(opening.getPositionName());
            dto.setSentById(sender.getId());
            dto.setSenderName(sender.getFirstName()+" "+sender.getMiddleInitial()+" "+sender.getLastName());
            dto.setSentAt(DateUtil.getDateTimeString(mail.getSentAt().getTime()));
            dto.setStatus(mail.getStatus());
            if(resume != null){
                dto.setResumeId(resume.getId());
                dto.setName(resume.getName());
                dto.setRemarks(resume.getRemarks());
            }
            dto.setBranchId(opening.getBranch().getId());
            dto.setCompanyId(opening.getBranch().getCompany().getId());
            massMailDTOList.add(dto);
        }
        return massMailDTOList;
    }

    @Override
    public MassMailDTO findById(Long massMailId) {
        MassMail mail = massMailDAO.findById(massMailId);
        MassMailDTO dto = new MassMailDTO();
        Opening opening = mail.getOpening();
        Employee sender = mail.getSentBy();
        Resume resume = resumeDAO.find(dto.getEmail());
        dto.setId(mail.getId());
        dto.setEmail(mail.getEmail());
        dto.setOpeningId(opening.getId());
        dto.setPositionName(opening.getPositionName());
        dto.setSentById(sender.getId());
        dto.setSenderName(sender.getFirstName()+" "+sender.getMiddleInitial()+" "+sender.getLastName());
        dto.setSentAt(DateUtil.getDateTimeString(mail.getSentAt().getTime()));
        dto.setStatus(mail.getStatus());
        if(resume != null){
            dto.setResumeId(resume.getId());
            dto.setName(resume.getName());
            dto.setRemarks(resume.getRemarks());
        }
        dto.setBranchId(opening.getBranch().getId());
        dto.setCompanyId(opening.getBranch().getCompany().getId());
        return dto;
    }

    @Override
    public List<MassMailDTO> findAllByOpeningId(Long openingId) {
        List<MassMail> massMailList = massMailDAO.findAllByOpeningId(openingId);
        List<MassMailDTO> massMailDTOList = new ArrayList<MassMailDTO>();
        for(MassMail mail : massMailList){
            MassMailDTO dto = new MassMailDTO();
            Opening opening = mail.getOpening();
            Employee sender = mail.getSentBy();
            Resume resume = resumeDAO.find(dto.getEmail());
            dto.setId(mail.getId());
            dto.setEmail(mail.getEmail());
            dto.setOpeningId(opening.getId());
            dto.setPositionName(opening.getPositionName());
            dto.setSentById(sender.getId());
            dto.setSenderName(sender.getFirstName()+" "+sender.getMiddleInitial()+" "+sender.getLastName());
            dto.setSentAt(DateUtil.getDateTimeString(mail.getSentAt().getTime()));
            dto.setStatus(mail.getStatus());
            if(resume != null){
                dto.setResumeId(resume.getId());
                dto.setName(resume.getName());
                dto.setRemarks(resume.getRemarks());
            }
            dto.setBranchId(opening.getBranch().getId());
            dto.setCompanyId(opening.getBranch().getCompany().getId());
            massMailDTOList.add(dto);
        }
        return massMailDTOList;
    }

    @Override
    public List<MassMailDTO> findAllByOpeningAndSenderId(Long openingId, Long senderId) {
        List<MassMail> massMailList = massMailDAO.findAllByOpeningAndSenderId(openingId, senderId);
        List<MassMailDTO> massMailDTOList = new ArrayList<MassMailDTO>();
        for(MassMail mail : massMailList){
            MassMailDTO dto = new MassMailDTO();
            Opening opening = mail.getOpening();
            Employee sender = mail.getSentBy();
            Resume resume = resumeDAO.find(mail.getEmail());
            
            dto.setId(mail.getId());
            dto.setEmail(mail.getEmail());
            dto.setOpeningId(opening.getId());
            dto.setPositionName(opening.getPositionName());
            dto.setSentById(sender.getId());
            dto.setSenderName(sender.getFirstName()+" "+sender.getMiddleInitial()+" "+sender.getLastName());
            dto.setSentAt(DateUtil.getDateTimeString(mail.getSentAt().getTime()));
            dto.setStatus(mail.getStatus());
            if(resume != null){
                dto.setResumeId(resume.getId());
                dto.setName(resume.getName());
                dto.setRemarks(resume.getRemarks());
            }
            dto.setBranchId(opening.getBranch().getId());
            dto.setCompanyId(opening.getBranch().getCompany().getId());
            massMailDTOList.add(dto);
        }
        return massMailDTOList;
    }
    
}
