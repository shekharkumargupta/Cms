/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.domain.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Ramesh
 */
@Entity
public class BranchDetails implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private Integer branchType;
    private String branchName = new String();
    private String street1 = new String();
    private String street2 = new String();
    @ManyToOne(cascade= CascadeType.ALL)
    private Country country;
    @ManyToOne(cascade= CascadeType.ALL)
    private City city;
    private String pinCode = new String();
    
    @OneToOne
    private Branchh branch;

    public BranchDetails() {
    }

    public BranchDetails(Long id, Integer branchType, String branchName, String street1, String street2, Branchh branch) {
        this.id = id;
        this.branchType = branchType;
        this.branchName = branchName;
        this.street1 = street1;
        this.street2 = street2;
        this.branch = branch;
    }

    public Branchh getBranch() {
        return branch;
    }

    public void setBranch(Branchh branch) {
        this.branch = branch;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Integer getBranchType() {
        return branchType;
    }

    public void setBranchType(Integer branchType) {
        this.branchType = branchType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BranchDetails other = (BranchDetails) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "BranchDetails{" + "id=" + id + ", branchType=" + branchType + ", branchName=" + branchName + ", street1=" + street1 + ", street2=" + street2 + ", country=" + country + ", city=" + city + ", pinCode=" + pinCode + '}';
    }

    
    
    
    
}
