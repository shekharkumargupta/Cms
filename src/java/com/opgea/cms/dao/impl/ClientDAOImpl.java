/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao.impl;

import com.opgea.cms.dao.ClientDAO;
import com.opgea.cms.domain.entities.Client;
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
public class ClientDAOImpl implements ClientDAO{
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Client create(Client client) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(client);
        tx.commit();
        session.close();
        return client;
    }

    @Override
    public Client update(Client client) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.update(client);
        tx.commit();
        session.close();
        return client;
    }

    @Override
    public Client remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Client find(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Client.findById");
        query.setParameter("id", id);
        Client client = (Client) query.uniqueResult();
        session.close();
        return client;
    }

    @Override
    public List<Client> findByCompanyId(Long companyId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Client.findAllByCompanyId");
        query.setParameter("companyId", companyId);
        List<Client> clients = query.list();
        return clients;
    }

    @Override
    public List<Client> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Client.findAll");
        List<Client> clients = query.list();
        return clients;
    }
    
}
