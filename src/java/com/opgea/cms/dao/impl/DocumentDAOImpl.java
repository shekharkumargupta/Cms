/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.DocumentDAO;
import com.opgea.cms.domain.entities.Document;
import com.opgea.cms.domain.entities.Resume;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Shekhar
 */
@Repository
public class DocumentDAOImpl implements DocumentDAO{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Document create(Document document) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(document);
        tx.commit();
        session.close();
        return document;
    }

    @Override
    public Document update(Document document) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.update(document);
        tx.commit();
        session.close();
        return document;
    }

    @Override
    public Document remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Document find(Long documentId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Document.findById");
        query.setParameter("id", documentId);
        Document document = (Document) query.uniqueResult();
        session.close();
        return document;
    }

    @Override
    public List<Document> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Document.findAll");
        List<Document> documentList = query.list();
        session.close();
        return documentList;
    }

    @Override
    public List<Document> findAllByResumeId(Long resumeId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Document.findAllByResumeId")
        .setParameter("resumeId", resumeId);
        List<Document> documentList = query.list();
        session.close();
        return documentList;
    }
    
}
