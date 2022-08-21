package com.alkemy.ong.mapper.impl;

import com.alkemy.ong.dto.SimpleOrganizationDTO;
import com.alkemy.ong.entity.OrganizationEntity;
import com.alkemy.ong.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper implements Mapper<SimpleOrganizationDTO, OrganizationEntity> {
    @Override
    public SimpleOrganizationDTO toDto(OrganizationEntity organizationEntity) {
        var dto = new SimpleOrganizationDTO();
        dto.setName(organizationEntity.getName());
        dto.setAddress(organizationEntity.getAddress());
        dto.setImage(organizationEntity.getImage());
        dto.setPhone(organizationEntity.getPhone());
        return dto;
    }
}
