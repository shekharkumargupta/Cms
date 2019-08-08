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
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TeamAndOpening.findById", query = "SELECT o FROM TeamAndOpening o WHERE o.id = :id"),
    @NamedQuery(name = "TeamAndOpening.findAll", query = "SELECT o FROM TeamAndOpening o"),
    @NamedQuery(name = "TeamAndOpening.findAllByOpeningId", query = "SELECT o FROM TeamAndOpening o WHERE o.openingId = :openingId"),
    @NamedQuery(name = "TeamAndOpening.findAllByTeamId", query = "SELECT o FROM TeamAndOpening o WHERE o.teamId = :teamId")}
)
public class TeamAndOpening implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private Long openingId;
    private Long teamId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date allotedDate;
    private Long allotedById;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getOpeningId() {
        return openingId;
    }

    public void setOpeningId(Long openingId) {
        this.openingId = openingId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "TeamAndOpening{" + "id=" + id + ", openingId=" + openingId + ", teamId=" + teamId + ", allotedDate=" + allotedDate + ", allotedById=" + allotedById + '}';
    }
 
    
}
