/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.dto;

/**
 *
 * @author Ramesh
 */
public class ResumeDTO {
    
    private Long id;
    
    private String name;
    private String email;
    private String contactNo;
    private Float experience;
    private String keySkills;
    private Long updatedById;
    private String metaData;
    private byte[] resumeContent;
    private String contentImage;
    private Long branchId;
    private String branchName;
    private Long companyId;
    private String companyName;
    private Long pipelineId;
    private Long pipelineStatusId;
    private String pipelineStatusName;
    private Long createdById;
    private String createdByName;
    private String createdAt;
    
    private String currentCompany;
    private String currentLocation;
    private Float currentSalary;
    private String currentDesignation;
    private String dateOfBirth;
    private String remarks;
    
    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getExperience() {
        return experience;
    }

    public void setExperience(Float experience) {
        this.experience = experience;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeySkills() {
        return keySkills;
    }

    public void setKeySkills(String keySkills) {
        this.keySkills = keySkills;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }
    
    
    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }


    public String getMetaData() {
        StringBuilder data = new StringBuilder();
        data.append(this.name);
        data.append(" ");
        data.append(this.contactNo);
        data.append(" ");
        data.append(this.email);
        data.append(" ");
        data.append(this.experience);
        data.append(" ");
        data.append(this.keySkills);
        data.append(" ");
        data.append(this.currentCompany);
        data.append(" ");
        data.append(this.currentLocation);
        data.append(" ");
        data.append(this.currentSalary);
        data.append(" ");
        data.append(this.currentDesignation);
        
        this.metaData = data.toString();
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public byte[] getResumeContent() {
        return resumeContent;
    }

    public void setResumeContent(byte[] resumeContent) {
        this.resumeContent = resumeContent;
    }

    public Long getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(Long pipelineId) {
        this.pipelineId = pipelineId;
    }

    public Long getPipelineStatusId() {
        return pipelineStatusId;
    }

    public void setPipelineStatusId(Long pipelineStatusId) {
        this.pipelineStatusId = pipelineStatusId;
    }

    public String getPipelineStatusName() {
        return pipelineStatusName;
    }

    public void setPipelineStatusName(String pipelineStatusName) {
        this.pipelineStatusName = pipelineStatusName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getCurrentCompany() {
        return currentCompany;
    }

    public void setCurrentCompany(String currentCompany) {
        this.currentCompany = currentCompany;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Float getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(Float currentSalary) {
        this.currentSalary = currentSalary;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCurrentDesignation() {
        return currentDesignation;
    }

    public void setCurrentDesignation(String currentDesignation) {
        this.currentDesignation = currentDesignation;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getContentImage() {
        if(this.resumeContent == null){
            this.contentImage = "BLANK_ICON";
        }else{
            this.contentImage = "WORD_ICON";
        }
        return contentImage;
    }

    public void setContentImage(String contentImage) {
        this.contentImage = contentImage;
    }

    
    
    @Override
    public String toString() {
        return "ResumeDTO{" + "id=" + id + ", branchId=" + branchId + ", updatedById=" + updatedById + ", metaData=" + metaData + '}';
    }

}
