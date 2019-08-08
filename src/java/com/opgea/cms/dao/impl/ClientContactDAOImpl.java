/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.ClientContactDAO;
import com.opgea.cms.domain.entities.Client;
import com.opgea.cms.domain.entities.ClientContact;
import com.opgea.cms.domain.entities.Opening;
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
public class ClientContactDAOImpl implements ClientContactDAO{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ClientContact create(ClientContact clientContact) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(clientContact);
        tx.commit();
        session.close();
        return clientContact;
    }

    @Override
    public ClientContact update(ClientContact clientContact) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.update(clientContact);
        tx.commit();
        session.close();
        return clientContact;
    }

    @Override
    public ClientContact remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ClientContact find(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("ClientContact.findById");
        query.setParameter("id", id);
        ClientContact clientContact = (ClientContact) query.uniqueResult();
        session.close();
        return clientContact;
    }

    @Override
    public List<ClientContact> findByClientId(Long clientId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("ClientContact.findAllByClientId");
        query.setParameter("clientId", clientId);
        List<ClientContact> clientContacts = query.list();
        return clientContacts;
    }

    @Override
    public List<ClientContact> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("ClientContact.findAll");
        List<ClientContact> clientContacts = query.list();
        return clientContacts;
    }

    @Override
    public List<ClientContact> findByOpeningId(Long openingId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Opening.findById");
        query.setParameter("id", openingId);
        Opening opening = (Opening) query.uniqueResult();
        Client client = opening.getClient();
        
        //Query query2 = session.createQuery("FROM Client as c LEFT JOIN FETCH  c.contactPersons WHERE c.id="+client.getId());
        Query query2 = session.createQuery("FROM ClientContact c WHERE c.client.id="+client.getId());
        List<ClientContact> contacts = query2.list();
        System.out.println("ClientContacts Size: "+contacts.size());
        session.close();
        return contacts;
    }
    
}
