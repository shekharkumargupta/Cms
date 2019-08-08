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
@Table(name = "emailmessage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmailMessage.findById", query = "SELECT e FROM EmailMessage e WHERE e.id = :id"),
    @NamedQuery(name = "EmailMessage.findAll", query = "SELECT e FROM EmailMessage e"),
    @NamedQuery(name = "EmailMessage.findAllByBranchId", query = "SELECT e FROM EmailMessage e WHERE e.branch.id = :branchId")}
)
public class EmailMessage implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private String message;
    @OneToOne
    private Branchh branch;

    public Branchh getBranch() {
        return branch;
    }

    public void setBranch(Branchh branch) {
        this.branch = branch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
