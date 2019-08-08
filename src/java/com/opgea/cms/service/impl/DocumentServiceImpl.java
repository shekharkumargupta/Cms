/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service.impl;

import com.opgea.cms.dao.DocumentDAO;
import com.opgea.cms.dao.EmployeeDAO;
import com.opgea.cms.dao.ResumeDAO;
import com.opgea.cms.domain.entities.Branchh;
import com.opgea.cms.domain.entities.Document;
import com.opgea.cms.domain.entities.Employee;
import com.opgea.cms.domain.entities.Resume;
import com.opgea.cms.domain.modal.FileUploadBean;
import com.opgea.cms.domain.qualifiers.DocumentType;
import com.opgea.cms.service.DocumentService;
import com.opgea.cms.web.dto.DocumentDTO;
import com.opgea.constraints.ApplicationConstraints;
import com.opgea.util.DateUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Shekhar
 */
@Service
public class DocumentServiceImpl implements DocumentService{
    
    @Autowired
    private DocumentDAO documentDAO;
    
    @Autowired
    private EmployeeDAO employeeDAO;
    
    @Autowired
    private ResumeDAO resumeDAO;

    @Override
    public FileUploadBean create(FileUploadBean uploadBean) {
        
        Employee uploadedBy = employeeDAO.find(uploadBean.getUploadedById());
        Resume resume = resumeDAO.find(uploadBean.getResumeId());
        Document document = new Document();
        
        if(uploadBean.getId() != null && uploadBean.getId() > 0){
            document = documentDAO.find(uploadBean.getId());
        }
        MultipartFile file = uploadBean.getFile();
        document.setDocumentType(DocumentType.values()[uploadBean.getDocumentTypeId()]);
        document.setFileName(file.getOriginalFilename());
        document.setFileSize(file.getSize());
        document.setFileType(file.getContentType());
        document.setUploadedBy(uploadedBy);
        document.setUploadedAt(Calendar.getInstance().getTime());
        document.setResume(resume);
        
        if(uploadBean.getId() != null && uploadBean.getId() > 0){
            documentDAO.update(document);
        }else{
            documentDAO.create(document);
        }
        
        uploadBean.setId(document.getId());
        return uploadBean;
    }

    @Override
    public FileUploadBean update(FileUploadBean uploadBean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FileUploadBean remove(Long documentId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public DocumentDTO find(Long documentId) {
        Document document = documentDAO.find(documentId);
        Employee uploadedBy = document.getUploadedBy();
        Branchh branch = uploadedBy.getBranch();
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(document.getId());
        documentDTO.setResumeId(document.getResume().getId());
        documentDTO.setDocumentTypeId(document.getDocumentType().ordinal());
        documentDTO.setDocumentTypeName(document.getDocumentType().name());
        documentDTO.setFileName(document.getFileName());
        documentDTO.setFileType(document.getFileType());
        if(document.getFileSize() > 1024){
            documentDTO.setFileSize(document.getFileSize()/1024);
        }else{
            document.setFileSize(1L);
        }
        documentDTO.setUpdatedAt(DateUtil.getYYYYMMDDFromDate(document.getUploadedAt(), "-"));
        documentDTO.setUploadedById(uploadedBy.getId());
        documentDTO.setBranchId(branch.getId());
        documentDTO.setCompanyId(branch.getCompany().getId());
        
        return documentDTO;
    }

    @Override
    public List<DocumentDTO> findAll() {
        List<Document> documentList = documentDAO.findAll();
        List<DocumentDTO> documentDTOList = new ArrayList<DocumentDTO>();
        for(Document document : documentList){
            Employee uploadedBy = document.getUploadedBy();
            Branchh branch = uploadedBy.getBranch();
            DocumentDTO documentDTO = new DocumentDTO();
            documentDTO.setId(document.getId());
            documentDTO.setResumeId(document.getResume().getId());
            documentDTO.setDocumentTypeId(document.getDocumentType().ordinal());
            documentDTO.setDocumentTypeName(document.getDocumentType().name());
            documentDTO.setFileName(document.getFileName());
            documentDTO.setFileType(document.getFileType());
            if(document.getFileSize() > 1024){
                documentDTO.setFileSize(document.getFileSize()/1024);
            }else{
                document.setFileSize(1L);
            }
            documentDTO.setUpdatedAt(DateUtil.getYYYYMMDDFromDate(document.getUploadedAt(), "-"));
            documentDTO.setUploadedById(uploadedBy.getId());
            documentDTO.setBranchId(branch.getId());
            documentDTO.setCompanyId(branch.getCompany().getId());
            documentDTOList.add(documentDTO);
        }
        return documentDTOList;
    }

    @Override
    public List<DocumentDTO> findByResumeId(Long resumeId) {
        List<Document> documentList = documentDAO.findAllByResumeId(resumeId);
        List<DocumentDTO> documentDTOList = new ArrayList<DocumentDTO>();
        for(Document document : documentList){
            Employee uploadedBy = document.getUploadedBy();
            Branchh branch = uploadedBy.getBranch();
            DocumentDTO documentDTO = new DocumentDTO();
            documentDTO.setId(document.getId());
            documentDTO.setResumeId(document.getResume().getId());
            documentDTO.setDocumentTypeId(document.getDocumentType().ordinal());
            documentDTO.setDocumentTypeName(document.getDocumentType().name());
            documentDTO.setFileName(document.getFileName());
            documentDTO.setFileType(document.getFileType());
            if(document.getFileSize() > 1024){
                documentDTO.setFileSize(document.getFileSize()/1024);
            }else{
                document.setFileSize(1L);
            }
            documentDTO.setUpdatedAt(DateUtil.getYYYYMMDDFromDate(document.getUploadedAt(), "-"));
            documentDTO.setUploadedById(uploadedBy.getId());
            documentDTO.setBranchId(branch.getId());
            documentDTO.setCompanyId(branch.getCompany().getId());
            documentDTOList.add(documentDTO);
        }
        return documentDTOList;
    }

    @Override
    public boolean uploadDocument(FileUploadBean fileUploadBean, HttpServletRequest request) {
        boolean status = false;
        String path = ApplicationConstraints.getResumeFolderLocation(request);
        File folder = new File(path);
        if(folder.exists() == false){
            folder.mkdirs();
        }
        try {
            MultipartFile file = fileUploadBean.getFile();
            path = path+"/"+file.getOriginalFilename();
            InputStream inputStream = null;
            OutputStream outputStream = null;
            if (file.getSize() > 0) {
                    inputStream = file.getInputStream();
                    if (file.getSize() > 10000) {
                            //return "/uploadfile";
                    }
                    outputStream = new FileOutputStream(path);
                    int readBytes = 0;
                    byte[] buffer = new byte[10000];
                    while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                            outputStream.write(buffer, 0, readBytes);
                    }
                    outputStream.close();
                    inputStream.close();
                    status = true;       
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }
    
}
