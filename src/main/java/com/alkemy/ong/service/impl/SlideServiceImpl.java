package com.alkemy.ong.service.impl;

import com.alkemy.ong.entity.SlideEntity;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {

    private final SlideRepository slideRepository;

    @Override
    public void deleteSlide(String id) {
        slideRepository.deleteById(getById(id).getId());
    }

    public SlideEntity getById(String id){
        return slideRepository.findById(id).orElseThrow(
                ()-> new ParamNotFound("Slide not found"));
    }
}
