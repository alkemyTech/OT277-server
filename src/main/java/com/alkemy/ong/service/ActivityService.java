package com.alkemy.ong.service;

import com.alkemy.ong.dto.ActivityDTO;

public interface ActivityService {

    ActivityDTO saveActivity(ActivityDTO activityDto);

    ActivityDTO updateActivity(ActivityDTO activityDTO, String id);
}
