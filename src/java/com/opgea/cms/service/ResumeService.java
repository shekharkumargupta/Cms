/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service;

import com.opgea.cms.domain.modal.FileUploadBean;
import com.opgea.cms.web.dto.AdvanceSearchResumeDTO;
import com.opgea.cms.web.dto.ResumeDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author Ramesh
 */
public interface ResumeService {
    
    public ResumeDTO create(ResumeDTO resumeDTO);
    public int createBatch(List<ResumeDTO> resumeDTOList, Long branchId, Long updatedById);
    public ResumeDTO update(ResumeDTO resumeDTO);
    public ResumeDTO remove(Long resumeId);
    public ResumeDTO find(Long resumeId);
    public ResumeDTO find(String emailId);
    public List<ResumeDTO> findAllByEmailIds(String commaSeperatedEmailIds);
    public List<ResumeDTO> findAll();
    public List<ResumeDTO> findAllByOpeningId(Long openingId);
    public List<ResumeDTO> findBySearchKey(String searchKey, Long branchId);
    public List<ResumeDTO> advanceSearch(AdvanceSearchResumeDTO searchDTO);
    
    public boolean isExistingResume(String emailId, String contactNo, Long branchId);
    public boolean uploadResume(FileUploadBean fileUploadBean, String fileName, HttpServletRequest request);
    public String convertWordToHTML(String wordFilePath) throws IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException;
    
}
