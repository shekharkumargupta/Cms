/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.OpeningDetails;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface OpeningDetailsDAO {
    
    public OpeningDetails create(OpeningDetails details);
    public OpeningDetails update(OpeningDetails details);
    public OpeningDetails remove(Long openingDetailsId);
    public OpeningDetails find(Long openingDetailsId);
    public OpeningDetails findByOpeningId(Long openingId);
    public List<OpeningDetails> findAll();
    
}
