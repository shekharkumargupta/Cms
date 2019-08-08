/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service.impl;

import com.opgea.cms.dao.CategoryDAO;
import com.opgea.cms.dao.CityDAO;
import com.opgea.cms.dao.ClientDAO;
import com.opgea.cms.dao.CompanyDAO;
import com.opgea.cms.domain.entities.Category;
import com.opgea.cms.domain.entities.City;
import com.opgea.cms.domain.entities.Client;
import com.opgea.cms.domain.entities.ClientContact;
import com.opgea.cms.domain.entities.Company;
import com.opgea.cms.domain.qualifiers.ContactType;
import com.opgea.cms.service.ClientService;
import com.opgea.cms.web.dto.ClientContactDTO;
import com.opgea.cms.web.dto.ClientDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class ClientServiceImpl implements ClientService{
    
    @Autowired
    private ClientDAO clientDAO;
    
    @Autowired
    private CompanyDAO companyDAO;
    
    @Autowired
    private CityDAO cityDAO;
    
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public ClientDTO create(ClientDTO clientDTO) {
        Company company = companyDAO.find(clientDTO.getCompanyId());
        City city = cityDAO.find(new Long(clientDTO.getCityId()));
        Category category = categoryDAO.find(clientDTO.getCategoryId());
        
        Client client = null;
        
        if(clientDTO.getClientId() > 0){
            client = clientDAO.find(clientDTO.getClientId());
        }else{
            client = new Client();
            client.setCompany(company);
        }
        
        //client.setCategory(category);
        client.setClientName(clientDTO.getClientName());
        client.setWebsite(clientDTO.getWebsite());
        client.setStreet1(clientDTO.getStreet1());
        client.setStreet2(clientDTO.getStreet2());
        client.setCity(city);
        client.setZipCode(clientDTO.getZipCode());
        client.setCompany(company);
        
        if(clientDTO.getClientId() > 0){
            clientDAO.update(client);        
        }else{
            clientDAO.create(client);
        }
        return clientDTO;
    }

    @Override
    public ClientDTO update(ClientDTO clientDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ClientDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ClientDTO find(Long id) {
        Client client = clientDAO.find(id);
        ClientDTO clientDTO = new ClientDTO();
        //clientDTO.setCategoryId(client.getCategory().getId());
        //clientDTO.setCategoryName(client.getCategory().getName());
        clientDTO.setClientId(client.getId());
        clientDTO.setClientName(client.getClientName());
        clientDTO.setWebsite(client.getWebsite());
        clientDTO.setCityId(client.getCity().getId());
        clientDTO.setCityName(client.getCity().getName());
        clientDTO.setCountryId(client.getCity().getCountry().getId());
        clientDTO.setCountryName(client.getCity().getCountry().getName());
        clientDTO.setStreet1(client.getStreet1());
        clientDTO.setStreet2(client.getStreet2());
        clientDTO.setZipCode(client.getZipCode());
        return clientDTO;
    }

    @Override
    public List<ClientDTO> findAll() {
        List<Client> clientList = clientDAO.findAll();
        List<ClientDTO> clientDTOList = new ArrayList<ClientDTO>();
        for(Client client : clientList){
            ClientDTO clientDTO = new ClientDTO();
            //clientDTO.setCategoryId(client.getCategory().getId());
            //clientDTO.setCategoryName(client.getCategory().getName());
            clientDTO.setClientId(client.getId());
            clientDTO.setClientName(client.getClientName());
            clientDTO.setWebsite(client.getWebsite());
            clientDTO.setCityId(client.getCity().getId());
            clientDTO.setCityName(client.getCity().getName());
            clientDTO.setCountryId(client.getCity().getCountry().getId());
            clientDTO.setCountryName(client.getCity().getCountry().getName());
            clientDTO.setStreet1(client.getStreet1());
            clientDTO.setStreet2(client.getStreet2());
            clientDTO.setZipCode(client.getZipCode());
            clientDTOList.add(clientDTO);
        }
        return clientDTOList;
    }

    @Override
    public List<ClientDTO> findAllByCompanyId(Long companyId) {
        List<Client> clientList = clientDAO.findByCompanyId(companyId);
        List<ClientDTO> clientDTOList = new ArrayList<ClientDTO>();
        for(Client client : clientList){
            ClientDTO clientDTO = new ClientDTO();
            //clientDTO.setCategoryId(client.getCategory().getId());
            //clientDTO.setCategoryName(client.getCategory().getName());
            clientDTO.setClientId(client.getId());
            clientDTO.setClientName(client.getClientName());
            clientDTO.setWebsite(client.getWebsite());
            clientDTO.setCityId(client.getCity().getId());
            clientDTO.setCityName(client.getCity().getName());
            clientDTO.setCountryId(client.getCity().getCountry().getId());
            clientDTO.setCountryName(client.getCity().getCountry().getName());
            clientDTO.setStreet1(client.getStreet1());
            clientDTO.setStreet2(client.getStreet2());
            clientDTO.setZipCode(client.getZipCode());
            clientDTOList.add(clientDTO);
        }
        return clientDTOList;
    }

    @Override
    public void addContact(Long clientId, ClientContactDTO clientContactDTO) {
        Client client = clientDAO.find(clientId);
        ClientContact contact = new ClientContact();
        contact.setFirstName(clientContactDTO.getFirstName());
        contact.setMiddleInitial(clientContactDTO.getMiddleInitial());
        contact.setLastName(clientContactDTO.getLastName());
        contact.setContactType(ContactType.values()[clientContactDTO.getContactType()]);
        contact.setEmail(clientContactDTO.getEmail());
        contact.setPhone1(clientContactDTO.getPhone1());
        contact.setPhone2(clientContactDTO.getPhone2());
        contact.setClient(client);
        
        if(clientContactDTO.getContactId() > 0){
            contact.setId(clientContactDTO.getContactId());
            client.getContactPersons().remove(client);
        }
        
        client.addContact(contact);
        clientDAO.update(client);
        
    }

    @Override
    public List<ClientContactDTO> getContactList(Long clientId) {
        Client client = clientDAO.find(clientId);
        List<ClientContact> contacts = client.getContactPersons();
        List<ClientContactDTO> contactDTOList = new ArrayList<ClientContactDTO>();
        for(ClientContact contact : contacts){
            ClientContactDTO contactDTO = new ClientContactDTO();
            contactDTO.setContactId(contact.getId());
            contactDTO.setFirstName(contact.getFirstName());
            contactDTO.setMiddleInitial(contact.getMiddleInitial());
            contactDTO.setLastName(contact.getLastName());
            contactDTO.setDesignation(contact.getDesignation());
            contactDTO.setContactType(contact.getContactType().ordinal());
            contactDTO.setEmail(contact.getEmail());
            contactDTO.setPhone1(contact.getPhone1());
            contactDTO.setPhone2(contact.getPhone2());
            contactDTOList.add(contactDTO);
        }
        return contactDTOList;
    }
    
}
