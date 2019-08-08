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
@Table(name = "pipeline")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pipeline.findById", query = "SELECT p FROM Pipeline p WHERE p.id = :id"),
    @NamedQuery(name = "Pipeline.findAll", query = "SELECT p FROM Pipeline p"),
    @NamedQuery(name = "Pipeline.findAllByOpeningId", query = "SELECT p FROM Pipeline p WHERE p.opening.id = :openingId")}
)
public class Pipeline implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Opening opening;
    @ManyToOne
    private Resume resume;
    @ManyToOne
    private PipelineStage pipelineStatus;
    @ManyToOne
    private Employee pipelinedBy;
    @ManyToOne
    private Team pipelinedByTeam;
    @ManyToOne
    private Company company;
    @Temporal(TemporalType.TIMESTAMP)
    private Date pipelineAt;

    public Pipeline() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Opening getOpening() {
        return opening;
    }

    public void setOpening(Opening opening) {
        this.opening = opening;
    }

    public PipelineStage getPipelineStatus() {
        return pipelineStatus;
    }

    public void setPipelineStatus(PipelineStage pipelineStatus) {
        this.pipelineStatus = pipelineStatus;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public Date getPipelineAt() {
        return pipelineAt;
    }

    public void setPipelineAt(Date pipelineAt) {
        this.pipelineAt = pipelineAt;
    }

    public Employee getPipelinedBy() {
        return pipelinedBy;
    }

    public void setPipelinedBy(Employee pipelinedBy) {
        this.pipelinedBy = pipelinedBy;
    }

    public Team getPipelinedByTeam() {
        return pipelinedByTeam;
    }

    public void setPipelinedByTeam(Team pipelinedByTeam) {
        this.pipelinedByTeam = pipelinedByTeam;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pipeline other = (Pipeline) obj;
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
        return "Pipeline{" + "id=" + id + ", opening=" + opening + ", resume=" + resume + ", pipelineStatus=" + pipelineStatus + '}';
    }
    
    
}
