/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.Resume;
import com.opgea.cms.web.dto.AdvanceSearchResumeDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface ResumeDAO {
    
    public Resume create(Resume resume);
    public int createBatch(List<Resume> resumeList);
    public Resume update(Resume resume);
    public Resume remove(Long id);
    public Resume find(Long id);
    public Resume find(String emailId);
    public List<Resume> findAllByEmailIds(String commaSeperatedEmailIds);
    public List<Resume> findAll();
    public List<Resume> findAllByOpeningId(Long openingId);
    public List<Resume> findBySearchKey(String searchKey, Long branchId);
    public List<Resume> advanceSearch(AdvanceSearchResumeDTO searchDTO);
    public boolean uploadResume(Long resumeId, String content);
}
