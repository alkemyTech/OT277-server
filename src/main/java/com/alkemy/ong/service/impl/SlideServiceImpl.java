package com.alkemy.ong.service.impl;

import com.alkemy.ong.mapper.impl.SlideMapper;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.security.dto.SlideDTOResponse;
import com.alkemy.ong.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SlideServiceImpl implements SlideService {

    private final SlideRepository slideRepository;
    private final SlideMapper slideMapper;

    @Override
    public List<SlideDTOResponse> getSlides() {
        return slideMapper.toDtoResponseList(slideRepository.findAll());
    }
}
