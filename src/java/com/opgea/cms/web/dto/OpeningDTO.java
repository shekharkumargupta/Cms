/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.dto;

/**
 *
 * @author Ramesh
 */
public class OpeningDTO {
    
    private Long openingId;
    private Long clientId;
    private String clientName;
    private Long positionId;
    private String positionName;
    private String qualification;
    private Integer openingStatusId;
    private String openingStatusString;
    private String location;
    private Long branchId;
    private String branchName;
    private String keySkills;
    private String openingDetails;
    
    private Integer minLacs;
    private Integer minThousands;
    private Integer maxLacs;
    private Integer maxThousands;
    
    private Integer minYrs;
    private Integer minMonths;
    private Integer maxYrs;
    private Integer maxMonths;
    
    private Float minimumSalary;
    private Float maximumSalary;
    private Float minimumExp;
    private Float maximumExp;
    
    private String createdDate;
    private Long createdById;
    private String createdByName;


    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getOpeningId() {
        return openingId;
    }

    public void setOpeningId(Long openingId) {
        this.openingId = openingId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
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

    public Integer getOpeningStatusId() {
        return openingStatusId;
    }

    public void setOpeningStatusId(Integer openingStatusId) {
        this.openingStatusId = openingStatusId;
    }

    public String getOpeningStatusString() {
        return openingStatusString;
    }

    public void setOpeningStatusString(String openingStatusString) {
        this.openingStatusString = openingStatusString;
    }

    public String getOpeningDetails() {
        return openingDetails;
    }

    public void setOpeningDetails(String openingDetails) {
        this.openingDetails = openingDetails;
    }

    public Integer getMaxLacs() {
        return maxLacs;
    }

    public void setMaxLacs(Integer maxLacs) {
        this.maxLacs = maxLacs;
    }

    public Integer getMaxMonths() {
        return maxMonths;
    }

    public void setMaxMonths(Integer maxMonths) {
        this.maxMonths = maxMonths;
    }

    public Integer getMaxThousands() {
        return maxThousands;
    }

    public void setMaxThousands(Integer maxThousands) {
        this.maxThousands = maxThousands;
    }

    public Integer getMaxYrs() {
        return maxYrs;
    }

    public void setMaxYrs(Integer maxYrs) {
        this.maxYrs = maxYrs;
    }

    public Integer getMinLacs() {
        return minLacs;
    }

    public void setMinLacs(Integer minLacs) {
        this.minLacs = minLacs;
    }

    public Integer getMinMonths() {
        return minMonths;
    }

    public void setMinMonths(Integer minMonths) {
        this.minMonths = minMonths;
    }

    public Integer getMinThousands() {
        return minThousands;
    }

    public void setMinThousands(Integer minThousands) {
        this.minThousands = minThousands;
    }

    public Integer getMinYrs() {
        return minYrs;
    }

    public void setMinYrs(Integer minYrs) {
        this.minYrs = minYrs;
    }
    
    public Float getMaximumExp() {
        if(maximumExp == null && this.maxYrs != null && this.maxMonths != null){
            this.maximumExp = Float.valueOf(this.maxYrs+"."+this.maxMonths);
        }
        return maximumExp;
    }

    public void setMaximumExp(Float maximumExp) {
        this.maximumExp = maximumExp;
    }

    public Float getMinimumExp() {
        if(minimumExp == null  && this.minYrs != null && this.minMonths != null){
            this.minimumExp = Float.valueOf(this.minYrs+"."+this.minMonths);
        }
        return minimumExp;
    }

    public void setMinimumExp(Float minimumExp) {
        this.minimumExp = minimumExp;
    }
    
    public Float getMaximumSalary() {
        if(maximumSalary == null && this.maxLacs != null && this.maxThousands != null){
            //this.maximumSalary = Integer.valueOf((this.maxLacs*100000)+(this.maxThousands*1000));
            this.maximumSalary = Float.valueOf(this.maxLacs+"."+this.maxThousands);
        }
        return maximumSalary;
    }

    public void setMaximumSalary(Float maximumSalary) {
        this.maximumSalary = maximumSalary;
    }

    public Float getMinimumSalary() {
        if(minimumSalary == null && this.minLacs != null && this.minThousands != null){
            //this.minimumSalary = Integer.valueOf((this.minLacs*100000)+(this.minThousands*1000));
            this.minimumSalary = Float.valueOf(this.minLacs+"."+this.minThousands);
        }
        return minimumSalary;
    }

    public void setMinimumSalary(Float minimumSalary) {
        this.minimumSalary = minimumSalary;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
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

    public String getKeySkills() {
        return keySkills;
    }

    public void setKeySkills(String keySkills) {
        this.keySkills = keySkills;
    }

    @Override
    public String toString() {
        return "OpeningDTO{" + "openingId=" + openingId + ", clientId=" + clientId + ", clientName=" + clientName + ", categoryId=" + ", positionId=" + positionId + ", positionName=" + positionName + ", qualification=" + qualification + ", openingStatusId=" + openingStatusId + ", openingStatusString=" + openingStatusString + ", location=" + location + ", minimumSalary=" + minimumSalary + ", maximumSalary=" + maximumSalary + ", minimumExp=" + minimumExp + ", maximumExp=" + maximumExp + ", branchId=" + branchId + ", branchName=" + branchName + '}';
    }

    
    
    
    
    
    
}
