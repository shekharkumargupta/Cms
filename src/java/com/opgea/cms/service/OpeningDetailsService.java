/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service;

import com.opgea.cms.web.dto.OpeningDetailsDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface OpeningDetailsService {
    
    public OpeningDetailsDTO create(OpeningDetailsDTO detailsDTO);
    public OpeningDetailsDTO update(OpeningDetailsDTO detailsDTO);
    public OpeningDetailsDTO remove(Long openingDetailsId);
    public OpeningDetailsDTO find(Long openingDetailsId);
    public OpeningDetailsDTO findByOpeningId(Long openingId);
    public List<OpeningDetailsDTO> findAll();
    
    
}
