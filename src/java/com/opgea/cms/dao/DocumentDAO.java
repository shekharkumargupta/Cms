/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.Document;
import java.util.List;

/**
 *
 * @author Shekhar
 */
public interface DocumentDAO {
    
    public Document create(Document document);
    public Document update(Document document);
    public Document remove(Long id);
    public Document find(Long documentId);
    public List<Document> findAll();
    public List<Document> findAllByResumeId(Long resumeId);
    
}
