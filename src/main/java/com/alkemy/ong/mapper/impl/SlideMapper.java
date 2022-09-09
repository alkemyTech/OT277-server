package com.alkemy.ong.mapper.impl;

import com.alkemy.ong.entity.SlideEntity;
import com.alkemy.ong.security.dto.SlideDTOResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SlideMapper {

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
