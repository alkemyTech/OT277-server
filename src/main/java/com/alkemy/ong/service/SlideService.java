package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideDTO;
import com.alkemy.ong.dto.SlideDTOResponse;

public interface SlideService {
    SlideDTOResponse saveSlide(SlideDTO dto);
}
