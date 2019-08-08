/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.dto;

import com.opgea.cms.domain.qualifiers.ConditionType;

/**
 *
 * @author Ramesh
 */
public class AdvanceSearchResumeDTO {

    
    private Long branchId;
    
    private String currentDesignation;
    private ConditionType designationLogic;
    
    private String keySkills;
    private ConditionType keySkillsLogic;
    
    private String qualification;
    private ConditionType qualificationLogic;
    
    private String location;
    private ConditionType locationLogic;
    
    private Long minSalary;
    private Long maxSalary;
    
    private Long minExp;
    private Long maxExp;

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getCurrentDesignation() {
        return currentDesignation;
    }

    public void setCurrentDesignation(String currentDesignation) {
        this.currentDesignation = currentDesignation;
    }

    public ConditionType getDesignationLogic() {
        return designationLogic;
    }

    public void setDesignationLogic(ConditionType designationLogic) {
        this.designationLogic = designationLogic;
    }

    
    
    public String getKeySkills() {
        return keySkills;
    }

    public void setKeySkills(String keySkills) {
        this.keySkills = keySkills;
    }

    public ConditionType getKeySkillsLogic() {
        return keySkillsLogic;
    }

    public void setKeySkillsLogic(ConditionType keySkillsLogic) {
        this.keySkillsLogic = keySkillsLogic;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ConditionType getLocationLogic() {
        return locationLogic;
    }

    public void setLocationLogic(ConditionType locationLogic) {
        this.locationLogic = locationLogic;
    }

    public Long getMaxExp() {
        return maxExp;
    }

    public void setMaxExp(Long maxExp) {
        this.maxExp = maxExp;
    }

    public Long getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Long maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Long getMinExp() {
        return minExp;
    }

    public void setMinExp(Long minExp) {
        this.minExp = minExp;
    }

    public Long getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Long minSalary) {
        this.minSalary = minSalary;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public ConditionType getQualificationLogic() {
        return qualificationLogic;
    }

    public void setQualificationLogic(ConditionType qualificationLogic) {
        this.qualificationLogic = qualificationLogic;
    }

    @Override
    public String toString() {
        return "AdvanceSearchResumeDTO{" + "branchId=" + branchId + ", currentDesignation=" + currentDesignation + ", designationLogic=" + designationLogic + ", keySkills=" + keySkills + ", keySkillsLogic=" + keySkillsLogic + ", qualification=" + qualification + ", qualificationLogic=" + qualificationLogic + ", location=" + location + ", locationLogic=" + locationLogic + ", minSalary=" + minSalary + ", maxSalary=" + maxSalary + ", minExp=" + minExp + ", maxExp=" + maxExp + '}';
    }

    
    
    
    
}
