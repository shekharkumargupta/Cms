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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ramesh
 */
@Entity
@Table(name = "pipelinehistory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PipelineHistory.findById", query = "SELECT p FROM PipelineHistory p WHERE p.id = :id"),
    @NamedQuery(name = "PipelineHistory.findAll", query = "SELECT p FROM PipelineHistory p"),
    @NamedQuery(name = "PipelineHistory.findAllByPipelineId", query = "SELECT p FROM PipelineHistory p WHERE p.pipeline.id = :pipelineId")}
)
public class PipelineHistory implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private PipelineStage pipelineStatus;
    private String remarks;
    @ManyToOne
    private Pipeline pipeline;
    @ManyToOne
    private Employee createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public PipelineHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PipelineStage getPipelineStatus() {
        return pipelineStatus;
    }

    public void setPipelineStatus(PipelineStage pipelineStatus) {
        this.pipelineStatus = pipelineStatus;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
        final PipelineHistory other = (PipelineHistory) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "PipelineHistory{" + "id=" + id + ", remarks=" + remarks + ", pipeline=" + pipeline + '}';
    }
    
    
}
