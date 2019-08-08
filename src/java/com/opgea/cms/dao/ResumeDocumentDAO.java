/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.ResumeDocument;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface ResumeDocumentDAO {
    
    public ResumeDocument create(ResumeDocument doc);
    public ResumeDocument update(ResumeDocument doc);
    public ResumeDocument remove(Long documentId);
    public ResumeDocument find(Long documentId);
    public List<ResumeDocument> findAll();
}
