package com.alkemy.ong.mapper.impl;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.entity.OrganizationEntity;
import com.alkemy.ong.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper implements Mapper<OrganizationDTO, OrganizationEntity> {
    @Override
    public OrganizationDTO toDto(OrganizationEntity entity) {
        OrganizationDTO dto = new OrganizationDTO();
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setImage(entity.getImage());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setTimestamp(entity.getTimestamp());
        dto.setWelcomeText(entity.getWelcomeText());
        dto.setAboutUsText(entity.getAboutUsText());
        dto.setUrlFacebook(entity.getUrlFacebook());
        dto.setUrlLinkedin(entity.getUrlLinkedin());
        dto.setUrlInstagram(entity.getUrlInstagram());
        return dto;
    }

    @Override
    public OrganizationEntity toEntity(OrganizationDTO dto) {
        OrganizationEntity entity = new OrganizationEntity();
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setImage(dto.getImage());
        entity.setPhone(dto.getPhone());
        entity.setAboutUsText(dto.getAboutUsText());
        entity.setEmail(dto.getEmail());
        entity.setWelcomeText(dto.getWelcomeText());
        entity.setUrlFacebook(dto.getUrlFacebook());
        entity.setUrlLinkedin(dto.getUrlLinkedin());
        entity.setUrlInstagram(dto.getUrlInstagram());
        return entity;
    }

    @Override
    public OrganizationDTO toBasicDto(OrganizationEntity entity) {
        OrganizationDTO dto = new OrganizationDTO();
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setImage(entity.getImage());
        dto.setPhone(entity.getPhone());
        dto.setUrlFacebook(entity.getUrlFacebook());
        dto.setUrlLinkedin(entity.getUrlLinkedin());
        dto.setUrlInstagram(entity.getUrlInstagram());
        return dto;
    }
}
