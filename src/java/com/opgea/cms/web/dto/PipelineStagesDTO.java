/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.dto;

/**
 *
 * @author Ramesh
 */
public class PipelineStagesDTO {

    
    private Long id;
    private String name;
    private Integer stepNo;
    private Long companyId;

    public PipelineStagesDTO() {
    }

    public PipelineStagesDTO(Long id, String name, Long companyId) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
    }

    public PipelineStagesDTO(Long id, String name, Integer stepNo, Long companyId) {
        this.id = id;
        this.name = name;
        this.stepNo = stepNo;
        this.companyId = companyId;
    }

    public Integer getStepNo() {
        return stepNo;
    }

    public void setStepNo(Integer stepNo) {
        this.stepNo = stepNo;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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

    
    
    @Override
    public String toString() {
        return "PipelineStagesDTO{" + "id=" + id + ", name=" + name + ", companyId=" + companyId + '}';
    }
   
}
