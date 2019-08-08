/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service;

import com.opgea.cms.domain.modal.FileUploadBean;
import com.opgea.cms.web.dto.DocumentDTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Shekhar
 */
public interface DocumentService {
    
    public FileUploadBean create(FileUploadBean uploadBean);
    public FileUploadBean update(FileUploadBean uploadBean);
    public FileUploadBean remove(Long documentId);
    public DocumentDTO find(Long documentId);
    public List<DocumentDTO> findAll();
    public List<DocumentDTO> findByResumeId(Long resumeId);
    
    public boolean uploadDocument(FileUploadBean fileUploadBean, HttpServletRequest request);
    
}
