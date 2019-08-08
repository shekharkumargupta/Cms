/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ramesh
 */
@Entity
@Table(name = "branchh")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Branch.findById", query = "SELECT b FROM Branchh b WHERE b.id = :id"),
    @NamedQuery(name = "Branch.findAll", query = "SELECT b FROM Branchh b"),
    @NamedQuery(name = "Branch.findAllByCompanyId", query = "SELECT b FROM Branchh b WHERE b.company.id = :companyId")}
)
public class Branchh implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    
    @OneToMany
    private List<Category> categories;
    @OneToMany
    private List<Employee> employees = new ArrayList<Employee>();
    @OneToMany
    private List<Team> teams;
    @OneToMany
    private List<Client> clients;
    
    @ManyToOne(cascade= CascadeType.ALL)
    private Company company;
    
    @OneToOne(cascade= CascadeType.ALL, mappedBy="branch")
    private BranchDetails branchDetails;
    
    
    public Branchh() {
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }


    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    
    public void addEmployee(Employee employee){
        this.employees.add(employee);
    }


    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public BranchDetails getBranchDetails() {
        return branchDetails;
    }

    public void setBranchDetails(BranchDetails branchDetails) {
        this.branchDetails = branchDetails;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Branchh other = (Branchh) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Branchh{" + "id=" + id + '}';
    }

   

   
 
}
