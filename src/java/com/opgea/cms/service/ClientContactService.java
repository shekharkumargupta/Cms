/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service;

import com.opgea.cms.web.dto.ClientContactDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface ClientContactService {

    public ClientContactDTO create(ClientContactDTO clientDTO);
    public ClientContactDTO update(ClientContactDTO clientDTO);
    public ClientContactDTO remove(Long id);
    public ClientContactDTO find(Long id);
    public List<ClientContactDTO> findAll();
    public List<ClientContactDTO> findAllByClientId(Long clientId);
    public List<ClientContactDTO> findAllByOpeningId(Long openingId);
}
