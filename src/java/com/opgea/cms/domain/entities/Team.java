/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.domain.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ramesh
 */
@Entity
@Table(name = "team")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Team.findById", query = "SELECT t FROM Team t WHERE t.id = :id"),
    @NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t"),
    @NamedQuery(name = "Team.findAllByBranchId", query = "SELECT t FROM Team t WHERE t.branch.id = :branchId")}
)
public class Team implements Serializable {
    
    @Id
    @GeneratedValue        
    private Long id;
    private String name;
    @ManyToMany(fetch= FetchType.LAZY)
    private List<Category> categories;
    /*
    @OneToMany(fetch= FetchType.LAZY)
    private List<Employee> employees;
     * 
     */
    @ManyToMany(fetch= FetchType.EAGER)
    private List<Opening> openings;
    @ManyToOne
    private Branchh branch;

    public Team() {
    }

    public Branchh getBranch() {
        return branch;
    }

    /*
    public void addEmployee(Employee employee){
        employees.add(employee);
    }
    */
    
    public void addOpening(Opening opening){
        openings.add(opening);
    }
    
    public void setBranch(Branchh branch) {
        this.branch = branch;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /*
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    */
    
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

    public List<Opening> getOpenings() {
        return openings;
    }

    public void setOpenings(List<Opening> openings) {
        this.openings = openings;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Team other = (Team) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Team{" + "id=" + id + ", name=" + name + '}';
    }

  
    
}
