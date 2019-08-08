/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.domain.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ramesh
 */
@Entity
public class EmployeeAndOpening implements Serializable {
    
    
    @Id
    @GeneratedValue
    private Long id;
    private Long openingId;
    private Long employeeId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date allotedDate;
    private Long allotedById;

    public Long getAllotedById() {
        return allotedById;
    }

    public void setAllotedById(Long allotedById) {
        this.allotedById = allotedById;
    }

    public Date getAllotedDate() {
        return allotedDate;
    }

    public void setAllotedDate(Date allotedDate) {
        this.allotedDate = allotedDate;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOpeningId() {
        return openingId;
    }

    public void setOpeningId(Long openingId) {
        this.openingId = openingId;
    }
    
    
}
