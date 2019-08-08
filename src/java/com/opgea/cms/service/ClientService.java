/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service;

import com.opgea.cms.web.dto.ClientContactDTO;
import com.opgea.cms.web.dto.ClientDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface ClientService {
    
    public ClientDTO create(ClientDTO clientDTO);
    public ClientDTO update(ClientDTO clientDTO);
    public void addContact(Long clientId, ClientContactDTO clientContactDTO);
    public ClientDTO remove(Long id);
    public ClientDTO find(Long id);
    public List<ClientDTO> findAll();
    public List<ClientDTO> findAllByCompanyId(Long companyId);
    public List<ClientContactDTO> getContactList(Long clientId);
}
