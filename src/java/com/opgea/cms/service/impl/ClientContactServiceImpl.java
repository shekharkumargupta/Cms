/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service.impl;

import com.opgea.cms.dao.ClientContactDAO;
import com.opgea.cms.dao.ClientDAO;
import com.opgea.cms.domain.entities.Client;
import com.opgea.cms.domain.entities.ClientContact;
import com.opgea.cms.domain.qualifiers.ContactType;
import com.opgea.cms.service.ClientContactService;
import com.opgea.cms.web.dto.ClientContactDTO;
import com.opgea.util.DateUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class ClientContactServiceImpl implements ClientContactService{

    @Autowired
    private ClientContactDAO clientContactDAO;
    
    @Autowired
    private ClientDAO clientDAO;
    
    @Override
    public ClientContactDTO create(ClientContactDTO clientContactDTO) {
        Client client = clientDAO.find(clientContactDTO.getClientId());
        System.out.println("Client: "+client);
        ClientContact contact = null;
        if(clientContactDTO.getContactId() > 0){
            contact = clientContactDAO.find(clientContactDTO.getContactId());
        }else{
            contact = new ClientContact();
            contact.setClient(client);
        }
        contact.setFirstName(clientContactDTO.getFirstName());
        contact.setMiddleInitial(clientContactDTO.getMiddleInitial());
        contact.setLastName(clientContactDTO.getLastName());
        contact.setContactType(ContactType.values()[clientContactDTO.getContactType()]);
        contact.setEmail(clientContactDTO.getEmail());
        contact.setPhone1(clientContactDTO.getPhone1());
        contact.setPhone2(clientContactDTO.getPhone2());
        if(clientContactDTO.getDateOfBirth() != null){
            contact.setDateOfBirth(DateUtil.getDateFromYYYYMMDD(clientContactDTO.getDateOfBirth(), "-"));
        }
        if(clientContactDTO.getDateOfAnniversary() != null){
            contact.setDateOfAnniversary(DateUtil.getDateFromYYYYMMDD(clientContactDTO.getDateOfAnniversary(), "-"));
        }
        if(clientContactDTO.getContactId() > 0){
            clientContactDAO.update(contact);
        }else{
            clientContactDAO.create(contact);
        }
        return clientContactDTO;
    }

    @Override
    public ClientContactDTO update(ClientContactDTO clientDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ClientContactDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ClientContactDTO find(Long id) {
        ClientContact contact = clientContactDAO.find(id);
        ClientContactDTO clientContactDTO = new ClientContactDTO();
        clientContactDTO.setFirstName(contact.getFirstName());
        clientContactDTO.setMiddleInitial(contact.getMiddleInitial());
        clientContactDTO.setLastName(contact.getLastName());
        if(contact.getContactType() != null){
            clientContactDTO.setContactType(contact.getContactType().ordinal());
            clientContactDTO.setContactTypeName(contact.getContactType().name());
        }
        clientContactDTO.setEmail(contact.getEmail());
        clientContactDTO.setPhone1(contact.getPhone1());
        clientContactDTO.setPhone2(contact.getPhone2());
        clientContactDTO.setClientId(contact.getClient().getId());
        clientContactDTO.setContactId(contact.getId());
        if(contact.getDateOfBirth() != null){
            clientContactDTO.setDateOfBirth(DateUtil.getYYYYMMDDFromDate(contact.getDateOfBirth(), "-"));
        }
        if(contact.getDateOfAnniversary() != null){
            clientContactDTO.setDateOfAnniversary(DateUtil.getYYYYMMDDFromDate(contact.getDateOfAnniversary(), "-"));
        }
        return clientContactDTO;
    }

    @Override
    public List<ClientContactDTO> findAll() {
        List<ClientContact> contactList = clientContactDAO.findAll();
        List<ClientContactDTO> contactDTOList = new ArrayList<ClientContactDTO>();
        for(ClientContact contact : contactList){
            ClientContactDTO clientContactDTO = new ClientContactDTO();
            clientContactDTO.setFirstName(contact.getFirstName());
            clientContactDTO.setMiddleInitial(contact.getMiddleInitial());
            clientContactDTO.setLastName(contact.getLastName());
            if(contact.getContactType() != null){
                clientContactDTO.setContactType(contact.getContactType().ordinal());
                clientContactDTO.setContactTypeName(contact.getContactType().name());
            }
            clientContactDTO.setEmail(contact.getEmail());
            clientContactDTO.setPhone1(contact.getPhone1());
            clientContactDTO.setPhone2(contact.getPhone2());
            clientContactDTO.setClientId(contact.getClient().getId());
            clientContactDTO.setContactId(contact.getId());
            if(contact.getDateOfBirth() != null){
                clientContactDTO.setDateOfBirth(DateUtil.getYYYYMMDDFromDate(contact.getDateOfBirth(), "-"));
            }
            if(contact.getDateOfAnniversary() != null){
                clientContactDTO.setDateOfAnniversary(DateUtil.getYYYYMMDDFromDate(contact.getDateOfAnniversary(), "-"));
            }
            contactDTOList.add(clientContactDTO);
        }
        return contactDTOList;
    }

    @Override
    public List<ClientContactDTO> findAllByClientId(Long clientId) {
        List<ClientContact> contactList = clientContactDAO.findByClientId(clientId);
        List<ClientContactDTO> contactDTOList = new ArrayList<ClientContactDTO>();
        for(ClientContact contact : contactList){
            ClientContactDTO clientContactDTO = new ClientContactDTO();
            clientContactDTO.setFirstName(contact.getFirstName());
            clientContactDTO.setMiddleInitial(contact.getMiddleInitial());
            clientContactDTO.setLastName(contact.getLastName());
            if(contact.getContactType() != null){
                clientContactDTO.setContactType(contact.getContactType().ordinal());
                clientContactDTO.setContactTypeName(contact.getContactType().name());
            }
            clientContactDTO.setEmail(contact.getEmail());
            clientContactDTO.setPhone1(contact.getPhone1());
            clientContactDTO.setPhone2(contact.getPhone2());
            clientContactDTO.setClientId(contact.getClient().getId());
            clientContactDTO.setContactId(contact.getId());
            if(contact.getDateOfBirth() != null){
                clientContactDTO.setDateOfBirth(DateUtil.getYYYYMMDDFromDate(contact.getDateOfBirth(), "-"));
            }
            if(contact.getDateOfAnniversary() != null){
                clientContactDTO.setDateOfAnniversary(DateUtil.getYYYYMMDDFromDate(contact.getDateOfAnniversary(), "-"));
            }
            contactDTOList.add(clientContactDTO);
        }
        return contactDTOList;
    }

    @Override
    public List<ClientContactDTO> findAllByOpeningId(Long openingId) {
        List<ClientContact> contactList = clientContactDAO.findByOpeningId(openingId);
        List<ClientContactDTO> contactDTOList = new ArrayList<ClientContactDTO>();
        for(ClientContact contact : contactList){
            ClientContactDTO clientContactDTO = new ClientContactDTO();
            clientContactDTO.setFirstName(contact.getFirstName());
            clientContactDTO.setMiddleInitial(contact.getMiddleInitial());
            clientContactDTO.setLastName(contact.getLastName());
            if(contact.getContactType() != null){
                clientContactDTO.setContactType(contact.getContactType().ordinal());
                clientContactDTO.setContactTypeName(contact.getContactType().name());
            }
            clientContactDTO.setEmail(contact.getEmail());
            clientContactDTO.setPhone1(contact.getPhone1());
            clientContactDTO.setPhone2(contact.getPhone2());
            clientContactDTO.setClientId(contact.getClient().getId());
            clientContactDTO.setContactId(contact.getId());
            if(contact.getDateOfBirth() != null){
                clientContactDTO.setDateOfBirth(DateUtil.getYYYYMMDDFromDate(contact.getDateOfBirth(), "-"));
            }
            if(contact.getDateOfAnniversary() != null){
                clientContactDTO.setDateOfAnniversary(DateUtil.getYYYYMMDDFromDate(contact.getDateOfAnniversary(), "-"));
            }
            contactDTOList.add(clientContactDTO);
        }
        return contactDTOList;
    }
    
}
