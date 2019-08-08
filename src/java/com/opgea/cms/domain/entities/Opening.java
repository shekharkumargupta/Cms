/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.domain.entities;

import com.opgea.cms.domain.qualifiers.OpeningStatus;
import com.opgea.cms.domain.qualifiers.SalaryBasis;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ramesh
 */
@Entity
@Table(name = "opening")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opening.findById", query = "SELECT o FROM Opening o WHERE o.id = :id"),
    @NamedQuery(name = "Opening.findAll", query = "SELECT o FROM Opening o"),
    @NamedQuery(name = "Opening.findAllByBranchId", query = "SELECT o FROM Opening o WHERE o.branch.id = :branchId"),
    @NamedQuery(name = "Opening.findAllByClientId", query = "SELECT o FROM Opening o WHERE o.client.id = :clientId"),
    @NamedQuery(name = "Opening.findAllByCompanyId", query = "SELECT o FROM Opening o WHERE o.branch.company.id = :companyId")}
)
public class Opening implements Serializable {
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String positionName;
    private String qualification;
    private OpeningStatus openingStatus;
    private Float minimumExperience;
    private Float maximumExperience;
    private Float minimumSalary;
    private Float maximumSalary;
    private String location;
    private SalaryBasis salaryBasis;
    private String keySkills;
    
    @ManyToOne
    private Currency currency;
    
    @ManyToOne
    private Client client;
    
    @ManyToMany(fetch= FetchType.LAZY, mappedBy="openings")
    private List<Team> teams;
    
    @ManyToMany(cascade= CascadeType.ALL)
    private List<Employee> employees;
    
    @ManyToMany
    private List<Resume> resumes;
    
    @ManyToOne
    private Branchh branch;
    
    @OneToOne
    private OpeningDetails openingDetails;
    
    @OneToMany
    private List<TeamAndOpening> teamAndOpenings;
    
    @OneToMany
    private List<EmployeeAndOpening> employeeAndOpenings;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @ManyToOne
    private Employee createdBy;
    
    //openingdetails

    public Opening() {
    }
    
    public void addTeam(Team team){
        this.teams.add(team);
    }
    
    public void addResume(Resume resume){
        this.resumes.add(resume);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Branchh getBranch() {
        return branch;
    }

    public void setBranch(Branchh branch) {
        this.branch = branch;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getMaximumSalary() {
        return maximumSalary;
    }

    public void setMaximumSalary(Float maximumSalary) {
        this.maximumSalary = maximumSalary;
    }

    public Float getMaximumExperience() {
        return maximumExperience;
    }

    public void setMaximumExperience(Float maximumExperience) {
        this.maximumExperience = maximumExperience;
    }

    public Float getMinimumExperience() {
        return minimumExperience;
    }

    public void setMinimumExperience(Float minimumExperience) {
        this.minimumExperience = minimumExperience;
    }

    public Float getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(Float minimumSalary) {
        this.minimumSalary = minimumSalary;
    }

    public OpeningStatus getOpeningStatus() {
        return openingStatus;
    }

    public void setOpeningStatus(OpeningStatus openingStatus) {
        this.openingStatus = openingStatus;
    }

    public SalaryBasis getSalaryBasis() {
        return salaryBasis;
    }

    public void setSalaryBasis(SalaryBasis salaryBasis) {
        this.salaryBasis = salaryBasis;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<TeamAndOpening> getTeamAndOpenings() {
        return teamAndOpenings;
    }

    public void setTeamAndOpenings(List<TeamAndOpening> teamAndOpenings) {
        this.teamAndOpenings = teamAndOpenings;
    }
    
    public List<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(List<Resume> resumes) {
        this.resumes = resumes;
    }

    public OpeningDetails getOpeningDetails() {
        return openingDetails;
    }

    public void setOpeningDetails(OpeningDetails openingDetails) {
        this.openingDetails = openingDetails;
    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
    }

    public List<EmployeeAndOpening> getEmployeeAndOpenings() {
        return employeeAndOpenings;
    }

    public void setEmployeeAndOpenings(List<EmployeeAndOpening> employeeAndOpenings) {
        this.employeeAndOpenings = employeeAndOpenings;
    }

    public String getKeySkills() {
        return keySkills;
    }

    public void setKeySkills(String keySkills) {
        this.keySkills = keySkills;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Opening other = (Opening) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Opening{" + "id=" + id + ", positionName=" + positionName + '}';
    }
 
}
