package com.alkemy.ong.mapper.impl;

import com.alkemy.ong.dto.SlideDTO;
import com.alkemy.ong.dto.SlideDTOResponse;
import com.alkemy.ong.entity.SlideEntity;
import com.alkemy.ong.mapper.Mapper;
import com.alkemy.ong.service.impl.OrganizationServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SlideMapper implements Mapper<SlideDTO, SlideEntity> {

    private final OrganizationServiceImp organizationService;

    @Override
    public SlideDTO toDto(SlideEntity slideEntity) {
        return null;
    }

    @Override
    public SlideEntity toEntity(SlideDTO slideDTO) {
        SlideEntity entity = new SlideEntity();
        entity.setText(slideDTO.getText());
        entity.setOrganizationId(organizationService.getById(slideDTO.getOrganization_id()).getId());
        return entity;
    }

    @Override
    public SlideDTO toBasicDto(SlideEntity slideEntity) {
        return null;
    }

    public SlideDTOResponse toDtoResponse(SlideEntity entity) {
        SlideDTOResponse dto = new SlideDTOResponse();
        dto.setImageUrl(entity.getImageUrl());
        dto.setText(entity.getText());
        dto.setOrder(entity.getSlideOrder());
        return dto;
    }

    public List<SlideDTOResponse> toDtoResponseList(List<SlideEntity> entityList) {
        List<SlideDTOResponse> dtoResponseList = new ArrayList<>();
        for (SlideEntity entity : entityList) {
            dtoResponseList.add(toDtoResponse(entity));
        }
        return dtoResponseList;
    }
}
