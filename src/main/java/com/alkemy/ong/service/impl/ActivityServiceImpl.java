package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ActivityDTO;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.mapper.impl.ActivityMapper;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityMapper activityMapper;
    private final ActivityRepository activityRepository;
    @Override
    public ActivityDTO saveActivity(ActivityDTO activityDto) {
        return activityMapper.toDto(
                activityRepository.save(
                        activityMapper.toEntity(activityDto)));
    }

    @Override
    public ActivityDTO updateActivity(ActivityDTO activityDTO, String id) {
        var activity = this.activityRepository.findById(id).orElseThrow(
                ()-> new ParamNotFound("Param not found :" + id)
        );
        activity.setContent(activityDTO.getContent());
        activity.setImage(activityDTO.getImage());
        activity.setName(activityDTO.getName());
        return activityMapper.toDto(activityRepository.save(activity));
    }
}
