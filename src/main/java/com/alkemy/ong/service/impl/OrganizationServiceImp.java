package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.mapper.impl.OrganizationMapper;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImp implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;
    @Override
    public OrganizationDTO getPublicInformation() {
        return organizationMapper.toDto(organizationRepository.findAll().stream()
                .findFirst().orElseThrow());
    }
}
