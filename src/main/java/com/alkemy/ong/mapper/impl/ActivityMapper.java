package com.alkemy.ong.mapper.impl;

import com.alkemy.ong.dto.ActivityDTO;
import com.alkemy.ong.entity.ActivityEntity;
import com.alkemy.ong.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper implements Mapper<ActivityDTO, ActivityEntity> {
    @Override
    public ActivityDTO toDto(ActivityEntity activityEntity) {
        ActivityDTO response = new ActivityDTO();
        response.setName(activityEntity.getName());
        response.setContent(activityEntity.getContent());
        response.setImage(activityEntity.getImage());
        return response;
    }

    @Override
    public ActivityEntity toEntity(ActivityDTO activityDTO) {
        ActivityEntity entity = new ActivityEntity();
        entity.setName(activityDTO.getName());
        entity.setContent(activityDTO.getContent());
        entity.setImage(activityDTO.getImage());
        return entity;
    }

    @Override
    public ActivityDTO toBasicDto(ActivityEntity activityEntity) {
        return null;
    }
}
