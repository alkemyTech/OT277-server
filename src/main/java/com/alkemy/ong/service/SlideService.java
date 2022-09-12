package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideDTO;
import com.alkemy.ong.dto.SlideDTOResponse;

import java.util.List;

public interface SlideService {

    SlideDTOResponse getById(String id);

    SlideDTOResponse saveSlide(SlideDTO dto);

    List<SlideDTOResponse> getSlides();

}
