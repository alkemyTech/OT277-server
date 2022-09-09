package com.alkemy.ong.service;

import com.alkemy.ong.entity.SlideEntity;

public interface SlideService {
    SlideEntity getById(String id);
    void deleteSlide(String id);
}
