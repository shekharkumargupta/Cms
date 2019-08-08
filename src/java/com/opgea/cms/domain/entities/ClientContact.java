/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.domain.entities;

import com.opgea.cms.domain.qualifiers.ContactType;
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
@Table(name = "clientcontact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientContact.findById", query = "SELECT c FROM ClientContact c WHERE c.id = :id"),
    @NamedQuery(name = "ClientContact.findAll", query = "SELECT c FROM ClientContact c"),
    @NamedQuery(name = "ClientContact.findAllByClientId", query = "SELECT c FROM ClientContact c WHERE c.client.id = :clientId")}
)
public class ClientContact implements Serializable {
 
    @Id
    @GeneratedValue
    private Long id;
    private ContactType contactType;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String designation;
    private String email;
    private String phone1;
    private String phone2;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfAnniversary;
    private Boolean isActive;
    @ManyToOne
    private Client client;

    public ClientContact() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfAnniversary() {
        return dateOfAnniversary;
    }

    public void setDateOfAnniversary(Date dateOfAnniversary) {
        this.dateOfAnniversary = dateOfAnniversary;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClientContact other = (ClientContact) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ClientContact{" + "id=" + id + ", contactType=" + contactType + ", firstName=" + firstName + ", middleInitial=" + middleInitial + ", lastName=" + lastName + ", designation=" + designation + ", email=" + email + ", phone1=" + phone1 + ", phone2=" + phone2 + ", dateOfBirth=" + dateOfBirth + ", dateOfAnniversary=" + dateOfAnniversary + ", isActive=" + isActive + ", client=" + client + '}';
    }
    
    
}
