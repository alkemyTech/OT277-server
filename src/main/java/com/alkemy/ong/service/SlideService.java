package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideDTO;
import com.alkemy.ong.dto.SlideDTOResponse;
import com.alkemy.ong.entity.SlideEntity;

import java.util.List;

public interface SlideService {

    SlideDTOResponse saveSlide(SlideDTO dto);

    List<SlideDTOResponse> getSlides();

    SlideEntity getById(String id);

    void deleteSlide(String id);

}
