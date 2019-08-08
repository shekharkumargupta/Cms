/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.Client;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface ClientDAO {
    
    public Client create(Client client);
    public Client update(Client client);
    public Client remove(Long id);
    public Client find(Long id);
    public List<Client> findByCompanyId(Long companyId);
    public List<Client> findAll();
}
