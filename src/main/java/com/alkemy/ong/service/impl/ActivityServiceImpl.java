package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ActivityDTO;
import com.alkemy.ong.mapper.impl.ActivityMapper;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
