package com.alkemy.ong.service;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.entity.OrganizationEntity;

public interface OrganizationService {
    OrganizationDTO getPublicInformation(String id);

    OrganizationDTO update(OrganizationDTO dto, String id);

    OrganizationEntity getById(String id);

    OrganizationDTO save(OrganizationDTO dto);

    void delete(String id);

    void validate(String email);
}
