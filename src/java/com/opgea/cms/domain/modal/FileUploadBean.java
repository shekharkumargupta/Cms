package com.opgea.cms.domain.modal;

import org.springframework.web.multipart.commons.CommonsMultipartFile;





public class FileUploadBean {

    private Long id;
    private CommonsMultipartFile file;
    private int documentTypeId;
    private String documentType;
    private Long resumeId;
    private Long uploadedById;

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setDocumentTypeId(int documentTypeId) {
            this.documentTypeId = documentTypeId;
    }
    public int getDocumentTypeId() {
            return documentTypeId;
    }
	
    public void setDocumentType(String documentType) {
            this.documentType = documentType;
    }
    public String getDocumentType() {
            return documentType;
    }
    
    public CommonsMultipartFile getFile() {
            return file;
    }
    public void setFile(CommonsMultipartFile file) {
            this.file = file;
    }
    
    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public Long getUploadedById() {
        return uploadedById;
    }

    public void setUploadedById(Long uploadedById) {
        this.uploadedById = uploadedById;
    }

    @Override
    public String toString() {
        return "FileUploadBean{" + "id=" + id + ", file=" + file + ", documentTypeId=" + documentTypeId + ", documentType=" + documentType + ", resumeId=" + resumeId + ", uploadedById=" + uploadedById + '}';
    }
    
}
