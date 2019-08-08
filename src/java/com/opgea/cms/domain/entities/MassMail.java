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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ramesh
 */
@Entity
@Table(name = "massmail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MassMail.findById", query = "SELECT m FROM MassMail m WHERE m.id = :id"),
    @NamedQuery(name = "MassMail.findAll", query = "SELECT m FROM MassMail m"),
    @NamedQuery(name = "MassMail.findAllByOpeningId", query = "SELECT m FROM MassMail m WHERE m.opening.id = :openingId"),
    @NamedQuery(name = "MassMail.findAllByOpeningAndSender", query = "SELECT m FROM MassMail m WHERE m.opening.id = :openingId AND m.sentBy.id = :senderId")
    }
)
public class MassMail implements Serializable{
   
    @Id
    @GeneratedValue 
    private Long id;
    private String email;
    private String status;
    
    @ManyToOne
    private Opening opening;
    @ManyToOne
    private Employee sentBy;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date sentAt;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }

    public Employee getSentBy() {
        return sentBy;
    }

    public void setSentBy(Employee sentBy) {
        this.sentBy = sentBy;
    }

    @Override
    public String toString() {
        return "MassMail{" + "id=" + id + ", email=" + email + ", status=" + status + '}';
    }
    
    
}
