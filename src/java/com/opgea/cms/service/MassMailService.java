/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.service;

import com.opgea.cms.web.dto.MassMailDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface MassMailService {
    
    public void create(List<MassMailDTO> massMailDTOList, Long openingId, Long senderId);
    public List<MassMailDTO> findAll();
    public MassMailDTO findById(Long massMailId);
    public List<MassMailDTO> findAllByOpeningId(Long openingId);
    public List<MassMailDTO> findAllByOpeningAndSenderId(Long openingId, Long senderId);
}
