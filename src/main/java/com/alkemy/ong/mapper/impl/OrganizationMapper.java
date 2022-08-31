package com.alkemy.ong.mapper.impl;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.entity.OrganizationEntity;
import com.alkemy.ong.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper implements Mapper<OrganizationDTO, OrganizationEntity> {
    @Override
    public OrganizationDTO toDto(OrganizationEntity organizationEntity) {
        var dto = new OrganizationDTO();
        dto.setName(organizationEntity.getName());
        dto.setAddress(organizationEntity.getAddress());
        dto.setImage(organizationEntity.getImage());
        dto.setPhone(organizationEntity.getPhone());
        return dto;
    }


    public OrganizationDTO toDtoAllAtributes(OrganizationEntity organizationEntity) {
        var dto = new OrganizationDTO();
        dto.setName(organizationEntity.getName());
        dto.setAddress(organizationEntity.getAddress());
        dto.setImage(organizationEntity.getImage());
        dto.setPhone(organizationEntity.getPhone());
        dto.setEmail(organizationEntity.getEmail());
        dto.setTimestamp(organizationEntity.getTimestamp());
        dto.setWelcomeText(organizationEntity.getWelcomeText());
        dto.setAboutUsText(organizationEntity.getAboutUsText());
        return dto;
    }


    @Override
    public OrganizationEntity toEntity(OrganizationDTO organizationDTO) {
        OrganizationEntity entity = new OrganizationEntity();
        entity.setName(organizationDTO.getName());
        entity.setAddress(organizationDTO.getAddress());
        entity.setImage(organizationDTO.getImage());
        entity.setPhone(organizationDTO.getPhone());
        entity.setAboutUsText(organizationDTO.getAboutUsText());
        entity.setEmail(organizationDTO.getEmail());
        entity.setWelcomeText(organizationDTO.getWelcomeText());
        return entity;
    }

    @Override
    public OrganizationDTO toBasicDto(OrganizationEntity organizationEntity) {
        return null;
    }
}
