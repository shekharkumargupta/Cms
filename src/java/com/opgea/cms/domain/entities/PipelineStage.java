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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ramesh
 */
@Entity
@Table(name = "pipelinestage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PipelineStage.findById", query = "SELECT p FROM PipelineStage p WHERE p.id = :id"),
    @NamedQuery(name = "PipelineStage.findAll", query = "SELECT p FROM PipelineStage p"),
    @NamedQuery(name = "PipelineStage.findAllByCompanyId", query = "SELECT p FROM PipelineStage p WHERE p.company.id = :companyId")}
)
public class PipelineStage implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer stepNo;
    @ManyToOne(cascade= CascadeType.ALL)
    private Company company;

    public PipelineStage() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public Integer getStepNo() {
        return stepNo;
    }

    public void setStepNo(Integer stepNo) {
        this.stepNo = stepNo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PipelineStage other = (PipelineStage) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "PipelineStatus{" + "id=" + id + ", name=" + name + ", company=" + company + '}';
    }
    
    
}
