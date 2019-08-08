/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.domain.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ramesh
 */
@Entity
@Table(name = "resume")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resume.findById", query = "SELECT r FROM Resume r WHERE r.id = :id"),
    @NamedQuery(name = "Resume.findByEmailId", query = "SELECT r FROM Resume r WHERE r.email = :emailId"),
    @NamedQuery(name = "Resume.findAll", query = "SELECT r FROM Resume r"),
    @NamedQuery(name = "Resume.findBySearchKey", query = "SELECT r FROM Resume r WHERE r.metaData like :searchKey")
    }
)
public class Resume implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String contactNo;
    private Float experience;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;
    private String currentCompany;
    private String currentLocation;
    private Float currentSalary;
    private String currentDesignation;
    private String remarks;
    private String keySkills;
    @Lob
    private String metaData;
    
    @OneToOne
    private ResumeDocument resumeDocument;
    @ManyToOne
    private Branchh branch;
    @ManyToMany(mappedBy="resumes")
    private List<Opening> openings;
    @ManyToOne
    private Employee createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Resume() {
    }
    
    public void addOpening(Opening opening){
        this.openings.add(opening);
    }
    
  

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

    public String getCurrentCompany() {
        return currentCompany;
    }

    public void setCurrentCompany(String currentCompany) {
        this.currentCompany = currentCompany;
    }

    public String getCurrentDesignation() {
        return currentDesignation;
    }

    public void setCurrentDesignation(String currentDesignation) {
        this.currentDesignation = currentDesignation;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getKeySkills() {
        return keySkills;
    }

    public void setKeySkills(String keySkills) {
        this.keySkills = keySkills;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public ResumeDocument getResumeDocument() {
        return resumeDocument;
    }

    public void setResumeDocument(ResumeDocument resumeDocument) {
        this.resumeDocument = resumeDocument;
    }

    public Branchh getBranch() {
        return branch;
    }

    public void setBranch(Branchh branch) {
        this.branch = branch;
    }

    
    public List<Opening> getOpenings() {
        return openings;
    }

    public void setOpenings(List<Opening> openings) {
        this.openings = openings;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Resume other = (Resume) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Resume{" + "id=" + id + ", metaData=" + metaData + '}';
    }

    
    
}
