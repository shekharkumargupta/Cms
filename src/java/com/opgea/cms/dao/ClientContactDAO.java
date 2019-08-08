/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.ClientContact;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface ClientContactDAO {
    
    public ClientContact create(ClientContact clientContact);
    public ClientContact update(ClientContact clientContact);
    public ClientContact remove(Long id);
    public ClientContact find(Long id);
    
    public List<ClientContact> findByClientId(Long clientId);
    public List<ClientContact> findByOpeningId(Long openingId);
    public List<ClientContact> findAll();
    
}
