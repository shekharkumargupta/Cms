/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.web.dto;

/**
 *
 * @author Ramesh
 */
public class PipelineHistoryDTO {
    
    private Long id;
    private Long pipelineId;
    private Long pipelineStatusId;
    private String pipelineStatusName;
    private String remarks;
    private Long createdById;
    private String createdByName;
    private String createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(Long pipelineId) {
        this.pipelineId = pipelineId;
    }

    public Long getPipelineStatusId() {
        return pipelineStatusId;
    }

    public void setPipelineStatusId(Long pipelineStatusId) {
        this.pipelineStatusId = pipelineStatusId;
    }

    public String getPipelineStatusName() {
        return pipelineStatusName;
    }

    public void setPipelineStatusName(String pipelineStatusName) {
        this.pipelineStatusName = pipelineStatusName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    @Override
    public String toString() {
        return "PipelineHistoryDTO{" + "id=" + id + ", pipelineId=" + pipelineId + ", pipelineStatusId=" + pipelineStatusId + ", pipelineStatusName=" + pipelineStatusName + ", remarks=" + remarks + '}';
    }
    
    
    
}
