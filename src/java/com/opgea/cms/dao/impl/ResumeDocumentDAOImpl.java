/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.ResumeDocumentDAO;
import com.opgea.cms.domain.entities.ResumeDocument;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ramesh
 */
@Repository
public class ResumeDocumentDAOImpl implements ResumeDocumentDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public ResumeDocument create(ResumeDocument doc) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(doc);
        tx.commit();
        session.close();
        return doc;
    }

    @Override
    public ResumeDocument update(ResumeDocument doc) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.merge(doc);
        tx.commit();
        session.close();
        return doc;
    }

    @Override
    public ResumeDocument remove(Long documentId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResumeDocument find(Long documentId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("ResumeDocument.findById");
        query.setParameter("id", documentId);
        ResumeDocument resumeDocument = (ResumeDocument) query.uniqueResult();
        session.close();
        return resumeDocument;
    }

    @Override
    public List<ResumeDocument> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("ResumeDocument.findAll");
        List<ResumeDocument> resumeDocuments = query.list();
        return resumeDocuments;
    }
    
}
