/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ramesh
 */
@Entity
@Table(name = "company")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Company.findById", query = "SELECT c FROM Company c WHERE c.id = :id"),
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c")}
)
public class Company implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private String companyName;
    private String email;
    private String website;
    private String contactNo;
    private Boolean isActive;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date activeTill;
    @OneToMany(fetch=FetchType.LAZY, mappedBy="company")
    private List<Branchh> branches = new ArrayList<Branchh>();
    public Company() {
    }

    public Date getActiveTill() {
        return activeTill;
    }

    public void setActiveTill(Date activeTill) {
        this.activeTill = activeTill;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void addBranch(Branchh branch){
        this.branches.add(branch);
    }
    
    public List<Branchh> getBranches() {
        return branches;
    }

    public void setBranches(List<Branchh> branches) {
        this.branches = branches;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Company other = (Company) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Company{" + "id=" + id + ", companyName=" + companyName + ", email=" + email + ", website=" + website + ", isActive=" + isActive + ", activeTill=" + activeTill + '}';
    }
 
    
    
}
