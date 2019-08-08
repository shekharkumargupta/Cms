/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service.impl;

import com.opgea.cms.dao.OpeningDAO;
import com.opgea.cms.dao.OpeningDetailsDAO;
import com.opgea.cms.domain.entities.Opening;
import com.opgea.cms.domain.entities.OpeningDetails;
import com.opgea.cms.service.OpeningDetailsService;
import com.opgea.cms.web.dto.OpeningDetailsDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class OpeningDetailsServiceImpl implements OpeningDetailsService{
    
    @Autowired
    private OpeningDetailsDAO detailsDAO;
    
    @Autowired
    private OpeningDAO openingDAO;

    @Override
    public OpeningDetailsDTO create(OpeningDetailsDTO detailsDTO) {
        
        OpeningDetails details = null;
        Opening opening = openingDAO.find(detailsDTO.getOpeningId());
        if(detailsDTO.getId() > 0 && detailsDTO.getId() != null){
            details = detailsDAO.find(detailsDTO.getId());
            details.setJobDetails(detailsDTO.getDetails());
            detailsDAO.update(details);
            
            opening.setOpeningDetails(details);
            openingDAO.update(opening);
        }else{
            details = new OpeningDetails();
            details.setJobDetails(detailsDTO.getDetails());
            details.setOpening(opening);
            detailsDAO.create(details);
            
            opening.setOpeningDetails(details);
            openingDAO.update(opening);
        }
        return detailsDTO;
    }

    @Override
    public OpeningDetailsDTO update(OpeningDetailsDTO detailsDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public OpeningDetailsDTO remove(Long openingDetailsId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public OpeningDetailsDTO find(Long openingDetailsId) {
        OpeningDetails details = detailsDAO.find(openingDetailsId);
        OpeningDetailsDTO detailsDTO = new OpeningDetailsDTO();
        detailsDTO.setId(details.getId());
        detailsDTO.setDetails(details.getJobDetails());
        detailsDTO.setOpeningId(details.getOpening().getId());
        return detailsDTO;
    }

    @Override
    public OpeningDetailsDTO findByOpeningId(Long openingId) {
        OpeningDetails details = detailsDAO.findByOpeningId(openingId);
        OpeningDetailsDTO detailsDTO = new OpeningDetailsDTO();
        detailsDTO.setId(details.getId());
        detailsDTO.setDetails(details.getJobDetails());
        detailsDTO.setOpeningId(details.getOpening().getId());
        return detailsDTO;
    }

    @Override
    public List<OpeningDetailsDTO> findAll() {
        List<OpeningDetails> detailsList = detailsDAO.findAll();
        List<OpeningDetailsDTO> detailsDTOList = new ArrayList<OpeningDetailsDTO>();
        for(OpeningDetails details: detailsList){
            OpeningDetailsDTO detailsDTO = new OpeningDetailsDTO();
            detailsDTO.setId(details.getId());
            detailsDTO.setDetails(details.getJobDetails());
            detailsDTO.setOpeningId(details.getOpening().getId());
            detailsDTOList.add(detailsDTO);
        }
        return detailsDTOList;
    }
    
}
