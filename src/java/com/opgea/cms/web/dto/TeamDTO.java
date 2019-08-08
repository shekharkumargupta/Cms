/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.dto;

/**
 *
 * @author Ramesh
 */
public class TeamDTO {
    
    private Long id;
    private String name;
    private Long branchId;
    private Long openingId;

    public TeamDTO() {
    }

    public TeamDTO(Long id, String name, Long branchId) {
        this.id = id;
        this.name = name;
        this.branchId = branchId;
    }

    
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

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getOpeningId() {
        return openingId;
    }

    public void setOpeningId(Long openingId) {
        this.openingId = openingId;
    }

    @Override
    public String toString() {
        return "TeamDTO{" + "id=" + id + ", name=" + name + ", branchId=" + branchId + '}';
    }

    
    
    
    
    
    
}
