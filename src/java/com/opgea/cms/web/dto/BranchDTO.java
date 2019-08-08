/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.dto;

/**
 *
 * @author Ramesh
 */
public class BranchDTO {

    private Long branchId;
    private Integer branchType;
    //private String branchTypeString;
    private String branchName;
    private String street1;
    private String street2;
    private Long country;
    private String countryString;
    private Long city;
    private String cityString;
    private String pinCode;
    private Long companyId;
    
    /*
     * Branch Head Info
     */
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String email;
    private String contactNo;
    

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

    public int getBranchType() {
        return branchType;
    }

    public void setBranchType(Integer branchType) {
        this.branchType = branchType;
    }

    /*
    public String getBranchTypeString() {
        return branchTypeString;
    }

    public void setBranchTypeString(String branchTypeString) {
        this.branchTypeString = branchTypeString;
    }
    */ 
    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public String getCityString() {
        return cityString;
    }

    public void setCityString(String cityString) {
        this.cityString = cityString;
    }

    public String getCountryString() {
        return countryString;
    }

    public void setCountryString(String countryString) {
        this.countryString = countryString;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    @Override
    public String toString() {
        return "BranchDTO{" + "branchId=" + branchId + ", branchType=" + branchType + ", branchName=" + branchName + ", street1=" + street1 + ", street2=" + street2 + ", country=" + country + ", countryString=" + countryString + ", city=" + city + ", cityString=" + cityString + ", pinCode=" + pinCode + ", companyId=" + companyId + ", firstName=" + firstName + ", email=" + email + ", contactNo=" + contactNo + '}';
    }

  
    
}
