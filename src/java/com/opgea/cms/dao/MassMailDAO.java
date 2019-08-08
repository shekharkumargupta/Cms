/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.cms.dao;

import com.opgea.cms.domain.entities.MassMail;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface MassMailDAO {
    
    public void create(List<MassMail> massMailList);
    public List<MassMail> findAll();
    public MassMail findById(Long massMailId);
    public List<MassMail> findAllByOpeningId(Long openingId);
    public List<MassMail> findAllByOpeningAndSenderId(Long openingId, Long senderId);
    
}
