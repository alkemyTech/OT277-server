package com.alkemy.ong.mapper.impl;

import com.alkemy.ong.entity.SlideEntity;
import com.alkemy.ong.security.dto.SlideDTOResponse;
import org.springframework.stereotype.Component;

@Component
public class SlideMapper {

    public SlideDTOResponse toDtoResponse(SlideEntity entity) {
        SlideDTOResponse dto = new SlideDTOResponse();
        dto.setImageUrl(entity.getImageUrl());
        dto.setText(entity.getText());
        dto.setOrder(entity.getSlideOrder());
        return dto;
    }
}
