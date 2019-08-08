/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.domain.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ramesh
 */
@Entity
@Table(name = "openingdetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpeningDetails.findById", query = "SELECT o FROM OpeningDetails o WHERE o.id = :id"),
    @NamedQuery(name = "OpeningDetails.findByOpeningId", query = "SELECT o FROM OpeningDetails o WHERE o.opening.id = :openingId"),
    @NamedQuery(name = "OpeningDetails.findAll", query = "SELECT o FROM OpeningDetails o")}
)
public class OpeningDetails implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private String jobDetails;
    @OneToOne
    private Opening opening;

    public OpeningDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobDetails() {
        return jobDetails;
    }

    public void setJobDetails(String jobDetails) {
        this.jobDetails = jobDetails;
    }

    public Opening getOpening() {
        return opening;
    }

    public void setOpening(Opening opening) {
        this.opening = opening;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OpeningDetails other = (OpeningDetails) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "OpeningDetails{" + "id=" + id + ", jobDetails=" + jobDetails + ", opening=" + opening + '}';
    }
    
    
}
