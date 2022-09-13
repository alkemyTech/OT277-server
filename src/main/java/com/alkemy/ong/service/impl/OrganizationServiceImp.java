package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.entity.OrganizationEntity;
import com.alkemy.ong.exception.ParamNotFound;
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
    public OrganizationDTO getPublicInformation(String id) {
        return organizationMapper.toDto(organizationRepository.
                findById(id).orElseThrow());
    }

    @Override
    public OrganizationDTO update(OrganizationDTO dto, String id) {

        if (!organizationRepository.findById(id).isPresent()) {
            throw new ParamNotFound("El id de organizacion es invalido");
        }
        OrganizationEntity entity = this.organizationMapper.toEntity(dto);
        entity.setId(id);
        return this.organizationMapper.toDtoAllAtributes(this.organizationRepository.save(entity));
    }

    public OrganizationEntity getById(String id) {
        return organizationRepository.findById(id).orElseThrow(
                () -> new ParamNotFound("Organization not found"));
    }

    @Override
    public OrganizationDTO save(OrganizationDTO dto) {
        return organizationMapper.toDto(organizationRepository.save(organizationMapper.toEntity(dto)));
    }
}
